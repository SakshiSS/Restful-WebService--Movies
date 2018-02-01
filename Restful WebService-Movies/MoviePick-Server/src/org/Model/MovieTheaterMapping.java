package org.Model;



/**
 * Created by marne on 2/5/2017.
 */

public class MovieTheaterMapping {

    private String mname;
    private String tname;
    private int rating;
    private String address;
    private String genre;
    private String showTimes;

    public MovieTheaterMapping(){
        this.mname = null;
        this.tname = null;
        this.rating= 0;
        this.address = null;
        this.genre = null;
        this.showTimes = null;
    }

    public MovieTheaterMapping(String mname, String tname, int rating, String address, String genre, String showTimes){
        this.mname = mname;
        this.tname = tname;
        this.rating = rating;
        this.address = address;
        this.genre = genre;
        this.showTimes = showTimes;
    }

    public int getRating() {
        return rating;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(String showTimes) {
        this.showTimes = showTimes;
    }

}
