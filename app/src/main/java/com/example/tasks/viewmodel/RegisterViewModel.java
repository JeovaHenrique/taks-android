package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tasks.service.repository.PersonRepository;

public class RegisterViewModel extends AndroidViewModel {

    private PersonRepository mPersonRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.mPersonRepository = new PersonRepository(application);
    }

    public void create(String name, String email, String password) {
        this.mPersonRepository.create(name,email,password);

    }
}
