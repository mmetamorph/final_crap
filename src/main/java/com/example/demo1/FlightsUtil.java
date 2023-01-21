package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightsUtil {
    FlightsUtil() {

    }


    private static final String CREATE = "CREATE TABLE IF NOT EXISTS FLIGHTS(" +
            "CITY1 VARCHAR(30)," +
            "CITY2 VARCHAR(30)," +
            "DATE INTEGER," +
            "AMOUNT INTEGER)";


    public static void createTable() {
        try {
            JDBCUtil.getStatement().executeUpdate(CREATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String insert(Flights flight) {

        String INSERT_TABLE = "INSERT INTO FLIGHTS(CITY1, CITY2, DATE, AMOUNT) VALUES(" +
                "'" + flight.getCity1() + " ', '" +
                flight.getCity2() + "'," +
                flight.getDate() + ","
                + flight.getAmount() + ")";

        try {
            JDBCUtil.getStatement().executeUpdate(INSERT_TABLE);
            return "ინფორმაცია წარმატებით დაემატა";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<PieChart.Data> chart(){

        String SELECT = "SELECT CITY1, COUNT(*) AS COUNT FROM FLIGHTS GROUP BY CITY1";

        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();

        try{
            ResultSet result = JDBCUtil.getStatement().executeQuery(SELECT);

            while (result.next()){
                observableList.add(new PieChart.Data(result.getString("CITY1"), result.getInt("COUNT")));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return observableList;

    }
}