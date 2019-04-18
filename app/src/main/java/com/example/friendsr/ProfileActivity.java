package com.example.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Friend retrievedFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // identify to which friend (the clicked friend) you want to go
        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // get the name, bio and image of the friend.
        final TextView text = findViewById(R.id.name1);
        TextView bio = findViewById(R.id.bio1);
        ImageView image = findViewById(R.id.image1);

        // set the name, bio and image of friend.
        text.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        image.setImageResource(retrievedFriend.getDrawableId());

        // Identify the rating bar, and save it specifically for that friend in particular.
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(load(retrievedFriend.getName()));
        ratingBar.setOnRatingBarChangeListener(new MyRatingChangeListener());
    }
    private class MyRatingChangeListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        // when a rating is changed, save it for the specific friend.
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            save(rating, retrievedFriend.getName());
        }
    }
    // save the sharedPreferences with the rating that is given to the person in particular
    public void save(float rating, String text){
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(text, rating);
        editor.commit();
    }
    // get the rating to save.
    public float load(String text){

        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        float rating = sharedPreferences.getFloat(text, 0f);
        return rating;
    }
}
