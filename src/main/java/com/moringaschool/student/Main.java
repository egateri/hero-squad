package com.moringaschool.student;

import com.moringaschool.student.config.DatabaseConfig;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {


    public static void main(String[] args) {
         Connection connection = DatabaseConfig.getConnection();
//        Sql2o sql2o = DatabaseConfig.getDatabase();

       get("/hello",(request, response)->{
           HandlebarsTemplateEngine template = new HandlebarsTemplateEngine();
           Map<String, Object> payload = new HashMap<>();
           ModelAndView model = new ModelAndView(template,"home.hbs");

            return "Connection is OK!";



//  return "Hello World!";

//           try(Connection connection = sql2o.open()){
//
//
//               String msg = connection.toString();
//
//               System.out.println("SUCCESS "+msg);
//
//               return msg;
//
//           }
//           catch (Exception exception){
//
//               System.out.println("ERROR "+exception.getLocalizedMessage());
//
//               return exception.getLocalizedMessage();
//
//           }

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

            return new ModelAndView(new HashMap<>(),"home.hbs");

                },new HandlebarsTemplateEngine());

    }
}