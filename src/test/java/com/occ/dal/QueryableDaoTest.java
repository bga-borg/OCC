package com.occ.dal;

import com.occ.openstack.model.entities.Image;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bg on 2016.04.26..
 */
public class QueryableDaoTest {

    @Test
    public void testSearchByQuery() throws Exception {
        QueryableDao<Image> imageDao = QueryableDao.qOf(Image.class);
        QueryBuilder queryBuilder = imageDao.getQueryBuilder();
        Query query = queryBuilder.keyword()
                .onField("name")
                .matching("cirros")
                .createQuery();
        List<Image> images = imageDao.searchByQuery(query);
    }

    @Test
    public void testGetQueryBuilder() throws Exception {

    }

    @Test
    public void testSearchByAttribute() throws Exception {

    }
}