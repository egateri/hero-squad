package com.moringaschool.student;

import com.moringaschool.student.config.DBConfig;
import com.moringaschool.student.hero.Hero;
import com.moringaschool.student.squad.Squad;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        staticFileLocation("/public");
         Connection connection = DBConfig.getConnection();

        if(connection!=null) {
            get("/", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                ModelAndView model = new ModelAndView(payload, "home.hbs");
                return engine.render(model);
            });

            get("/squad", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                List<String> list = new ArrayList<>();
                list.add("id:1");
                list.add("Squad One");
                list.add("Size: 10");
                list.add("Cause");
                payload.put("squad", list);
                return new ModelAndView(payload, "squad.hbs");
            }, new HandlebarsTemplateEngine());

            get("/squad/new", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                return new ModelAndView(payload, "create-squad.hbs");
            }, new HandlebarsTemplateEngine());

            post("/squad/new", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                List<String> list = new ArrayList<>();
                String name = request.queryParams("name");
                String maxSize = request.queryParams("maxSize");
                Integer size = Integer.parseInt(maxSize);
                String cause = request.queryParams("cause");
                list.add(name);
                list.add(maxSize);
                list.add(cause);
                payload.put("squad", list);
                final String query = "INSERT INTO squad (name,size,cause) VALUES (:name,:size,:cause)";
                connection.createQuery(query)
                        .addParameter("name",name)
                        .addParameter("size",size)
                        .addParameter("cause",cause)
                        .executeUpdate();
                return new ModelAndView(payload, "squad.hbs");
            }, new HandlebarsTemplateEngine());

            get("/squad/all", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                final String query = "SELECT * FROM squad";
               List<Squad> squads = connection.createQuery(query).executeAndFetch(Squad.class);
                payload.put("squads", squads);
                return new ModelAndView(payload, "all-squad.hbs");
            }, new HandlebarsTemplateEngine());

            get("/hero", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                ModelAndView model = new ModelAndView(payload, "hero.hbs");
                return engine.render(model);
            });

            get("/hero/new", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                return new ModelAndView(payload, "create-hero.hbs");
            }, new HandlebarsTemplateEngine());
            post("/hero/new", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                List<String> list = new ArrayList<>();
                String name = request.queryParams("name");
                String ageAsString = request.queryParams("age");
                Integer age = Integer.parseInt(ageAsString);
                String power = request.queryParams("power");
                String weakness = request.queryParams("weakness");
                list.add(name);
                list.add(ageAsString);
                list.add(power);
                list.add(weakness);
                payload.put("hero", list);
                final String query = "INSERT INTO hero (name,age,power,weakness) VALUES (:name,:age,:power,:weakness)";
                connection.createQuery(query)
                        .addParameter("name",name)
                        .addParameter("age",age)
                        .addParameter("power",power)
                        .addParameter("weakness",weakness)
                        .executeUpdate();
                return new ModelAndView(payload, "hero.hbs");
            }, new HandlebarsTemplateEngine());

            get("/hero/all", (request, response) -> {
                Map<String, Object> payload = new HashMap<>();
                final String query = "SELECT * FROM hero";
                List<Hero> heroes= connection.createQuery(query).executeAndFetch(Hero.class);
                payload.put("heroes", heroes);
                return new ModelAndView(payload, "all-hero.hbs");
            }, new HandlebarsTemplateEngine());
        }

        else{
            get("*", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                ModelAndView model = new ModelAndView(payload, "error.hbs");
                return engine.render(model);
            });
        }

    }
}