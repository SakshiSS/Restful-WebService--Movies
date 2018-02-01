package org.Business;

import javafx.scene.chart.PieChart;
import org.Model.Movie;
import org.Model.MovieTheaterMapping;
import org.Model.Theater;
import org.Persistence.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marne on 2/4/2017.
 */
public class MovieService {



    public MovieService(){
//        movies.put("Movie1",new Movie("Movie1",0,"Comedy"));
//        movies.put("Movie2",new Movie("Movie2",0,"Romcom"));
//        movies.put("Movie3",new Movie("Movie3",0,"Action"));
//        movies.put("Movie4",new Movie("Movie4",0,"Horror"));
//        movies.put("Movie5",new Movie("Movie5",0,"Suspence"));
//        movies.put("Movie6",new Movie("Movie6",0,"Family"));
    }

    public List<Movie> getMovies(){
        //return new ArrayList<Movie>(movies.values());

        List<Movie> moviesList = new ArrayList<Movie>(Database.getMoviesDB().values());
//        Map<String,ArrayList<MovieTheaterMapping>> moviesMap = new HashMap<String, ArrayList<MovieTheaterMapping>>(Database.getMoviesDB());
//        System.out.println("The size of the list of movies is "+moviesMap.size());
        return moviesList;

    }

    public Map<String,ArrayList<MovieTheaterMapping>> getMovie(String movieName){
        //List<Movie> movieList = new ArrayList<Movie>(Database.getMovie(movieName).values());
        Map<String,ArrayList<MovieTheaterMapping>> moviesMap = new HashMap<String, ArrayList<MovieTheaterMapping>>(Database.getMovie(movieName));
        return moviesMap;
    }

    public List<MovieTheaterMapping> getTheatersGivenMovie(String movieName){
        List<MovieTheaterMapping> mtDetails = new ArrayList<MovieTheaterMapping>(Database.getTheatersGivenMovie(movieName));



        return mtDetails;
    }

    public List<Movie> getMoviesWithGenre(String genre){
        List<Movie> movieList = new ArrayList<Movie>(Database.getMoviesWithGenre(genre));
        return movieList;
    }


}
