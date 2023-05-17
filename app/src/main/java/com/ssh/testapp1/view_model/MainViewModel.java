package com.ssh.testapp1.view_model;

import androidx.lifecycle.ViewModel;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ssh.testapp1.R;
import com.ssh.testapp1.fragment.Fragment1;
import com.ssh.testapp1.fragment.Fragment2;
import com.ssh.testapp1.fragment.Fragment3;
import com.ssh.testapp1.fragment.Fragment4;
import com.ssh.testapp1.fragment.Fragment5;

public class MainViewModel extends ViewModel {
    private FragmentManager fragmentManager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private FragmentTransaction transaction;

    public void init(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment1).commitAllowingStateLoss();
    }

    public void onButtonClick(int viewId) {
        transaction = fragmentManager.beginTransaction();
        switch (viewId) {
            case R.id.btn_fragment1:
                transaction.replace(R.id.frameLayout, fragment1).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment2:
                transaction.replace(R.id.frameLayout, fragment2).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment3:
                transaction.replace(R.id.frameLayout, fragment3).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment4:
                transaction.replace(R.id.frameLayout, fragment4).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment5:
                transaction.replace(R.id.frameLayout, fragment5).commitAllowingStateLoss();
                break;
        }
    }
}
