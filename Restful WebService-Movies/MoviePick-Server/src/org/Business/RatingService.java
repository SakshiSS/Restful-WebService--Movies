package org.Business;

import org.Model.Movie;
import org.Persistence.Database;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by marne on 2/6/2017.
 */
public class RatingService {

    public List<Movie> findMovieByRating(int rating) throws ClassNotFoundException, SQLException {

        List<Movie> res= Database.getMovieByRating(rating);
        return res;

    }//Rating


    public int findRatingByMovie(String movieName) throws ClassNotFoundException, SQLException{
        int rating = Database.getRatingByMovie(movieName);
        return rating;

    }//Rating


    public Movie updateMovie(String movieName,int rating) throws ClassNotFoundException, SQLException{

        Movie m=Database.updateMovieRating(movieName,rating);
        return m;

    }//update
}
