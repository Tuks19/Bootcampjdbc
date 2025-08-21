package org.example;

import java.sql.*;

public class testjdbc {
    public static void main(String[] args) throws SQLException {
        Connection cont = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Market", "postgres","1111");
        try (Statement sta = cont.createStatement()){
            ResultSet rs = sta.executeQuery("SELECT * from cliente");
            while (rs.next()){
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                System.out.println("Cliente: " + nombre + " " + apellido);
            }
        }
    }
}
