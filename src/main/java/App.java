import dao.Sql2oHappyHourDao;
import dao.Sql2oNeighborhoodDao;
import models.HappyHour;
import models.Neighborhood;
import org.sql2o.Sql2o;

import static spark.Spark.staticFileLocation;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
/**
 * Created by Guest on 8/16/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHappyHourDao happyHourDao = new Sql2oHappyHourDao(sql2o);
        Sql2oNeighborhoodDao neighborhoodDao = new Sql2oNeighborhoodDao(sql2o);
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            List<HappyHour> happyHourList = happyHourDao.getAll();
            model.put("neighborhoods", neighborhoods);
            model.put("happyHours", happyHourList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show for, to enter new neighborhood
        get("/neighborhood/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"neighborhood-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new neighborhoodform
        post("/neighborhood/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String location = request.queryParams("neighborhoodName");
            String description = request.queryParams("eventDescription");
            Neighborhood neighborhood = new Neighborhood(location, description);
            neighborhoodDao.add(neighborhood);
            model.put("restaurants", neighborhood);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show for, to enter new happyhour
        get("/happyhours/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"happyhour-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new happyhour
        post("/happyhours/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String happyHourName = request.queryParams("HappyHourName");
            String address = request.queryParams("address");
            String startTime = request.queryParams("startTime");
            String endTime = request.queryParams("endTime");
            Integer rating= Integer.parseInt(request.queryParams("rating"));
            String neighborhood = "blah";
            int id = neighborhoodDao.findByName(neighborhood);
            HappyHour happyHour = new HappyHour(startTime, endTime, rating,happyHourName, address, id );
            happyHourDao.add(happyHour);
            model.put("restaurants", neighborhood);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}


