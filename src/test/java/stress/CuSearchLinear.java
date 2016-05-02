package stress;


import com.occ.dal.Dao;
import com.occ.dal.DataAccess;
import com.occ.openstack.model.entities.Image;
import com.occ.openstack.model.entities.Server;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class CuSearchLinear {
    final DataAccess<Server> serverDao = Dao.of(Server.class);
    final DataAccess<Volume> volumeDao = Dao.of(Volume.class);
    final DataAccess<Image> imageDao = Dao.of(Image.class);


    final String OUTPUT_FILE = "insert_without_weak.txt";
    final String NEWLINE = "\n";

    final Util util = new Util();

    protected static final Logger logger = Logger.getLogger(CuSearchLinear.class);

    /**
     * Creates output
     * no_of_records time
     */
    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records lin_search_time" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 50; i++) {
            logger.info("Iteration: " + i);
            long startTime = System.nanoTime();
            Util.ModelContainer modelContainer = util.composeModel(step);
            long endTime = System.nanoTime();
            long modelCreationTime = (endTime - startTime);

            util.clearCache();
            util.threadSleep(500);
            storeModel(modelContainer).run();

            double timeSingle = (double) (util.measure(searchForAttributeSimple(serverDao, "name", "server-1")) + modelCreationTime) / 1000000000.0;

            outBuf.append((step == 1 ? 1 : (step - 1)) + " " + timeSingle + NEWLINE);
            step += 1000;
        }
        System.out.println(outBuf);
    }

    public Runnable searchForAttributeSimple(final DataAccess dao, final String attrName, final String value){
        return () -> {
            List list = dao.searchByAttribute(attrName, value);
            logger.info(list);
        };
    }

    private Runnable storeModel(Util.ModelContainer modelContainer) {
        return () -> {
            serverDao.put(new HashSet<>(modelContainer.servers), null);
            imageDao.put(new HashSet<>(modelContainer.images), null);
            volumeDao.put(new HashSet<>(modelContainer.volumes), null);
        };
    }
}
