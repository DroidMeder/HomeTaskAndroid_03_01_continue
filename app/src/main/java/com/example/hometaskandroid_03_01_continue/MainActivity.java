package com.example.hometaskandroid_03_01_continue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static String KEY_FOR_USERNAME = "key_for_username";
    public static String KEY_FOR_PASSWORD = "key_for_password";

    private ImageView imageView;
    private Button buttonGo;
    TextInputLayout textInputLayoutPassword, textInputLayoutUsername;
    private TextInputEditText textInputUsername, textInputPassword;
    private boolean isNotEmptyUsername=false, isNotEmptyPassword = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        changeListener(textInputUsername);
        changeListener(textInputPassword);
        click();


        Glide
                .with(this)
                .load("https://i.pinimg.com/474x/23/ab/a6/23aba60b66ef08174bb7455c4a8a2d2f.jpg")
                .into(imageView);

    }

    private void click() {
        buttonGo.setOnClickListener(v -> {

            if (isNotEmptyUsername){
                textInputLayoutUsername.setError(null);
                if (isNotEmptyPassword){
                    textInputLayoutPassword.setError(null);
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(KEY_FOR_USERNAME, Objects.requireNonNull(textInputUsername.getText()).toString());
                    intent.putExtra(KEY_FOR_PASSWORD, Objects.requireNonNull(textInputPassword.getText()).toString());
                    startActivity(intent);
                }else textInputLayoutPassword.setError("Длина пароля не должен быть меньше 6 символов!!!");
            }else textInputLayoutUsername.setError("Имя не может быть пустым!!!");
        });
    }

    private void changeListener(TextInputEditText inputLayout) {
        if (inputLayout == textInputUsername){
            inputLayout.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    isNotEmptyUsername = !s.toString().isEmpty();
                    textInputLayoutUsername.setError(null);
                }
            });
        }else if (inputLayout == textInputPassword){
            inputLayout.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty() && s.toString().length() > 6) {
                        isNotEmptyPassword = true;
                        textInputLayoutPassword.setError(null);
                    }
                }
            });
        }
    }

    private void initViews() {
        buttonGo=findViewById(R.id.btn_go);
        textInputLayoutPassword = findViewById(R.id.til_password);
        textInputLayoutUsername = findViewById(R.id.til_username);
        textInputUsername=findViewById(R.id.tied_username);
        textInputPassword=findViewById(R.id.tied_password);
        imageView=findViewById(R.id.imageView);
    }
}