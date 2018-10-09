package com.tiger.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.tiger.ov.FileInfo;

import java.io.*;

/**
 * Created by wangshaohu on 10/9/18.
 */
public class OSSUtils {

    private final static String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private final static String ACCESS_KEY_ID = "LTAIbyOB2FRv0B9u";
    private final static String ACCESS_KEY_SECRET = "YKbJrdKDSmIahso1ihF1Nb6paR3W9R";
    private final static String BUCKET_NAME = "tiger-personal";


    public static void  uploadFile(String fileId, InputStream inputStream){
        OSSClient ossClient = new OSSClient(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET);
        PutObjectResult result = ossClient.putObject(BUCKET_NAME,fileId,inputStream);
        System.out.println(result.toString());
        ossClient.shutdown();
    }

    public static void downloadFile(String fileid){
        OSSClient ossClient = new OSSClient(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET);
        ossClient.getObject(new GetObjectRequest(BUCKET_NAME,fileid),
                new File("/Users/wangshaohu/Desktop/a.xlsx"));
        ossClient.shutdown();
    }

    public static void main(String[] args) {
        try {
            downloadFile("app1-234444ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
