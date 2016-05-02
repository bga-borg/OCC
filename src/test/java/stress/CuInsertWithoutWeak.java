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

import java.util.List;

public class CuInsertWithoutWeak {
    final DataAccess<Server> serverDao = Dao.of(Server.class);
    final DataAccess<Volume> volumeDao = Dao.of(Volume.class);
    final DataAccess<Image> imageDao = Dao.of(Image.class);

    final String OUTPUT_FILE = "insert_without_weak.txt";
    final String NEWLINE = "\n";

    final Util util = new Util();

    protected static final Logger logger = Logger.getLogger(CuInsertWithoutWeak.class);

    /**
     * Creates output
     * no_of_records time
     */
    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records time_single" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 50; i++) {
            logger.info("Iteration: " + i);
            long startTime = System.nanoTime();
            Util.ModelContainer modelContainer = util.composeModel(step);
            long endTime = System.nanoTime();
            long modelCreationTime = (endTime - startTime);


            util.clearCache();
            util.threadSleep(500);
            double timeSingle = (double) (util.measure(storeModel(modelContainer)) + modelCreationTime) / 1000000000.0;
//
//            util.clearCache();
//            util.threadSleep(500);
//            double timeParallel = (double) (util.measure(storeModelParallel(modelContainer)) + modelCreationTime) / 1000000000.0;

            outBuf.append((step == 1 ? 1 : (step - 1)) + " " + timeSingle /*+ " " + timeParallel*/ + NEWLINE);
            step += 1000;
        }
        System.out.println(outBuf);

    }



    Runnable storeModelParallel(Util.ModelContainer modelContainer) {
        return () -> {
            modelContainer.servers.parallelStream().forEach(server -> serverDao.put(server));
            modelContainer.images.parallelStream().forEach(image -> imageDao.put(image));
            modelContainer.volumes.parallelStream().forEach(volume -> volumeDao.put(volume));
        };
    }

    Runnable storeModel(Util.ModelContainer modelContainer) {
        return () -> {
            modelContainer.servers.stream().forEach(server -> serverDao.put(server));
            modelContainer.images.stream().forEach(image -> imageDao.put(image));
            modelContainer.volumes.stream().forEach(volume -> volumeDao.put(volume));
        };
    }
}
