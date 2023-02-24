package com.example.SpringBatchNewVersion.repo;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyAppRepositoryImpl implements MyAppRepository{
    @Autowired
    MyAppJpaRepository myAppJpaRepository;

    @Override
    public List<MyAppItem> listAllItem() {
        return myAppJpaRepository.findAll();
    }
}