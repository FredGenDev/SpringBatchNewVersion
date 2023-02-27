package com.example.SpringBatchNewVersion.batch;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

public class MyItemReader implements ItemReader<MyAppItem> {
    @Autowired
    DataSource dataSource;

    MyAppItem myAppItem = new MyAppItem();

    @Override
    public MyAppItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

//        return new JdbcCursorItemReaderBuilder<MyAppItem>()
//                .name("cursorItemReader")
//                .dataSource(dataSource)
//                .sql("SELECT id, name, value_long1, value_long2, value_str FROM MY_APP_ITEM ORDER BY ID;")
//                .rowMapper(new BeanPropertyRowMapper<>(MyAppItem.class))
//                .build();
//
//
        myAppItem.setName("in5");
        myAppItem.setValueLong1(5L);
        myAppItem.setValueLong2(5L);
        myAppItem.setValueStr("cinq");
        return myAppItem;
    }
}
