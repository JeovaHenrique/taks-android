package com.example.tasks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasks.R;
import com.example.tasks.service.listener.Feedback;
import com.example.tasks.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mViewHolder.textEmail = findViewById(R.id.text_email);
        this.mViewHolder.textPassword = findViewById(R.id.text_password);
        this.mViewHolder.btnLogin = findViewById(R.id.btn_save);

        // Incializa as variáveis
        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        this.setListeners();
        // Cria observadores
        this.loadObservers();

        this.verifyUserLogged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_save) {
            String email = this.mViewHolder.textEmail.getText().toString();
            String password = this.mViewHolder.textPassword.getText().toString();

            this.mLoginViewModel.login(email,password);
        }

    }

    private void setListeners() {
        this.mViewHolder.btnLogin.setOnClickListener(this);
    }

    private void loadObservers() {
        this.mLoginViewModel.login.observe(this, new Observer<Feedback>() {
            @Override
            public void onChanged(Feedback feedBack) {
                if(feedBack.getSuccess()){
                    startMain();
                }else {
                    Toast.makeText(getApplicationContext(),feedBack.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.mLoginViewModel.userLogged.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logged) {
                if (logged) {
                    startMain();
                }
            }
        });
    }

    private void startMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void verifyUserLogged() {
        this.mLoginViewModel.verifyUserLogged();
    }

    private static class ViewHolder {
        EditText textEmail;
        EditText textPassword;
        Button btnLogin;
    }

}