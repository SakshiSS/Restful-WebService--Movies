package org.Persistence;

import org.Model.Movie;
import org.Model.MovieTheaterMapping;
import org.Model.Theater;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marne on 2/4/2017.
 */
public class Database {


    private static Map<String, Movie> movies;
    //private static Map<String,MovieTheaterMapping> mtMap;
    private static List<MovieTheaterMapping> listTheaters;

    public static final String url = "jdbc:mysql://uml.cs.uga.edu:3306/team4";
    public static final String uname = "team4";
    public static final String passwd = "dpatterns";

    public Database() {

    }

//    public static Map<String,ArrayList<Movie>> getMovies(){
//        return movies;
//    }


    public static Map<String, Movie> getMoviesDB() {

        movies = new HashMap<String, Movie>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, passwd);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  Movie");

            Movie movie;
            while (resultSet.next()) {
                movie = new Movie();
                movie.setMovieName(resultSet.getString(1));
                movie.setRating(resultSet.getInt(2));
                movie.setGenre(resultSet.getString(3));

                movies.put(movie.getMovieName(), movie);

            }

            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while connecting to database");
        }

        return movies;

    }

    public static Map<String, ArrayList<MovieTheaterMapping>> getMovie(String movieName) {
        Map<String, ArrayList<MovieTheaterMapping>> movies = new HashMap<String, ArrayList<MovieTheaterMapping>>();
        MovieTheaterMapping mtMapping;
        ArrayList<MovieTheaterMapping> movieList;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, passwd);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT M.name,M.rating,T.tname,T.address,MT.showTimings, M.genre FROM Movie M, Theater T , MovieTheater MT" +
                    " WHERE MT.mname= M.name AND T.tname = MT.tname AND M.name='" + movieName + "';");

            while (resultSet.next()) {
                mtMapping = new MovieTheaterMapping();
                mtMapping.setMname(resultSet.getString("name"));
                mtMapping.setRating(resultSet.getInt("rating"));
                mtMapping.setTname(resultSet.getString("tname"));
                mtMapping.setAddress(resultSet.getString("address"));
                mtMapping.setShowTimes(resultSet.getString("showTimings"));
                mtMapping.setGenre(resultSet.getString("genre"));
                if (movies.containsKey(mtMapping.getMname())) {
                    movies.get(mtMapping.getMname()).add(mtMapping);
                } else {
                    movieList = new ArrayList<MovieTheaterMapping>();
                    movieList.add(mtMapping);
                    movies.put(mtMapping.getMname(), movieList);
                }
            }


        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error while retrieving the movie with given name");
        }
        return movies;
    }

    public static List<MovieTheaterMapping> getTheatersGivenMovie(String movieName) {
        listTheaters = new ArrayList<MovieTheaterMapping>();

        try {

            MovieTheaterMapping mtMapping;
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, passwd);

            Statement statement = connection.createStatement();
            String query = "Select M.name, T.tname, T.address, MT.showTimings, M.rating, M.genre from " +
                    "Movie M, Theater T, MovieTheater MT where MT.mname = M.name and MT.tname=T.tname " +
                    " and M.name = '" + movieName + "';";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                mtMapping = new MovieTheaterMapping();
                mtMapping.setMname(resultSet.getString(1));
                mtMapping.setTname(resultSet.getString(2));
                mtMapping.setAddress(resultSet.getString(3));
                mtMapping.setShowTimes(resultSet.getString(4));
                mtMapping.setRating(resultSet.getInt(5));
                mtMapping.setGenre(resultSet.getString(6));

                //mtMap.put(mtMapping.getMname(),mtMapping);
                listTheaters.add(mtMapping);

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some error while getting theaters list for a given movie");
        }

        return listTheaters;

    }


    public static List<Movie> getMoviesWithGenre(String genre) {
        List<Movie> listMovies = new ArrayList<Movie>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, passwd);
            Statement statement = connection.createStatement();
            String query = "Select * from Movie where genre='" + genre + "';";
            ResultSet resultSet = statement.executeQuery(query);
            Movie movie;
            while (resultSet.next()) {
                movie = new Movie();
                movie.setMovieName(resultSet.getString(1));
                movie.setRating(resultSet.getInt(2));
                movie.setGenre(resultSet.getString(3));

                listMovies.add(movie);
            }
        } catch (Exception e) {
            System.out.println("Error while retrieving movies with givnen genre");

        }
        return listMovies;
    }

    //one more method for rating


    public static ArrayList<Theater> getTheaters() throws Exception {
        ArrayList<Theater> theaterList = new ArrayList<Theater>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, passwd);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Theater");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Theater theaterObj = new Theater();
                theaterObj.setTname(rs.getString("tname"));
                theaterObj.setAddress(rs.getString("address"));
                theaterList.add(theaterObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theaterList;
    }

    public static ArrayList<MovieTheaterMapping> getTheater(String theaterName) throws Exception {
        ArrayList<MovieTheaterMapping> theaterList = new ArrayList<MovieTheaterMapping>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, passwd);
            PreparedStatement stmt = connection.prepareStatement("Select  T.tname, T.address, M.name, MT.showTimings, M.rating, M.genre from " +
                    " Movie M, Theater T, MovieTheater MT where MT.mname = M.name and MT.tname=T.tname " +
                    "  and T.tname = '" + theaterName+ "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MovieTheaterMapping mtObj = new MovieTheaterMapping();
                mtObj.setTname(rs.getString(1));
                mtObj.setAddress(rs.getString(2));
                mtObj.setMname(rs.getString(3));
                mtObj.setShowTimes(rs.getString(4));
                mtObj.setRating(rs.getInt(5));
                mtObj.setGenre(rs.getString(6));

                theaterList.add(mtObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theaterList;
    }



    public static  List<Movie> getMovieByRating(int rating) throws ClassNotFoundException, SQLException{
        List<Movie> l1=new ArrayList<Movie>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,uname,passwd);
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from Movie where rating="+rating);
        while(rs.next()) {
            Movie m1=new Movie();
            m1.setMovieName(rs.getString("name"));
            m1.setGenre(rs.getString("genre"));
            m1.setRating(rating);
            l1.add(m1);

        }//while
        return l1;

    }//rating


    public static int getRatingByMovie(String movieName) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,uname,passwd);
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select rating from Movie where name='"+movieName+"'");
        int rating=0;
        while(rs.next()) {
            rating=rs.getInt("rating");
        }//while
        System.out.println("ratng"+rating);
        return rating;

    }//


    public static Movie updateMovieRating(String movieName,int rating) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,uname,passwd);
        String query = "update Movie set rating= ? where name = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setInt(1,rating);
        preparedStmt.setString(2,movieName);
        int i = preparedStmt.executeUpdate();
        Movie m=new Movie();
        if(i == 1) {
            m.setMovieName(movieName);
            m.setRating(rating);
            return m;
        }
        else{
            return null;
        }

    }//upate



}
