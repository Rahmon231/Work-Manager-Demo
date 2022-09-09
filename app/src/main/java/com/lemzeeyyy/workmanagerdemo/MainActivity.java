package com.lemzeeyyy.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        WorkRequest countWorkRequest = new OneTimeWorkRequest
                .Builder(DemoWorker.class)
                .setConstraints(constraints)
                .build();
        btn.setOnClickListener(view -> {
            WorkManager.getInstance(getApplicationContext()).enqueue(countWorkRequest);
        });

        //Display Status of Worker
        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(countWorkRequest.getId())
                .observe(this, workInfo -> {
                    if(workInfo!=null){
                        Toast.makeText(this,
                                "Status :"+workInfo.getState().name(),
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }
}