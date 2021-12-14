package com.navyck.androidlifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        Button mSecondActivityButton = findViewById(R.id.secondButton);
        mSecondActivityButton.setOnClickListener(v -> startActivity(intent));

        Button mDialogButton = findViewById(R.id.showDialog);
        mDialogButton.setOnClickListener(v -> showDialog());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("확인하였습니까?");
        builder.setPositiveButton("확인",
                (dialog, which) -> Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_LONG).show());
        builder.setNegativeButton("취소",
                (dialog, which) -> Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show());
        builder.show();
    }
}



/*
 앱 시작 시
 1. D/LifeCycle: onCreate()
 2. D/LifeCycle: onStart(), D/LifeCycle: onResume()

 2번째 액티비티로 이동 시
 1. D/LifeCycle: onPause()
 2. D/LifeCycle2: onCreate()
 3. D/LifeCycle2: onStart(), D/LifeCycle2: onResume()

 2번째 액티비티 finish
 1. D/LifeCycle2: onPause()
 2. D/LifeCycle: onResume()
 3. D/LifeCycle2: onStop(), D/LifeCycle2: onDestroy()

 홈 버튼 시
 1. D/LifeCycle: onPause()
 2. D/LifeCycle: onStop()

 다시 앱 켰을 때
 1. D/LifeCycle: onRestart(), D/LifeCycle: onStart()
 2. D/LifeCycle: onResume()

 앱 종료 시
 1. D/LifeCycle: onPause()
 2. D/LifeCycle: onStop(), D/LifeCycle: onDestroy()
*/
