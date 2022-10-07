package com.example.tasks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tasks.R;
import com.example.tasks.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private RegisterViewModel mRegisterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Botão de voltar nativo
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.mViewHolder.mName = findViewById(R.id.text_name);
        this.mViewHolder.mEmail = findViewById(R.id.text_email);
        this.mViewHolder.mPassword = findViewById(R.id.text_password);
        this.mViewHolder.mBtnSave = findViewById(R.id.btn_save);

        this.mViewHolder.mBtnSave.setOnClickListener(this);

        // Incializa variáveis
        this.mRegisterViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Cria observadores
        this.loadObservers();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_save){
            String name = this.mViewHolder.mName.getText().toString();
            String email = this.mViewHolder.mEmail.getText().toString();
            String password = this.mViewHolder.mPassword.getText().toString();

            this.mRegisterViewModel.create(name,email,password);
        }
    }

    private void loadObservers() {}

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText mName;
        EditText mEmail;
        EditText mPassword;
        Button mBtnSave;
    }

}