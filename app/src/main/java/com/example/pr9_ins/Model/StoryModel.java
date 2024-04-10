package com.example.pr9_ins.Model;

public class StoryModel {
    int homeStoryImage;
    String homeStoryName;


    public StoryModel(int homeStoryImage, String homeStoryName) {
        this.homeStoryImage = homeStoryImage;
        this.homeStoryName = homeStoryName;
    }

    public int getHomeStoryImage() {
        return homeStoryImage;
    }

    public void setHomeStoryImage(int homeStoryImage) {
        this.homeStoryImage = homeStoryImage;
    }

    public String getHomeStoryName() {
        return homeStoryName;
    }

    public void setHomeStoryName(String homeStoryName) {
        this.homeStoryName = homeStoryName;
    }
}
