package dao;

import models.HappyHour;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


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
        int happyId = happyHour.getId();
        happyHourDao.add(happyHour);
        assertEquals(happyId, happyHour.getId());
    }



    public HappyHour helper(){
        return new HappyHour("4:30","6:30",5,"Henry's","Across Burnside");
    }
}