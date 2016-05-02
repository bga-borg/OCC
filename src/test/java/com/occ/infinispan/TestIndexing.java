package com.occ.infinispan;


import com.occ.infinispan.testmodel.Author;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.Index;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.SearchManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;
import java.util.UUID;

public class TestIndexing {

    @Test
    public void testSimpleIndexing() throws Exception {

        Configuration infinispanConfiguration = new ConfigurationBuilder()
//                .indexing().index(Index.ALL)
                .build();

        DefaultCacheManager cacheManager = new DefaultCacheManager(infinispanConfiguration);

        Cache<String, Object> cache = cacheManager.getCache();
        SearchManager sm = org.infinispan.query.Search.getSearchManager(cache);


        for (int i = 0; i < 100; i++) {
            Author author = new Author(UUID.randomUUID().toString(), "Manik"+i, "Surtani"+i);
            cache.put(author.getId(), author);
        }

        QueryBuilder qb = sm.buildQueryBuilderForClass(Object.class).get();
        Query q = qb.keyword().onField("name").matching("Manik1").createQuery();

        CacheQuery cq = sm.getQuery(q, Author.class);
        Assert.assertEquals(1, cq.list().size());
    }
}
