package com.example.laba15.domain.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba15.databinding.UserPageItemBinding;
import com.example.laba15.domain.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserPagerAdapter extends RecyclerView.Adapter<UserPagerAdapter.ViewHolder> {

    ArrayList<User> userArrayList = new ArrayList<>();

    public UserPagerAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserPageItemBinding binding = UserPageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPagerAdapter.ViewHolder holder, int position) {
        holder.binding.userName.setText(userArrayList.get(position).getUserName());
        Log.d("USER_IMAGE_URL", userArrayList.get(position).getUserImage());
        Picasso.get()
                .load(userArrayList.get(position).getUserImage())
                .resize(200, 200)
                .into(holder.binding.userPicture);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public void updateData(ArrayList<User> newUsers) {
        this.userArrayList = newUsers;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        UserPageItemBinding binding;
        public ViewHolder(@NonNull UserPageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
