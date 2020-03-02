package com.huang.amqp;

import com.huang.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /*
     * 1.单播（点对点）
     *
     * */
    @Test
    public void contextLoads() {
//      rabbitTemplate.send(exchange,routeKey,message);
//      rabbitTemplate.convertAndSend(exchange,routeKey,object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息！");
        map.put("data", Arrays.asList("helloworld", 123, true));
        //对象被默认序列化之后发送出去
//      rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("西游记", "吴承恩"));

    }

    //接受数据
    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /*
     * 广播
     * */
    @Test
    public void sendMsg() {
//      rabbitTemplate.convertAndSend("exchange.fanout", new Book("三国演义", "罗贯中"));
        rabbitTemplate.convertAndSend("exchange.fanout","", new Book("西游记", "吴承恩"));
    }



}
