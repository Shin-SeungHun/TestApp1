package com.ssh.testapp1;

import android.view.LayoutInflater;

import java.util.List;

// 상위 리사이클러뷰 아이템클래스를 정의
public class Item {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String itemTitle;
    // 하위 리사이클러뷰 아이템으로 정의한 subItemList를 전역변수로 선언한다.
    public List<SubItem> subItemList;

public Item(){}

    public Item(String itemTitle, List<SubItem> subItemList) {
        this.itemTitle = itemTitle;
        // 하위 리사이클러뷰
        this.subItemList = subItemList;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public List<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<SubItem> subItemList) {
        this.subItemList = subItemList;
    }
}


