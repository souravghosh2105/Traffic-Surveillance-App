package com.example.surveillance;

public class Details {

    private String title;
    private String desc;
    private Float lat;
    private Float lon;
    private String image;
    private String start_time;
    private String end_time;
    private String vnum;
    private String name;
    private String number;
    private String address;

    public Details(String title, String desc, String image, Float lat, Float lon,String start_time,String end_time,String vnum,String name,String number,String address) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
        this.start_time = start_time;
        this.end_time = end_time;
        this.vnum = vnum;
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon(){
        return lon;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getVnum() {
        return vnum;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }



    public Details(){

    }

}
