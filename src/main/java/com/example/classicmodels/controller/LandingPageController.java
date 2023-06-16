package com.example.classicmodels.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingPageController {

    private String readInputStream(InputStream inputStream) {
        String ret = "";
        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                ret += line + System.lineSeparator();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    ResponseEntity<String> getLandingPageInternal() throws IOException {
        Resource resource = new ClassPathResource("index.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = readInputStream(inputStream);
        return ResponseEntity.status(HttpStatus.OK).body(htmlContent);
    }

    @GetMapping("/api")
    public ResponseEntity<String> getLandingPage() throws IOException {
        return getLandingPageInternal();
    }

    @GetMapping("/")
    public ResponseEntity<String> getApiLandingPage() throws IOException {
        return getLandingPageInternal();
    }
}
