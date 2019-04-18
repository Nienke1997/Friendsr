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

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        final TextView text = findViewById(R.id.name1);
        TextView bio = findViewById(R.id.bio1);
        ImageView image = findViewById(R.id.image1);

        text.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        image.setImageResource(retrievedFriend.getDrawableId());

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(load(retrievedFriend.getName()));
        ratingBar.setOnRatingBarChangeListener(new MyRatingChangeListener());
    }
    private class MyRatingChangeListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            save(rating, retrievedFriend.getName());
        }
    }
    public void save(float rating, String text){
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(text, rating);
        editor.commit();
    }
    public float load(String text){

        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        float rating = sharedPreferences.getFloat(text, 0f);
        return rating;
    }
}
