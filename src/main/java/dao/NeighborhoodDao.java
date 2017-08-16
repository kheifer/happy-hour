package dao;

import models.Neighborhood;

import java.util.List;

/**
 * Created by Guest on 8/16/17.
 */
public interface NeighborhoodDao {

    void add(Neighborhood neighborhood);

    List<Neighborhood> getAll();

    Neighborhood findById(int id);

}
