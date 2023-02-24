package com.example.SpringBatchNewVersion.batch;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyItemReader implements ItemReader<MyAppItem> {
    MyAppItem myAppItem = new MyAppItem();

    @Override
    public MyAppItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        myAppItem.setName("in5");
        myAppItem.setValueLong1(5L);
        myAppItem.setValueLong2(5L);
        myAppItem.setValueStr("cinq");
        return myAppItem;
    }
}
