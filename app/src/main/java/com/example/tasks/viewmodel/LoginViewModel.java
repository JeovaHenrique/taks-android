package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.service.listener.APIListeners;
import com.example.tasks.service.listener.FeedBack;
import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.repository.PersonRepository;

public class LoginViewModel extends AndroidViewModel {

    private PersonRepository mPersonRepository;

    private MutableLiveData<FeedBack> mLogin = new MutableLiveData<>();
    public LiveData<FeedBack> login = this.mLogin;

    private MutableLiveData<Boolean> mUserLogged = new MutableLiveData<>();
    public LiveData<Boolean> userLogged = this.mUserLogged;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.mPersonRepository = new PersonRepository(application);
    }

    public void login(String email, String password) {
        this.mPersonRepository.login(email, password, new APIListeners<PersonModel>() {
            @Override
            public void onSuccess(PersonModel result) {

                mPersonRepository.saveUserData(result);

                mLogin.setValue(new FeedBack());
            }

            @Override
            public void onFailure(String message) {
                mLogin.setValue(new FeedBack(message));
            }
        });

    }

    public void verifyUserLogged() {
        PersonModel model = this.mPersonRepository.getUserData();

        this.mUserLogged.setValue((!"".equals(model.getName())));
    }
}
