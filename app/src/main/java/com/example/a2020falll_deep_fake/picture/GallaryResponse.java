package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GallaryResponse {
    @SerializedName("len")
    private int len;

    @SerializedName("rids")
    private List<Integer> rids;

    @SerializedName("hairs")
    private List<String> hairs;

    @SerializedName("progresses")
    private List<Integer> progresses;

    @SerializedName("status")
    private String status;

    public int get_len() { return this.len; }
    public List<Integer> get_rids() { return this.rids; }
    public List<String> get_hairs() {
        return this.hairs;
    }
    public List<Integer> get_progress() { return this.progresses; }
    public String get_status() {
        return this.status;
    }

    public Boolean check_status() {
        String pass = "OK";
        if (status.equals(pass)){
            return true;
        } else{
            return false;
        }
    }

}
