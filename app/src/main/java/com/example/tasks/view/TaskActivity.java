package com.example.tasks.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tasks.R;
import com.example.tasks.viewmodel.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private ViewHolder mViewHolder = new ViewHolder();
    private TaskViewModel mViewModel;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_date:

                break;
            case R.id.btn_save:

                break;
        }
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

    /**
     * Observadores
     */
    private void loadObservers() {
    }



    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText textDescription;
        Spinner spinnerPriority;
        CheckBox checkComplete;
        Button btnDate;
        Button btnSave;
    }
}