package stress;


import com.occ.dal.Dao;
import com.occ.dal.DataAccess;
import com.occ.dal.QueryableDao;
import com.occ.openstack.model.entities.Image;
import com.occ.openstack.model.entities.Server;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class CuInsertWithWeak {
    final DataAccess<Server> serverDao = Dao.of(Server.class);
    final DataAccess<Volume> volumeDao = Dao.of(Volume.class);
    final DataAccess<Image> imageDao = Dao.of(Image.class);

    final String OUTPUT_FILE = "insert_without_weak.txt";
    final String NEWLINE = "\n";

    final Util util = new Util();

    protected static final Logger logger = Logger.getLogger(CuInsertWithWeak.class);

    /**
     * Creates output
     * no_of_records time
     */
    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records time_nosync time_isync" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 50; i++) {
            logger.info("Iteration: " + i);
            long startTime = System.nanoTime();
            Util.ModelContainer modelContainer = util.composeModel(step);
            long endTime = System.nanoTime();
            long modelCreationTime = (endTime - startTime);

            util.clearCache();
            util.threadSleep(500);
            Dao.INSTANCE_SYNCHRONIZE_ENABLED = false;
            double timeNosync = (double) (util.measure(storeModel(modelContainer)) + modelCreationTime) / 1000000000.0;

            util.clearCache();
            util.threadSleep(500);
            Dao.INSTANCE_SYNCHRONIZE_ENABLED = true;
            double timeSync = (double) (util.measure(storeModel(modelContainer)) + modelCreationTime) / 1000000000.0;

            outBuf.append((step == 1 ? 1 : (step - 1)) + " " + timeNosync + " " + timeSync + NEWLINE);
            step += 1000;
        }
        System.out.println(outBuf);
        QueryableDao<Server> qServerDao = QueryableDao.qOf(Server.class);

//        Query query = qServerDao.getQueryBuilder().keyword().onField("name").matching("server-1").createQuery();
//        List<Server> servers = qServerDao.searchByQuery(query);
//        System.out.println(servers);
    }


    Runnable storeModel(Util.ModelContainer modelContainer) {
        return () -> {
            serverDao.put(new HashSet<>(modelContainer.servers), server -> {
                server.getAttachedVolumeIds().forEach(id -> volumeDao.putWeak(id));
                imageDao.putWeak(server.getImageId());
                return null;
            });
            imageDao.put(new HashSet<>(modelContainer.images), null);
            volumeDao.put(new HashSet<>(modelContainer.volumes), null);
        };
    }

}
