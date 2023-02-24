package com.example.SpringBatchNewVersion.repo;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAppJpaRepository extends JpaRepository<MyAppItem, Long> {
}