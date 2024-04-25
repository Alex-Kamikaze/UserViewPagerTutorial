package com.example.laba15.ui.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.laba15.R;
import com.example.laba15.databinding.ActivityMainBinding;
import com.example.laba15.domain.adapters.UserPagerAdapter;
import com.example.laba15.domain.models.User;
import com.example.laba15.domain.viewmodels.MainViewModel;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        ArrayList<User> users = viewModel.users().getValue();
        if(users == null) {
            users = new ArrayList<>();
        }
        UserPagerAdapter adapter = new UserPagerAdapter(users);
        binding.userPager.setAdapter(adapter);
        binding.userPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewModel.users().observe(this, newUsers -> {
            adapter.updateData(newUsers);
            adapter.notifyDataSetChanged();
            binding.userPager.requestTransform();
        });
        binding.userPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                try {
                    viewModel.downloadUser(1);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Не удалось загрузить следующий профиль пользователя", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}