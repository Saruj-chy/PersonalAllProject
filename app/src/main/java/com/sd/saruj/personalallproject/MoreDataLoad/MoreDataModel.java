package com.sd.saruj.personalallproject.MoreDataLoad;

import java.lang.reflect.Field;

public class MoreDataModel {

    String userId, id, title,body;

    public MoreDataModel() {
    }
    public Field[] getAllFields(){
        return this.getClass().getDeclaredFields() ;
    }


    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
