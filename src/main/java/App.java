import com.sun.tools.internal.xjc.model.Model;
import dao.HappyHourDao;
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
        String connectionString = "jdbc:h2:~/happyhour.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHappyHourDao happyHourDao = new Sql2oHappyHourDao(sql2o);
        Sql2oNeighborhoodDao neighborhoodDao = new Sql2oNeighborhoodDao(sql2o);
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            List<HappyHour> happyHourList = happyHourDao.getAll();
            model.put("neighborhoods", neighborhoods);
            model.put("happyhours", happyHourList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show for, to enter new neighborhood
        get("/neighborhoods/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"neighborhood-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new neighborhoodform
        post("/neighborhoods/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String location = request.queryParams("neighborhoodName");
            String description = request.queryParams("eventDescription");
            Neighborhood neighborhood = new Neighborhood(location, description);
            neighborhoodDao.add(neighborhood);
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("neighborhoods", neighborhoods);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show for, to enter new happyhour
        get("/happyhours/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("neighborhoods", neighborhoods);
            return new ModelAndView(model,"happyhour-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new happyhour
        post("/happyhours/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String neighborhood = request.queryParams("neighborhood");
            String happyHourName = request.queryParams("HappyHourName");
            String address = request.queryParams("address");
            String startTime = request.queryParams("startTime");
            String endTime = request.queryParams("endTime");
            Integer rating= Integer.parseInt(request.queryParams("rating"));
            int resId = neighborhoodDao.findByName(neighborhood);
            System.out.println(resId);
            HappyHour happyHour = new HappyHour(startTime, endTime, rating,happyHourName, address, resId );
            happyHourDao.add(happyHour);
            List<HappyHour> happyHourList = happyHourDao.getAll();
            model.put("happyhours", happyHourList);
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("neighborhoods", neighborhoods);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/happyhours/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            happyHourDao.deleteAllHappyHours();
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("neighborhoods", neighborhoods);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //see happyhour details
        get("/happyhours/:id", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            HappyHour happyHour = happyHourDao.findById(Integer.parseInt(request.params("id")));
            model.put("happyhour", happyHour);
            return new ModelAndView(model, "happyhour-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //edit a happyhour
        get("/happyhours/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            HappyHour editHappyHour = happyHourDao.findById(Integer.parseInt(request.params("id")));
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("neighborhoods", neighborhoods);
            model.put("editHappyHour", editHappyHour);
            return new ModelAndView(model, "happyhour-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/happyhours/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String neighborhood = request.queryParams("neighborhood");
            String happyHourName = request.queryParams("HappyHourName");
            String address = request.queryParams("address");
            String startTime = request.queryParams("startTime");
            String endTime = request.queryParams("endTime");
            Integer rating= Integer.parseInt(request.queryParams("rating"));
            int resId = neighborhoodDao.findByName(neighborhood);
            int id = Integer.parseInt(request.params("id"));
            happyHourDao.updateHappyHour(startTime, endTime, rating,happyHourName, address, id, resId );
            List<HappyHour> happyHourList = happyHourDao.getAll();
            model.put("happyhours", happyHourList);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/happyhours/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            happyHourDao.deleteById(Integer.parseInt(request.params("id")));
            List<HappyHour> happyHourList = happyHourDao.getAll();
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("happyhours", happyHourList);
            model.put("neighborhoods", neighborhoods);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/neighborhoods/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<HappyHour> happyhourByNeighborhood = neighborhoodDao.getAllHappyhourByNeighborhood(Integer.parseInt(request.params("id")));
            List<Neighborhood> neighborhoods = neighborhoodDao.getAll();
            model.put("neighborhoods", neighborhoods);
            model.put("happyhours", happyhourByNeighborhood);
            return new ModelAndView(model, "happyhoursByNeighborhood.hbs");
        }, new HandlebarsTemplateEngine());

    }
}


