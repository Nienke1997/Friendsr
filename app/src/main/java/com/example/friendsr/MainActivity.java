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
        String[] bio = {"The youngest Stark girl and a wild, willful, but very intelligent child. ",
                "Queen of the realm and wife of Robert Baratheon. She despises Robert (as well as most other people it seems), and she is cunning and extremely ambitious.",
                "The Dothraki khaleesi (queen) and Targaryen princess. Mother of dragons.",
                "Jaime is arrogant, short-tempered, and rash, but he’s also a gifted swordsman. He is widely mistrusted and called Kingslayer because he murdered the previous king.",
                "Knows nothing.",
                "An exiled knight who serves unofficially as Daenerys’s chief advisor.",
                "Margaery Tyrell became Queen Consort through her marriage to Joffrey Baratheon, and later Tommen Baratheon.",
                "Melisandre is a Red Priestess. After the Battle of Winterfell, she revives Jon Snow believing him to be The Prince That Was Promised.",
                "The elder Stark daughter and a beautiful, but extremely naïve, young girl.",
                "I drink and I know things."};

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
