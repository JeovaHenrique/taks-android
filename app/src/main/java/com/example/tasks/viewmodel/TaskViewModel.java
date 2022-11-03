package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.service.listener.APIListeners;
import com.example.tasks.service.listener.FeedBack;
import com.example.tasks.service.model.PriorityModel;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.service.repository.PriorityRepository;
import com.example.tasks.service.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private PriorityRepository mPriorityRepository;
    private TaskRepository mTaskRepository;

    private MutableLiveData<List<PriorityModel>> mListPriority = new MutableLiveData<>();
    public LiveData<List<PriorityModel>> listPriority = this.mListPriority;

    private MutableLiveData<FeedBack> mTaskSave = new MutableLiveData<>();
    public LiveData<FeedBack> taskSave = this.mTaskSave;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.mPriorityRepository = new PriorityRepository(application);
    }

    public void getList() {
        List<PriorityModel> list = this.mPriorityRepository.getList();
        this.mListPriority.setValue(list);
    }

    public void save(TaskModel task) {
        this.mTaskRepository.save(task, new APIListeners<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                mTaskSave.setValue(new FeedBack());
            }

            @Override
            public void onFailure(String message) {
                mTaskSave.setValue(new FeedBack(message));
            }
        });
    }
}
