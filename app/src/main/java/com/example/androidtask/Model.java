package com.example.androidtask;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("_id")
    private String id;
    @SerializedName("superhero_name")
    private String charName;
    @SerializedName("photo")
    private String photo;
    @SerializedName("name")
    private String name;
    public Model(String id, String charName,String photo,String name){
        this.id = id;
        this.charName = charName;
        this.photo = photo;
        this.name = name;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getCharName() {
        return charName;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
