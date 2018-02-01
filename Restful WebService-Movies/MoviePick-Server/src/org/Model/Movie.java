package org.Model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marne on 2/4/2017.
 */

public class Movie {

    private String movieName;
    private int rating;
    private String genre;
//    private String tname;
//    private String taddress;
//    private String showTimings;


    public Movie(){
        this.movieName = null;
        this.genre = null;
        this.rating = 0;
//        this.tname = null;
//        this.taddress = null;
//        this.showTimings = null;

    }

    public Movie(String movieName, int rating, String genre){
        this.movieName = movieName;
        this.rating = rating;
        this.genre = genre;
//        this.taddress = taddress;
//        this.tname = tname;
//        this.showTimings = showTimings;
    }











    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }






}
