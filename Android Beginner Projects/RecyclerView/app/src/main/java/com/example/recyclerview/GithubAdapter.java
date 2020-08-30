package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.PriorityQueue;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder> {

    private Context context;
    private User[] data;
public GithubAdapter(Context context, User[] data){
    this.context = context;
    this.data = data;
}

    @NonNull
    @Override
    public GithubAdapter.GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user_layout,parent, false);
    return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GithubAdapter.GithubViewHolder holder, int position) {
final User user = data[position];
holder.txtUser.setText(user.getLogin());
        Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               holder.txtUser.setText(user.getLogin());
               Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
               int pos;




            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class GithubViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView txtUser;
        public GithubViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = (ImageView) itemView.findViewById(R.id.imgUser);
            txtUser = (TextView) itemView.findViewById(R.id.txtUser);
        }
    }
}

