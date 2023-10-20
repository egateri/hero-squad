package com.moringaschool.student;

import com.moringaschool.student.config.DatabaseConfig;
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
         Connection connection = DatabaseConfig.getConnection();
//        Sql2o sql2o = DatabaseConfig.getDatabase();

       get("/hello",(request, response)->{
           HandlebarsTemplateEngine template = new HandlebarsTemplateEngine();
           Map<String, Object> payload = new HashMap<>();
           ModelAndView model = new ModelAndView(payload,"home.hbs");

//            return "Connection is OK!";
return template.render(model);


//  return "Hello World!";



       });

       get("/",(request,response)->{
           return "Home";
       });

        get("/test",(request, response)->{
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            Map<String, Object> payload = new HashMap<>();
            payload.put("test","test");
            ModelAndView model = new ModelAndView(payload,"mash.hbs");

            return engine.render(model);

            }
            );

        get("/home",(request, response)->{
            if(connection!=null){

                Map<String, Object> payload =new HashMap<>();
                List<String> list = new ArrayList<>();
                list.add("Eliud");
                list.add("Ann");
                list.add("Andrew");
                list.add("Ken");
                list.add("Kennedy");
                list.add("Peris");
                payload.put("user",list);
                return new ModelAndView(payload,"home.hbs");
            }
            else{

                return new ModelAndView(new HashMap<>(),"error.hbs");
            }


                },new HandlebarsTemplateEngine());

    }
}