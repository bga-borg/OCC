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

public class CuSearchIndexed {
    final QueryableDao<Server> serverDao = QueryableDao.qOf(Server.class);
    final QueryableDao<Volume> volumeDao = QueryableDao.qOf(Volume.class);
    final QueryableDao<Image> imageDao = QueryableDao.qOf(Image.class);

    final String NEWLINE = "\n";

    final Util util = new Util();

    protected static final Logger logger = Logger.getLogger(CuSearchIndexed.class);

    @Test
    public void test() {
        StringBuffer outBuf = new StringBuffer();
        outBuf.append("no_of_records lin_search_time" + NEWLINE);

        int step = 1;
        for (int i = 0; i < 50; i++) {
            Util.ModelContainer modelContainer = util.composeModel(step);

            util.clearCache();
            util.threadSleep(500);
            storeModel(modelContainer).run();
            double timeSingle = (double) (util.measure(searchForAttributeIndexed(serverDao, "name", "server-1"))) / 1000000000.0;

            outBuf.append((step == 1 ? 1 : (step - 1)) + " " + timeSingle + NEWLINE);
            step += 1000;
        }
        System.out.println(outBuf);
    }

    private Runnable searchForAttributeIndexed(QueryableDao<Server> serverDao, String field, String expression) {
        return () -> {
            Query query = serverDao.getQueryBuilder().keyword().onField(field).matching(expression).createQuery();
            List<Server> servers = serverDao.searchByQuery(query);
//            logger.info("Search result: " + servers.stream().findFirst());
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
