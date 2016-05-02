package com.occ.dal;

import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

public interface QueryableIndexedDataAccess<T> extends DataAccess {

    public List<T> searchByQuery(Query luceneQuery);

    public QueryBuilder getQueryBuilder();
}
