package stress;


import com.occ.dal.Dao;
import com.occ.dal.DataAccess;
import com.occ.dal.QueryableDao;
import com.occ.openstack.model.entities.Image;
import com.occ.openstack.model.entities.Server;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashSet;

public class CuInsert {
    final DataAccess<Server> serverDao = Dao.of(Server.class);
    final DataAccess<Volume> volumeDao = Dao.of(Volume.class);
    final DataAccess<Image> imageDao = Dao.of(Image.class);

    final static String NEWLINE = "\n";
    final static String SPACE = " ";

    final Util util = new Util();

    protected static final Logger logger = Logger.getLogger(CuInsert.class);

    /**
     * Creates output
     * no_of_records time
     */
    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records time_simple time_weakrefs time_weak_isync" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 50; i++) {
            logger.info("Iteration: " + i);
            long startTime = System.nanoTime();
            Util.ModelContainer modelContainer = util.composeModel(step);
            long endTime = System.nanoTime();
            long modelCreationTime = (endTime - startTime);

            outBuf.append((step == 1 ? 1 : (step - 1)) + SPACE);

            util.clearCache();
            util.threadSleep(500);
            Dao.INSTANCE_SYNCHRONIZE_ENABLED = false;
            Dao.WEAK_RELATIONS_REFERENCE_CREATION_ENABLED = false;
            double timeSimple = (double) (util.measure(storeModel(modelContainer)) + modelCreationTime) / 1000000000.0;
            outBuf.append(timeSimple + SPACE);

            util.clearCache();
            util.threadSleep(500);
            Dao.WEAK_RELATIONS_REFERENCE_CREATION_ENABLED = true;
            Dao.INSTANCE_SYNCHRONIZE_ENABLED = false;
            double timeWeak = (double) (util.measure(storeModel(modelContainer)) + modelCreationTime) / 1000000000.0;
            outBuf.append(timeWeak + SPACE);

            util.clearCache();
            util.threadSleep(500);
            Dao.WEAK_RELATIONS_REFERENCE_CREATION_ENABLED = true;
            Dao.INSTANCE_SYNCHRONIZE_ENABLED = true;
            double timeWeakSync = (double) (util.measure(storeModel(modelContainer)) + modelCreationTime) / 1000000000.0;
            outBuf.append(timeWeakSync + SPACE);

            outBuf.append(NEWLINE);
            step += 1000;
        }
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
