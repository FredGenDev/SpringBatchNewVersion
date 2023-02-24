package com.example.SpringBatchNewVersion.batch;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import org.springframework.batch.item.ItemProcessor;

public class MyAppItemProcessor implements ItemProcessor<MyAppItem,String> {

    @Override
    public String process(MyAppItem item) throws Exception {
        return "In the processor...";
    }
}