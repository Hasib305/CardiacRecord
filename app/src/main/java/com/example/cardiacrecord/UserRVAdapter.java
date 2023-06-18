package com.example.cardiacrecord;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {
    // creating variables for our list, context, interface and position.
    private ArrayList<UserRVModal> userRVModalArrayList;
    private Context context;
    private UserClickInterface userClickInterface;
    int lastPos = -1;

    // creating a constructor.
    public UserRVAdapter(ArrayList<UserRVModal> userRVModalArrayList, Context context, UserClickInterface userClickInterface) {
        this.userRVModalArrayList = userRVModalArrayList;
        this.context = context;
        this.userClickInterface = userClickInterface;
    }

    @NonNull
    @Override
    public UserRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.user_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
//    public void onBindViewHolder(@NonNull UserRVAdapter.ViewHolder holder, int position) {
//        // setting data to our recycler view item on below line.
//        UserRVModal userRVModal = userRVModalArrayList.get(position);
//        holder.userNameTV.setText(userRVModal.getUserName());
//        holder.heartTV.setText(userRVModal.getUserheart());
//        holder.diostolicTV.setText(userRVModal.getUserdio() + "mm(Hg)");
//        holder.systolicTV.setText(userRVModal.getUsersys() + "mm (Hg)");
//        holder.time.setText(userRVModal.getUsertime());
//        holder.date.setText(userRVModal.getUserdate());
//        holder.cmnt.setText(userRVModal.getUserDesc());
//
//        // adding animation to recycler view item on below line.
//        setAnimation(holder.itemView, position);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userClickInterface.onUserClick(position);
//            }
//        });
//    }

    public void onBindViewHolder(@NonNull UserRVAdapter.ViewHolder holder, int position) {
        // setting data to our recycler view item on below line.
        UserRVModal userRVModal = userRVModalArrayList.get(holder.getAdapterPosition());
        holder.userNameTV.setText(userRVModal.getUserName());
        holder.heartTV.setText(userRVModal.getUserheart());
        holder.diostolicTV.setText(userRVModal.getUserdio() + "mm(Hg)");
        holder.systolicTV.setText(userRVModal.getUsersys() + "mm (Hg)");
        holder.time.setText(userRVModal.getUsertime());
        holder.date.setText(userRVModal.getUserdate());
        holder.cmnt.setText(userRVModal.getUserDesc());

        // adding animation to recycler view item on below line.
        setAnimation(holder.itemView, holder.getAdapterPosition());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClickInterface.onUserClick(holder.getAdapterPosition());
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            // on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return userRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variable for our image view and text view on below line.
        //private ImageView userIV;
        private TextView userNameTV, cmnt, systolicTV,diostolicTV, heartTV,time,date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing all our variables on below line.
            userNameTV = itemView.findViewById(R.id.textName);
            systolicTV = itemView.findViewById(R.id.textSystolicPressure);
            diostolicTV = itemView.findViewById(R.id.textDiastolicPressure);
            heartTV = itemView.findViewById(R.id.textHeartRate);
            cmnt = itemView.findViewById(R.id.textComment);
            date = itemView.findViewById(R.id.textDate);
            time = itemView.findViewById(R.id.textTime);
        }
    }

    // creating a interface for on click
    public interface UserClickInterface {
        void onUserClick(int position);
    }
}