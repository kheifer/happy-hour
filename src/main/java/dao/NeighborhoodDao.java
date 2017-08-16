package dao;

import models.HappyHour;
import models.Neighborhood;

import java.util.List;

/**
 * Created by Guest on 8/16/17.
 */
public interface NeighborhoodDao {

    void add(Neighborhood neighborhood);

    List<Neighborhood> getAll();

    List<HappyHour> getAllHappyhourByNeighborhood(int categoryId);

    Neighborhood findById(int id);

    Integer findByName(String name);

    void update(String newName, String newDescription, int id);

    void deleteAll();

    void deleteByID(int id);

}
