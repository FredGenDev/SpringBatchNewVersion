package com.example.SpringBatchNewVersion.repo;

import com.example.SpringBatchNewVersion.data.MyAppItem;

import java.util.List;

public interface MyAppRepository {
    List<MyAppItem> listAllItem();
}
