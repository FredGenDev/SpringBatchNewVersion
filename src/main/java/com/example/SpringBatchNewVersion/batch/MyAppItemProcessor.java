package com.example.SpringBatchNewVersion.batch;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import org.springframework.batch.item.ItemProcessor;

public class MyAppItemProcessor implements ItemProcessor<MyAppItem,String> {

    @Override
    public String process(MyAppItem item) throws Exception {
        Long multiplication = item.getValueLong1() * item.getValueLong2();
        StringBuilder strOut = new StringBuilder();
        strOut.append(item.getName().toUpperCase()).append(";").append(item.getValueStr().toUpperCase()).append(";").append(multiplication) ;
        return strOut.toString();
    }
}