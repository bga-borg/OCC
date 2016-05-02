package com.occ.dal;


import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;

import java.util.List;

public class QueryableDao<T> extends Dao implements QueryableIndexedDataAccess {
    @Override
    public List<T> searchByQuery(Query luceneQuery) {
        SearchManager searchManager = Search.getSearchManager(cache);
        CacheQuery cq = searchManager.getQuery(luceneQuery, clazz);
        return (List<T>) cq.list();
    }

    @Override
    public QueryBuilder getQueryBuilder() {
        SearchManager searchManager = Search.getSearchManager(cache);
        return searchManager.buildQueryBuilderForClass(clazz).get();
    }

    @Override
    public List<T> searchByAttribute(String attributeName, String searchValue) {
        QueryBuilder qb = this.getQueryBuilder();
        Query query = qb.keyword().onField(attributeName).matching(searchValue).createQuery();
        SearchManager searchManager = Search.getSearchManager(cache);
        CacheQuery cq = searchManager.getQuery(query, clazz);
        return (List<T>) cq.list();
    }

    public static <T> QueryableDao<T> qOf(Class<T> clazz) {
        QueryableDao<T> dao = new QueryableDao<>();
        dao.clazz = clazz;
        return dao;
    }

}
