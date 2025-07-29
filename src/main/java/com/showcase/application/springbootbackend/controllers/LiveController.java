package com.showcase.application.springbootbackend.controllers;

import com.showcase.application.springbootbackend.config.AppInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/live")
@RequiredArgsConstructor
public class LiveController {

    private final AppInfo appInfo;

    @GetMapping("/appinfo")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(appInfo, HttpStatus.OK);
    }
}
