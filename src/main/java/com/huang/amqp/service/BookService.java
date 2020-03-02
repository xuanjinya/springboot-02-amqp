package com.huang.amqp.service;

import com.huang.amqp.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author huang.beijin
 * @date 2020/3/2 20:55
 * @description
 */
@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book) {
        System.out.println("收到消息：" + book);
    }
}
