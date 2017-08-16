package dao;

import models.Neighborhood;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

/**
 * Created by Guest on 8/16/17.
 */
public class Sql2oNeighborhoodDao implements NeighborhoodDao{
    private final Sql2o sql2o;
    public Sql2oNeighborhoodDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Neighborhood neighborhood) {
        String sql = "INSERT INTO neighborhood (name, description) VALUES (:name, :description)";
            try(Connection con = sql2o.open()){
                int id = (int) con.createQuery(sql) //make a new variable
                        .bind(neighborhood) //map my argument onto the query so we can use information from it
                        .executeUpdate() //run it all
                        .getKey(); //int id is now the row number (row “key”) of db
                neighborhood.setId(id);
            }catch (Sql2oException ex) {
                System.out.println(ex); //oops we have an error!
            }
    }
}
