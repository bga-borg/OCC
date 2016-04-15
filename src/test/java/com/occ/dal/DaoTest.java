package com.occ.dal;

import com.occ.openstack.model.entities.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class DaoTest {

    public static final String SERVER_1_ID = "server1";
    Dao<Server> dao;

    @Before
    public void init(){
        dao = Dao.of(Server.class);
    }

    @Test
    public void testPut() throws Exception {
        Server server = new Server();
        server.setId(SERVER_1_ID);
        server.setName(SERVER_1_ID);

        dao.put(server);

        Optional<Server> retrievedServer = dao.get(SERVER_1_ID);
        assertTrue(retrievedServer.isPresent());
        assertTrue(retrievedServer.get().getId().equals(SERVER_1_ID));
    }

    @Test
    public void testGetNull() throws Exception {
        Optional<Server> wrong_id = dao.get("wrong_id");
        assertTrue(!wrong_id.isPresent());
    }
}