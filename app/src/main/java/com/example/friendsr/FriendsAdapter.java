package com.example.friendsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<Friend>{
    // Create arraylist of class friends
    ArrayList<Friend> friends;

    //Create FriendsAdapter using arraylist friends as objects
    public FriendsAdapter(Context context, int resource, ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // if the app has not been started yet do this
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        // get the information of a friend in the Friend class (name, bio, image)
        Friend friend = friends.get(position);
        TextView text = convertView.findViewById(R.id.text);
        ImageView image = convertView.findViewById(R.id.image);

        text.setText(friend.getName());
        image.setImageResource(friend.getDrawableId());

        return convertView;
    }
}
