package com.example.hometaskandroid_03_01_continue;

import static com.example.hometaskandroid_03_01_continue.MainActivity.KEY_FOR_PASSWORD;
import static com.example.hometaskandroid_03_01_continue.MainActivity.KEY_FOR_USERNAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textViewEdit;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();
        setIntentExtras();
        click();


        Glide
                .with(this)
                .load(R.drawable.pexels_pixabay)
                .into(imageView);
    }

    private void setIntentExtras() {
        if (getIntent()!= null){
            editTextUsername.setText(getIntent().getStringExtra(KEY_FOR_USERNAME));
            editTextPassword.setText(getIntent().getStringExtra(KEY_FOR_PASSWORD));
        }
    }

    private void click() {
        textViewEdit.setOnClickListener(v -> showAlert());
    }

    @SuppressLint("IntentReset")
    private void showAlert() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecondActivity.this);
        alertDialog.setTitle("Редактировать фото...");
        alertDialog.setNeutralButton("Сделать фото?", (dialog, which) -> {
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 0);
        });

        alertDialog.setPositiveButton("Из галереи?", (dialog, which) -> {
            @SuppressLint("IntentReset") Intent takeFromGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            takeFromGallery.setType("image/*");
            takeFromGallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(takeFromGallery, 1);
        });
        alertDialog.setNegativeButton("Назад?", (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    Bundle bundle = data.getExtras();
                    Bitmap bmp = (Bitmap) bundle.get("data");
                    Bitmap resized = Bitmap.createScaledBitmap(bmp, 152, 152, true);
                    imageView.setImageBitmap(resized);
                    break;

                case 1:
                    Uri ImageURI = data.getData();
                    imageView.setImageURI(ImageURI);
                    break;
            }


        }
    }


    private void initViews() {
        imageView=findViewById(R.id.imageView2);
        textViewEdit = findViewById(R.id.tv_edit);
        editTextUsername=findViewById(R.id.et_first_name);
        editTextPassword=findViewById(R.id.et_password);
    }
}