package com.example.submission3_zulkaranen.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;
/**
 * @author zulkarnaen
 */
public class TvShow implements Parcelable {

    private int id;
    private boolean isOnfavorites;

    private String original_name;
    private String popularity;
    private String vote_count;
    private String vote_average;
    private String first_air_date;
    private String poster_path;
    private String overview;


    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOnfavorites() {
        return isOnfavorites;
    }

    public void setOnfavorites(boolean onfavorites) {
        isOnfavorites = onfavorites;
    }

    public static Creator<TvShow> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original_name);
        dest.writeString(this.popularity);
        dest.writeString(this.vote_count);
        dest.writeString(this.vote_average);
        dest.writeString(this.first_air_date);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeByte(this.isOnfavorites ? (byte) 1 : (byte) 0);
    }

    public TvShow() {
    }

    public TvShow(JSONObject object) {
        try {
            String original_names = object.getString("original_name");
            String popularitys = object.getString("popularity");
            String vote_counts = object.getString("vote_count");
            String vote_averages = object.getString("vote_average");
            String first_air_dates = object.getString("first_air_date");
            String poster_path = object.getString("poster_path");
            String overview = object.getString("overview");

            this.original_name = original_names;
            this.popularity = popularitys;
            this.vote_average = vote_averages;
            this.vote_count = vote_counts;
            this.first_air_date = first_air_dates;
            this.poster_path = poster_path;
            this.overview = overview;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected TvShow(Parcel in) {
        this.original_name = in.readString();
        this.popularity = in.readString();
        this.vote_count = in.readString();
        this.vote_average = in.readString();
        this.first_air_date = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.isOnfavorites = in.readByte() != 0;
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
