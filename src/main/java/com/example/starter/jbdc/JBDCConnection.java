package com.example.starter.jbdc;

import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JBDCConnection {
  public static class App {
    String userName = "postgres";
    String password = "12345";
    String url = "jdbc:postgresql://127.0.0.1:5432/jooq";

    public void connect() {
      try (Connection conn = DriverManager.getConnection(url, userName, password)) {
        System.out.println("Connected");
        DSLContext dslContext = DSL.using(conn, SQLDialect.POSTGRES);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

     public static void main(String[] args) {
      App app = new App();
      app.connect();

  }
}
