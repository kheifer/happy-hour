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
    @Test
    public void getAllNeighborhoods() throws Exception{
        Neighborhood neighborhood = setNew();
        Neighborhood neighborhood1 = setNew2();
        neighborhoodDao.add(neighborhood);
        neighborhoodDao.add(neighborhood1);
        neighborhoodDao.getAll();
        assertEquals(2, neighborhoodDao.getAll().size());
    }
    @Test
    public void neighborhoodFindById_True() throws Exception{
        Neighborhood neighborhood = setNew();
        Neighborhood neighborhood1 = setNew2();
        neighborhoodDao.add(neighborhood);
        neighborhoodDao.add(neighborhood1);
        Neighborhood test = neighborhoodDao.findById(neighborhood.getId());
        assertEquals(neighborhood.getDescription(), test.getDescription());
    }
    @Test
    public void neighborhoodUpdateById_True() throws Exception{
        Neighborhood neighborhood = setNew();
        Neighborhood neighborhood1 = setNew2();
        neighborhoodDao.add(neighborhood);
        neighborhoodDao.add(neighborhood1);
        int search = neighborhood.getId();
        neighborhoodDao.update("Norf Norf","This is a description", neighborhood.getId());
        assertEquals("Norf Norf", neighborhoodDao.findById(search).getName());
    }
    @Test
    public void neighborhoodDeleteAll_True(){

    }

    public Neighborhood setNew(){
        return new Neighborhood("Northeast","North of South, East of West");
    }
    public Neighborhood setNew2(){
        return new Neighborhood("Southeast","South of North, East of West");
    }
}