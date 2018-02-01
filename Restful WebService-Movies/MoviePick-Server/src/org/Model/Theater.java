package org.Model;



/**
 * Created by marne on 2/4/2017.
 */

public class Theater {

    private String tname;
    private String address;
    private String showTimes;

    public Theater(){
        this.tname = null;
        this.address= null;
        this.showTimes = null;
    }

    public Theater(String tname,String address, String showTimes){
        this.tname = tname;
        this.address = address;
        this.showTimes = showTimes;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(String showTimes) {
        this.showTimes = showTimes;
    }






}
