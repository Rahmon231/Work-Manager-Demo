package com.lemzeeyyy.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        WorkRequest countWorkRequest = new OneTimeWorkRequest.Builder(DemoWorker.class)
                .build();
        btn.setOnClickListener(view -> {
            WorkManager.getInstance(getApplicationContext()).enqueue(countWorkRequest);
        });
    }
}