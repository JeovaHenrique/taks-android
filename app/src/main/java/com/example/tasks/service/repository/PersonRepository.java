package com.example.tasks.service.repository;

import static com.example.tasks.service.constants.TaskConstants.SHARED.PERSON_KEY;
import static com.example.tasks.service.constants.TaskConstants.SHARED.PERSON_NAME;
import static com.example.tasks.service.constants.TaskConstants.SHARED.TOKEN_KEY;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListeners;
import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.repository.local.SecurityPreferences;
import com.example.tasks.service.repository.remote.PersonService;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository extends BaseRepository  {

    private PersonService mPersonService;
    private SecurityPreferences mSecurityPreferences;

    public PersonRepository(Context context) {
        super(context);
        this.mPersonService = RetrofitClient.createService(PersonService.class);
        this.mContext = context;
        this.mSecurityPreferences = new SecurityPreferences(context);
    }

    public void create(String name, String email, String password, final APIListeners<PersonModel> listeners) {
        Call<PersonModel> call = this.mPersonService.create(name, email, password, true);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                        listeners.onSuccess(response.body());
                    }else {
                        listeners.onFailure(handlerFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                listeners.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void login(String email, String password, final APIListeners<PersonModel> listeners) {
        Call<PersonModel> call = this.mPersonService.login(email, password);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listeners.onSuccess(response.body());
                }else {
                    listeners.onFailure(handlerFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                listeners.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void saveUserData(PersonModel model) {
        this.mSecurityPreferences.storedString(TOKEN_KEY, model.getToken());
        this.mSecurityPreferences.storedString(PERSON_KEY,model.getPersonKey());
        this.mSecurityPreferences.storedString(PERSON_NAME,model.getName());

        RetrofitClient.saveHeaders(model.getToken(), model.getPersonKey());
    }

    public PersonModel getUserData() {
        PersonModel model = new PersonModel();

        model.setToken(this.mSecurityPreferences.getStoredString(TOKEN_KEY));
        model.setName(this.mSecurityPreferences.getStoredString(PERSON_NAME));
        model.setPersonKey(this.mSecurityPreferences.getStoredString(PERSON_KEY));
        return model;
    }
}
