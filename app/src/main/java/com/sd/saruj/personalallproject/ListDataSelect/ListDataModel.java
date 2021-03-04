package com.sd.saruj.personalallproject.ListDataSelect;

import java.lang.reflect.Field;

public class ListDataModel {
    public String username, phone ;

    public String image ;

    public ListDataModel() {
    }

    public Field[] getAllFields(){
        return this.getClass().getDeclaredFields() ;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }
}
