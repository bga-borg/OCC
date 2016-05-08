package stress;


import com.occ.dal.QueryableDao;
import com.occ.openstack.model.entities.Image;
import com.occ.openstack.model.entities.Server;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class CuSearchIndexed {
    protected static final Logger logger = Logger.getLogger(CuSearchIndexed.class);
    final QueryableDao<Server> serverDao = QueryableDao.qOf(Server.class);
    final QueryableDao<Volume> volumeDao = QueryableDao.qOf(Volume.class);
    final QueryableDao<Image> imageDao = QueryableDao.qOf(Image.class);
    final String NEWLINE = "\n";
    final Util util = new Util();

    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records indexed_search_time" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 60; i++) {
            logger.info("Iteration: " + i);
            Util.ModelContainer modelContainer = util.composeModel(step);
            outBuf.append((step == 1 ? 1 : (step - 1)) + " ");

            util.clearCache();
            util.threadSleep(500);
            storeModel(modelContainer).run();
            int randomVolume = new Random().nextInt(step > 100 ? step / 10 : 1);
            double timeSingle = (double) (util.measure(searchForAttributeIndexed(volumeDao, "name", "volume" + randomVolume))) / 1000000000.0;

            outBuf.append(timeSingle + NEWLINE);
            step += 20000;
        }
        System.out.println(outBuf);
    }

    private Runnable searchForAttributeIndexed(QueryableDao<Volume> dao, String field, String expression) {
        return () -> {
            Query query = dao.getQueryBuilder().keyword().onField(field).matching(expression).createQuery();
            List<Volume> volumes = dao.searchByQuery(query);
            logger.info("Found:" + volumes.size());
//          logger.info("Search result: " + servers.stream().findFirst());
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
