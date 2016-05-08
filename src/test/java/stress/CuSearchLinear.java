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
import java.util.Random;

public class CuSearchLinear {
    protected static final Logger logger = Logger.getLogger(CuSearchLinear.class);
    final DataAccess<Server> serverDao = Dao.of(Server.class);
    final DataAccess<Volume> volumeDao = Dao.of(Volume.class);
    final DataAccess<Image> imageDao = Dao.of(Image.class);
    final String NEWLINE = "\n";
    final Util util = new Util();

    /**
     * Creates output
     * no_of_records time
     */
    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records lin_search_time" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 60; i++) {
            logger.info("Iteration: " + i);
            Util.ModelContainer modelContainer = util.composeModel(step);

//            outBuf.append((step == 1 ? 1 : (step - 1)) + " ");

            util.clearCache();
            util.threadSleep(500);
            storeModel(modelContainer).run();
            int randomVolume = new Random().nextInt(step > 100 ? step / 10 : 1);
            Dao.STREAM_PARALLEL = false;
            double timeSingle = (double) (util.measure(searchForAttributeSimple(volumeDao, "name", "volume" + randomVolume))) / 1000000000.0;
            outBuf.append(timeSingle + " ");

            util.clearCache();
            util.threadSleep(500);
            Dao.STREAM_PARALLEL = true;
            storeModel(modelContainer).run();
            double timeParallel = (double) (util.measure(searchForAttributeSimple(volumeDao, "name", "volume" + randomVolume))) / 1000000000.0;
            outBuf.append(timeParallel);

            outBuf.append(NEWLINE);
            step += 20000;
        }
        System.out.println(outBuf);
    }

    public Runnable searchForAttributeSimple(final DataAccess<Volume> dao, final String attrName, final String value) {
        return () -> {
            List<Volume> list = dao.searchByAttribute(attrName, value);
            logger.info("Found:" + list.size());
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
