package org.Business;

import javafx.scene.chart.PieChart;
import org.Model.MovieTheaterMapping;
import org.Model.Theater;
import org.Persistence.Database;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by marne on 2/6/2017.
 */
public class TheaterService {

    public ArrayList<Theater> getTheaters() throws Exception{

        ArrayList<Theater> theaterList = new ArrayList<Theater>();
        Database db = new Database();

        theaterList = Database.getTheaters();
        return theaterList;
    }

    public ArrayList<MovieTheaterMapping> getTheater(String theaterName) throws Exception{

        ArrayList<MovieTheaterMapping> theaterList = new ArrayList<MovieTheaterMapping>();
        Database db = new Database();

        theaterList = Database.getTheater(theaterName);
        return theaterList;
    }
}
