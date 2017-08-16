package dao;

import models.Neighborhood;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oNeighborhoodDaoTest {
    private Sql2oNeighborhoodDao neighborhoodDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        neighborhoodDao = new Sql2oNeighborhoodDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void neighborhoodAddsNewInstanceWithId_True() throws Exception{
        Neighborhood neighborhood = setNew();
        neighborhoodDao.add(neighborhood);
        assertEquals(1, neighborhood.getId());
    }

    public Neighborhood setNew(){
        return new Neighborhood("Northeast","North of South, East of West");
    }
}