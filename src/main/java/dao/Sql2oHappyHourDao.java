package dao;
import models.HappyHour;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHappyHourDao implements HappyHourDao {
    private final Sql2o sql2o;
    public Sql2oHappyHourDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(HappyHour happyHour){
        String sql = "INSERT INTO happyhour(startTime, endTime, rating, restaurantName, address) VALUES (:startTime, :endTime, :rating, :restaurantName, :address)";
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .bind(happyHour) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            happyHour.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
    @Override
    public HappyHour findById(int id){
        String sql = "SELECT * FROM happyhour WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(HappyHour.class);
        }
    }

    @Override
    public List<HappyHour> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM happyHour")
                    .executeAndFetch(HappyHour.class);
        }
    }

    @Override
    public void deleteAllHappyHours() {
        try(Connection con = sql2o.open()){
             con.createQuery("DELETE FROM happyhour")
                    .executeUpdate();
        }
    }
}
