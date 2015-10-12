package com.piquor.app.quiz.model;

public class Category extends BaseModel{

    private String category_name;
    private String logo_path;
    private String category_id;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String catrgory_id) {
        this.category_id = catrgory_id;
    }
}
