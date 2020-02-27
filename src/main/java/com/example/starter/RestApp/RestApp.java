package com.example.starter.RestApp;

import com.example.starter.model.Person;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RestApp extends AbstractVerticle {

  //Model
  private static Map<String, Person> persons;


  @Override
  public void start() {
    persons = new HashMap<>();
    persons.put(UUID.randomUUID().toString(), new Person("abc", 22));
    persons.put(UUID.randomUUID().toString(), new Person("abcd", 224));
    persons.put(UUID.randomUUID().toString(), new Person("abcdef", 2243));
    Router router = Router.router(Vertx.vertx());
    router.get("/person").handler(this::all);
    Vertx.vertx().createHttpServer().requestHandler(router::accept).listen(8080);
  }

  private void all(RoutingContext context) {
    context.response()
      .setStatusCode(200)
      .putHeader("content-type", "application/json; charset=Utf-8")
      .end(Json.encode(persons));
  }

  public static void main(String[] args) {
    new RestApp().start();
  }
}
