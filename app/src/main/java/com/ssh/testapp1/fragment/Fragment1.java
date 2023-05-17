package com.ssh.testapp1.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssh.testapp1.Item;
import com.ssh.testapp1.ItemAdapter;
import com.ssh.testapp1.R;
import com.ssh.testapp1.SubItem;
import com.ssh.testapp1.SubItemAdapter;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment1 extends Fragment {
    private RecyclerView rvItem;
    private SubItemAdapter adapter;
    private  ArrayList<SubItem> movieList;
    private ArrayList<Item> itemList;
    private ItemAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1, container, false);

        rvItem = view.findViewById(R.id.rv_item);
        movieList = new ArrayList<SubItem>();

        //Asynctask - OKHttp
        String Load_url = "https://api.themoviedb.org/3/movie/upcoming?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&language=ko-KR&page=1";
        String[] strings = {Load_url};
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(strings[0]);

       // itemList = prepareData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ItemAdapter itemAdapter = new ItemAdapter(buildItemList());
//        itemAdapter = new ItemAdapter(itemList, getActivity());
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
        itemAdapter.notifyDataSetChanged();

        return view;
    }

//    private ArrayList<Item> prepareData(){
//        ArrayList<Item> itemList = new ArrayList<Item>();
//
//        Item ss = new Item();
//        ss.setId(1);
//        ss.setItemTitle("ss");
//        ss.subItemList = new ArrayList<>();
//
//        SubItem subItemList1 = new SubItem();
//        subItemList1.id= 1;
//        adapter = new SubItemAdapter(getActivity(), movieList);
//
//        return itemList;
//    }

    private ArrayList<Item> buildItemList() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (int i = 1; i <= 10; i++) {
            Item item = new Item("Item " + i, buildSubItemList(i));
            itemList.add(item);
        }
        return itemList;
    }

    private ArrayList<SubItem> buildSubItemList(int pos) {
        ArrayList<SubItem> subItemList = new ArrayList<SubItem>();
        if (pos == 0) {
            for (int i = 1; i <= 3; i++) {
                SubItem subItem = new SubItem(i, "Sub Item " + i, "Description " + i, "" + i, "" + i, "" + i, "" + i);
                subItemList.add(subItem);
            }
        } else if (pos == 1) {
            for (int i = 1; i <= 3; i++) {
                SubItem subItem = new SubItem(i, "Sub Item1 " + i, "Description " + i, "" + i, "" + i, "" + i, "" + i);
                subItemList.add(subItem);
            }
        } else if (pos == 2) {
            for (int i = 1; i <= 3; i++) {
                SubItem subItem = new SubItem(i, "Sub Item2 " + i, "Description " + i, "" + i, "" + i, "" + i, "" + i);
                subItemList.add(subItem);
            }
        } else if (pos == 3) {
            for (int i = 1; i <= 3; i++) {
                SubItem subItem = new SubItem(i, "Sub Item3 " + i, "Description " + i, "" + i, "" + i, "" + i, "" + i);
                subItemList.add(subItem);
            }
        } else if (pos == 4) {
            for (int i = 1; i <= 3; i++) {
                SubItem subItem = new SubItem(i, "Sub Item4 " + i, "Description " + i, "" + i, "" + i, "" + i, "" + i);
                subItemList.add(subItem);
            }
        } else if (pos == 5) {
            for (int i = 1; i <= 3; i++) {
                SubItem subItem = new SubItem(i, "Sub Item5 " + i, "Description " + i, "" + i, "" + i, "" + i, "" + i);
                subItemList.add(subItem);
            }
        }

        return subItemList;
    }

    public class MyAsyncTask extends AsyncTask<String, Void, SubItem[]> {
        //로딩중 표시
        ProgressDialog progressDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            //show dialog
            progressDialog.show();

            //목록 배열의 내용을 클리어 해 놓는다.
            movieList.clear();

        }

        @Override
        protected SubItem[] doInBackground(String... strings) {
            // 처음에 영화를 불러올때랑 검색결과를 불러올때 둘 다 여기에서 진행
            // doInBackground(String... strings) << 여기에서 strings 를 받아와 url에 넣음

            // url 확인 Log
            Log.d("AsyncTask", "url : " + strings[0]);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            // 리퀘스트 결과
            try {
                Response response = client.newCall(request).execute();
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");
                // Movie클래스에 데이터 저장
                SubItem[] posts = gson.fromJson(rootObject, SubItem[].class);
                return posts;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(SubItem[] result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            //ArrayList에 차례대로 집어 넣는다.
            if (result.length > 0) {
                for (SubItem p : result) {
                    movieList.add(p);
                }
            }

            //어답터 설정
            adapter = new SubItemAdapter(getActivity(), movieList);
            rvItem.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }
}