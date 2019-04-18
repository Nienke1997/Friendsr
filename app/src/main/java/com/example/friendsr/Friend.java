package com.example.friendsr;

import java.io.Serializable;

// Class of Friend, which consists of the name, the bio, and an image.
// The rating is set in profileactivity.
// You can get the name, bio, and image.
public class Friend implements Serializable {
    private String name, bio;
    private int drawableId;

    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
