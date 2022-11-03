package com.example.tasks.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tasks.R;
import com.example.tasks.service.model.PriorityModel;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.viewmodel.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private ViewHolder mViewHolder = new ViewHolder();
    private TaskViewModel mViewModel;
    private List<Integer> mListPriorityId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // Botão de voltar nativo
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.mViewHolder.textDescription = findViewById(R.id.text_description);
        this.mViewHolder.spinnerPriority = findViewById(R.id.spin_priority);
        this.mViewHolder.checkComplete = findViewById(R.id.check_completed);
        this.mViewHolder.btnDate = findViewById(R.id.btn_date);
        this.mViewHolder.btnSave = findViewById(R.id.btn_save);

        // ViewModel
        this.mViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        this.listeners();
        // Cria observadores
        this.loadObservers();
        this.mViewModel.getList();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_date:
                this.showDatePicker();
                break;
            case R.id.btn_save:
                this.handlerSave();
                break;
        }
    }

    private void handlerSave() {
        TaskModel task = new TaskModel();

        task.setDescription(this.mViewHolder.textDescription.getText().toString());
        task.setComplete(this.mViewHolder.checkComplete.isChecked());
        task.setDueDate(this.mViewHolder.btnDate.getText().toString());
        task.setPriorityId(this.mListPriorityId.get(this.mViewHolder.spinnerPriority.getSelectedItemPosition()));

        this.mViewModel.save(task);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        String date = this.mFormat.format(c.getTime());
        this.mViewHolder.btnDate.setText(date);
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();
        int year =  calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this,this, year,month,day).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void listeners() {
        this.mViewHolder.btnDate.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);
    }


    private void loadObservers() {
        this.mViewModel.listPriority.observe(this, new Observer<List<PriorityModel>>() {
            @Override
            public void onChanged(List<PriorityModel> list) {
                loadSpinner(list);
            }
        });
    }

    private void loadSpinner(List<PriorityModel> list){

        List<String> listPriority = new ArrayList<>();
        for(PriorityModel l : list) {
            listPriority.add(l.getDescription());
            mListPriorityId.add(l.getId());
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, listPriority);
        this.mViewHolder.spinnerPriority.setAdapter(adapter);
    }

    private static class ViewHolder {
        EditText textDescription;
        Spinner spinnerPriority;
        CheckBox checkComplete;
        Button btnDate;
        Button btnSave;
    }
}