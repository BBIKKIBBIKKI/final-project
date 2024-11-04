package com.wearei.finalsamplecode.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config extends AwsConfig {

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCredentials= new BasicAWSCredentials(getAccessKey(), getSecretKey());
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}