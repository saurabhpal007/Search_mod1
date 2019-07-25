package com.pal.thirstymission.serach_mod1;
import com.google.gson.annotations.SerializedName;
public class Users {


    @SerializedName("id") private int Id;
    @SerializedName("name") private String Name;
    @SerializedName("college") private String College;


    @SerializedName("branch") private String Branch;
    @SerializedName("start_year") private String Year;


    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCollege() {
        return College;
    }

    public String getBranch() {
        return Branch;
    }

    public String getYear() {
        return Year;
    }

}
