package com.moringaschool.student;

import com.moringaschool.student.config.DBConfig;
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


       get("/",(request, response)->{
           HandlebarsTemplateEngine template = new HandlebarsTemplateEngine();
           Map<String, Object> payload = new HashMap<>();
           ModelAndView model = new ModelAndView(payload,"home.hbs");
           return template.render(model);});

        get("/hero",(request, response)->{
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            Map<String, Object> payload = new HashMap<>();
            payload.put("hero","hero:Spider Man");
            ModelAndView model = new ModelAndView(payload,"hero.hbs");
            return engine.render(model);});

        get("/squad",(request, response)->{
                Map<String, Object> payload =new HashMap<>();
                List<String> list = new ArrayList<>();
                list.add("id:1");
                list.add("Squad One");
                list.add("Size: 10");
                list.add("Cause");
                payload.put("squad",list);
                return new ModelAndView(payload,"squad.hbs");
                },new HandlebarsTemplateEngine());

    }
}