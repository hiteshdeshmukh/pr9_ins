package com.example.pr9_ins.Model;

public class NotificationModel {

    int image1, image2;
    String name1, message1;

    public NotificationModel(int image1, int image2, String name1, String message1) {
        this.image1 = image1;
        this.image2 = image2;
        this.name1 = name1;
        this.message1 = message1;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }
}
