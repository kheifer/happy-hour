package dao;
import models.HappyHour;

import java.util.List;

public interface HappyHourDao {

     void add (HappyHour happyHour);

     HappyHour findById(int id);

     List<HappyHour> getAll();

     void deleteAllHappyHours();

     void updateHappyHour(String startTime, String endTime, int rating, String restaurantName, String address, int id, int neighborhoodId);

     void deleteById(int id);

     //void deleteAllHappyHoursByCategoryId(int categoryId);
}
