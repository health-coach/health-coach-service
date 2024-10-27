package com.team.health_coach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ObjectService {

    private final RestTemplate restTemplate;
    private final String storageUrl;
    
    @Autowired
    private TokenService tokenService;

    public ObjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.storageUrl = ${auth.storageUrl};
    }

    public void deleteObject(String containerName, String objectName) {
        String url = String.format("%s/%s/%s", storageUrl, containerName, objectName);
        String tokenId = tokenService.requestToken();

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", tokenId);
        HttpEntity<String> requestHttpEntity = new HttpEntity<>(null, headers);
        System.out.println("Deleting object with URL: " + url);
        // object storage 삭제 API 호출
        restTemplate.exchange(url, HttpMethod.DELETE, requestHttpEntity, String.class);
    }
}