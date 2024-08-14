package com.sky.test;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    private void testRedisTempLate(){
        System.out.println(redisTemplate);
        //string数据操作
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //hash类型数据操作
        HashOperations hashOperations = redisTemplate.opsForHash();
        //list类型数据操作
        ListOperations listOperations = redisTemplate.opsForList();
        //set类型数据操作
        SetOperations setOperations = redisTemplate.opsForSet();
        //zset类型数据操作
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    }

    /**
     * 操作字符串类型的数据
     */
    @Test
    public void testString(){
        // set get setex setnx
        //set：向redis中插入字符串的数据
        redisTemplate.opsForValue().set("name","小明");
        //get：从redis中获取字符串的数据
        String city = (String) redisTemplate.opsForValue().get("name");
        System.out.println(city);
        //setex：插入数据的同时设置数据的有效期
        redisTemplate.opsForValue().set("code","1234",3, TimeUnit.MINUTES);
        //setnx：当key不存在，就向redis中插入数据
        redisTemplate.opsForValue().setIfAbsent("lock","1");
        redisTemplate.opsForValue().setIfAbsent("lock","2");
    }



    /**
     * 操作哈希类型的数据
     */
    @Test
    public void testHash(){
        //hset hget hdel hkeys hvals
        HashOperations hashOperations = redisTemplate.opsForHash();

        //hset: 插入数据
        hashOperations.put("100","name","tom");
        hashOperations.put("100","age","20");

        //hget: 获取数据
        String name = (String) hashOperations.get("100", "name");
        System.out.println(name);

        //hkeys: 获取所有的key
        Set keys = hashOperations.keys("100");
        System.out.println(keys);

        //hvals: 获取所有的values
        List values = hashOperations.values("100");
        System.out.println(values);

        //hdel: 删除数据
        hashOperations.delete("100","age");
    }


    /**
     * 操作列表类型的数据
     */
    @Test
    public void testList(){
        //lpush lrange rpop llen
        ListOperations listOperations = redisTemplate.opsForList();

        //lpush：从左侧向列表当中插入元素
        listOperations.leftPushAll("mylist","a","b","c");
        listOperations.leftPush("mylist","d");

        //lrange：查询列表当中指定范围的元素
        List mylist = listOperations.range("mylist", 0, -1);
        System.out.println(mylist);

        //rpop：从列表的右侧移除元素
        listOperations.rightPop("mylist");

        //llen：获取列表的长度
        Long size = listOperations.size("mylist");
        System.out.println(size);
    }


    /**
     * 操作集合类型的数据
     */
    @Test
    public void testSet(){
        //sadd smembers scard sinter sunion srem
        SetOperations setOperations = redisTemplate.opsForSet();

        //向集合中添加元素
        setOperations.add("set1","a","b","c","d");
        setOperations.add("set2","a","b","x","y");

        //获取集合中对应的元素
        Set members = setOperations.members("set1");
        System.out.println(members);

        //获取集合中元素的个数
        Long size = setOperations.size("set1");
        System.out.println(size);

        //计算两个集合的交集
        Set intersect = setOperations.intersect("set1", "set2");
        System.out.println(intersect);

        //计算两个集合的并集
        Set union = setOperations.union("set1", "set2");
        System.out.println(union);

        //移除集合中的元素
        setOperations.remove("set1","a","b");
    }


    /**
     * 操作有序集合类型的数据
     */
    @Test
    public void testZset(){
        //zadd zrange zincrby zrem
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        //向集合中添加数据
        zSetOperations.add("zset1","a",10);
        zSetOperations.add("zset1","b",12);
        zSetOperations.add("zset1","c",9);

        //获取指定范围内的
        Set zset1 = zSetOperations.range("zset1", 0, -1);
        System.out.println(zset1);

        //给集合中的某个元素加上分数
        zSetOperations.incrementScore("zset1","c",10);

        //移除集合中的元素
        zSetOperations.remove("zset1","a","b");
    }


    /**
     * 通用命令操作
     */
    @Test
    public void testCommon(){
        //keys exists type del

        //获取所有的key
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);

        //查询key是否存在
        Boolean name = redisTemplate.hasKey("name");
        Boolean set1 = redisTemplate.hasKey("set1");

        //通过遍历来查看所有的key是什么类型
        for (Object key : keys) {
            DataType type = redisTemplate.type(key);
            System.out.println(type.name());
        }

        //删除key
        redisTemplate.delete("mylist");
    }





}
