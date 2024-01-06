package com.anicaaz.leaguewarefx.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpsUtils {

    private String apiUrl;
    private String requestMethod;

    public Map<String, Object> sendHttpRequest(String authorizationToken) throws IOException {
        // 设置请求地址
        URL url = new URL(apiUrl);
        disableSslVerification();
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法
        connection.setRequestMethod(requestMethod);

        // 添加请求头，设置Authorization参数
        connection.setRequestProperty("Authorization", authorizationToken);

        // 获取响应码
        int responseCode = connection.getResponseCode();

        // 读取响应内容并将其映射到Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(connection.getInputStream(), Map.class);

        // 关闭连接
        connection.disconnect();

        // 返回响应的Map
        return responseMap;
    }

    public Integer sendHttpRequestAndGetRespCode(String authorizationToken) throws IOException {
        // 设置请求地址
        URL url = new URL(apiUrl);

        // 禁用SSL连接
        disableSslVerification();

        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法
        connection.setRequestMethod(requestMethod);

        // 添加请求头，设置Authorization参数
        connection.setRequestProperty("Authorization", authorizationToken);

        // 获取响应码
        int responseCode = connection.getResponseCode();

        // 关闭连接
        connection.disconnect();

        // 返回响应的Map
        return responseCode;
    }

    public JsonNode[] getJSON() throws IOException {
        // 设置请求地址
        URL url = new URL(apiUrl);

        //从Url中读取JSON数据
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode[] nodes = objectMapper.readValue(url, JsonNode[].class);

        return nodes;
    }

    public void downloadImage(String filePath, String authorizationToken) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Authorization", authorizationToken);
        InputStream inStream = connection.getInputStream();
        byte[] data = readInputStream(inStream);
        File imageFile = new File(filePath);
        FileOutputStream outStream = new FileOutputStream(imageFile);
        outStream.write(data);
        outStream.close();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    public static String constructUrl(String baseUrl, String appPort, String requestUrl) {
        return baseUrl + appPort + requestUrl;
    }

    private void disableSslVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
