package com.example.laba15.domain.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.laba15.domain.models.User;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> _users = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<User>> users() {
        return _users;
    }

    public MainViewModel() throws IOException {
        downloadUser(2);
    }

    public void downloadUser(int amount) throws IOException {
        ArrayList<User> currentUsers = _users.getValue();
        for(int i = 0; i <= amount; i++) {
            currentUsers.add(new User("User #" + currentUsers.size(), "https://api.dicebear.com/8.x/lorelei/png?seed=" + generateRandomString(10)));
        }
        _users.setValue(currentUsers);
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        String characters = "abcdefghijklmnopqrstuvwxyz"; // символы, из которых будет состоять строка

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
