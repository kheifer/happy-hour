package dao;
import models.HappyHour;

import java.util.List;

public interface HappyHourDao {
     void add (HappyHour happyHour);

     HappyHour findById(int id);

     List<HappyHour> getAll();

     void deleteAllHappyHours();

}
