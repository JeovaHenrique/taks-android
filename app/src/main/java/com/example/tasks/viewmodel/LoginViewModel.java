package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.service.listener.APIListeners;
import com.example.tasks.service.listener.FeedBack;
import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.model.PriorityModel;
import com.example.tasks.service.repository.PersonRepository;
import com.example.tasks.service.repository.PriorityRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private PersonRepository mPersonRepository;
    private PriorityRepository mPriorityRepository;

    private MutableLiveData<FeedBack> mLogin = new MutableLiveData<>();
    public LiveData<FeedBack> login = this.mLogin;

    private MutableLiveData<Boolean> mUserLogged = new MutableLiveData<>();
    public LiveData<Boolean> userLogged = this.mUserLogged;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.mPersonRepository = new PersonRepository(application);
        this.mPriorityRepository = new PriorityRepository(application);
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

        boolean logger = !"".equals(model.getName());
        if(!logger) {
            this.mPriorityRepository.all(new APIListeners<List<PriorityModel>>() {
                @Override
                public void onSuccess(List<PriorityModel> result) {
                    mPriorityRepository.save(result);
                }

                @Override
                public void onFailure(String message) {

                }
            });
        }

        this.mUserLogged.setValue(logger);
    }
}
