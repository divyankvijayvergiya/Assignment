package com.teamvariance.teamvariance1.Model;

/**
 * Created by divyankvijayvergiya on 12/03/18.
 */

public class Items {
    private int id;
    private String image;
    private String category_name;
    private String created_at;
    private String updated_at;

    public Items(){

    }

    public Items(int id, String image, String category, String createdAt, String updatedAt){
        this.id = id;
        this.image = image;
        this.category_name = category;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category_name;
    }

    public void setCategory(String category) {
        this.category_name = category;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }
}
