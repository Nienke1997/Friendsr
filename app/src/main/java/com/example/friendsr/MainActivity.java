package com.example.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Friend> friends = new ArrayList<>();

        // create a list of the names of the persons
        String[] names = {"Arya", "Cersei", "Daenerys", "Jaime", "Jon", "Jorah", "Margaery",
                "Melisandre", "Sansa", "Tyrion"};
        // create a list of the bios of the persons
        String[] bio = {"", "", "", "", "", "", "", "", "", ""};

        int counter = 0;
        // for each name in the list of names, add to the arraylist friends with bio and image
        for (String name:names){
            int id = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
            Friend friend = new Friend (name, bio[counter], id);
            friends.add(friend);
            counter++;
        }
        FriendsAdapter adapter = new FriendsAdapter(getApplicationContext(), R.layout.grid_item, friends);

        // Identify the gridView that we want to use the adapter on, and add the setOnItemClickListener to it.
        GridView grid = findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new GridItemClickListener());

    }

    //class that reacts to an item on a grid being clicked
    private class GridItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Identify the friend that is clicked
            parent.getItemAtPosition(position);
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            // identifies where the intent is and where they want to go (ProfileActivity)
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }


}
