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

public class HttpClientTest {

    /**
     * 测试通过httpclient发送GET请求
     * @throws Exception
     */
    @Test
    public void testGET() throws Exception{
        //创建http对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        //发送请求，接收响应
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //获取服务端返回的状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态码为：" + statusCode);


        HttpEntity entity = httpResponse.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("服务端返回数据为：" + body);

        //关闭资源
        httpResponse.close();
        httpClient.close();
    }


    /**
     * 测试通过httpclient发送POST方式的请求
     * @throws Exception
     */
    @Test
    public void testPost() throws Exception{
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","123456");

        StringEntity entity = new StringEntity(jsonObject.toString());


        //指定请求编码
        entity.setContentEncoding("utf-8");

        //数据格式
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        //发送请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        //解析和返回结果
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("响应码为：" + statusCode);

        HttpEntity entity1 = httpResponse.getEntity();
        String body = EntityUtils.toString(entity1);
        System.out.println("响应数据为：" + body);

        //关闭资源
        httpResponse.close();
        httpClient.close();

    }
}
