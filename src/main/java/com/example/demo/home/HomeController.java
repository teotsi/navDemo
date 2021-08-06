package com.example.demo.home;

import com.squareup.okhttp.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class HomeController {
    @GetMapping("/api")
    public String home() {
        return "home";
    }

}
