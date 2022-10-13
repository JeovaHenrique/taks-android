package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.google.gson.Gson;

import okhttp3.ResponseBody;

public class BaseRepository {

    Context mContext;

    public BaseRepository(Context context) {
        this.mContext = context;
    }

    public String handlerFailure(ResponseBody response) {
        try {
            return new Gson().fromJson(response.string(),String.class);
        } catch (Exception e) {
            return mContext.getString(R.string.ERROR_UNEXPECTED);
        }
    }
}
