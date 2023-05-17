package com.ssh.testapp1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ssh.testapp1.view_model.MainViewModel;
import com.ssh.testapp1.R;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewModel = new MainViewModel();
        viewModel.init(fragmentManager);

        findViewById(R.id.btn_fragment1).setOnClickListener(v -> viewModel.onButtonClick(v.getId()));
        findViewById(R.id.btn_fragment2).setOnClickListener(v -> viewModel.onButtonClick(v.getId()));
        findViewById(R.id.btn_fragment3).setOnClickListener(v -> viewModel.onButtonClick(v.getId()));
        findViewById(R.id.btn_fragment4).setOnClickListener(v -> viewModel.onButtonClick(v.getId()));
        findViewById(R.id.btn_fragment5).setOnClickListener(v -> viewModel.onButtonClick(v.getId()));
    }
}


