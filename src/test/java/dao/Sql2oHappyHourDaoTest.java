package dao;

import models.HappyHour;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/16/17.
 */
public class Sql2oHappyHourDaoTest {
    private Sql2oHappyHourDao happyHourDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        happyHourDao = new Sql2oHappyHourDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void HappyhourSetsId_True(){
        HappyHour happyHour = helper();
        happyHourDao.add(happyHour);
        int happyId = happyHour.getId();
        assertEquals(happyId, happyHour.getId());
    }
    @Test
    public void HappyhourFindbySpecificId_True(){
        HappyHour happyHour = helper();
        happyHourDao.add(happyHour);
        int happyId = happyHour.getId();
        HappyHour otherHappyHour = happyHourDao.findById(happyId);
        assertEquals(happyHour, otherHappyHour);
    }
    @Test
    public void HappyhourRetreiveAllInstances_True(){
        HappyHour happyHour = helper();
        HappyHour otherHour = helper2();
        happyHourDao.add(happyHour);
        happyHourDao.add(otherHour);
        List<HappyHour> show = happyHourDao.getAll();
        assertEquals(2, show.size());
    }
    @Test
    public void HappyhourReturnsNoInstancesWhenEmpty_True(){
        HappyHour happyHour = helper();
        HappyHour otherHour = helper2();
        happyHourDao.add(happyHour);
        happyHourDao.add(otherHour);
        int daoSize = happyHourDao.getAll().size();
        happyHourDao.deleteAllHappyHours();
        assertTrue(daoSize > 0 && daoSize > happyHourDao.getAll().size());
    }

    @Test
    public void HappyHourUpdate_True(){
        HappyHour happyHour = helper();
        HappyHour happyHour2 = helper2();
        happyHourDao.add(happyHour);
        happyHourDao.add(happyHour2);
        int idNum = happyHour2.getId();
        System.out.println(idNum);
        int nNum = 1;
        String expectedAddress = "epicodus";
        happyHourDao.updateHappyHour("7", "9", 6, "Restaurant", "epicodus", idNum, nNum);
        assertEquals(expectedAddress, happyHourDao.findById(idNum).getAddress());
    }
    @Test
    public void happyHourDeleteByID_True(){
        HappyHour happyHour = helper();
        happyHourDao.add(happyHour);
        happyHourDao.deleteById(happyHour.getId());
        assertEquals(0,happyHourDao.getAll().size());
    }



    public HappyHour helper(){
        return new HappyHour("4:30","6:30",5,"Henry's","Across Burnside",1);
    }
    public HappyHour helper2(){
        return new HappyHour("3:30","6:30",4,"EastBurn","On Burnside",1);
    }
}