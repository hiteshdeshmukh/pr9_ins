package com.example.pr9_ins.Model;

public class HighlightModel {
    int profileHighlightImage;
    String profileHighlightName;

    public HighlightModel(int profileHighlightImage, String profileHighlightName) {
        this.profileHighlightImage = profileHighlightImage;
        this.profileHighlightName = profileHighlightName;
    }

    public int getProfileHighlightImage() {
        return profileHighlightImage;
    }

    public void setProfileHighlightImage(int profileHighlightImage) {
        this.profileHighlightImage = profileHighlightImage;
    }

    public String getProfileHighlightName() {
        return profileHighlightName;
    }

    public void setProfileHighlightName(String profileHighlightName) {
        this.profileHighlightName = profileHighlightName;
    }
}
