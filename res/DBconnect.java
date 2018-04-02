package sample;


import jdk.nashorn.internal.codegen.CompilerConstants;
import sun.util.resources.cldr.sr.CalendarData_sr_Latn_BA;

import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.*;


/**
 * Created by HP on 25-Dec-16.
 */
public class DBconnect {

    private Connection conn = null;

    public DBconnect() {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String dbURL = "jdbc:oracle:thin:@localhost:1521:Oracle";
            String username = "scott";
            String password = "tiger";
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Connection successfully established." + "\n");
            } else {
                System.out.println("Could not establish connection" + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
    }


    public String getUser(String username, String password) {
        String query1 = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query1);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("USERNAME");
                return name;
            } else
                return "No user!!";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed!!";
    }



    public String setUser(String username,String password){
        String query2 = "INSERT INTO USERS VALUES (?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query2);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed!!";
    }



    public String setScore(String username,int point){
        String query3 = "INSERT INTO SCORES VALUES (?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query3);
            statement.setString(1, username);
            statement.setInt(2, point);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed!!";
    }


    public int getScore(String username, int point) {
        String query4 = "SELECT * FROM USERS WHERE USERNAME = ? AND POINT = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query4);
            statement.setString(1, username);
            statement.setInt(2, point);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("USERNAME");
                int score=resultSet.getInt(point);
                return point;
            } else
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
