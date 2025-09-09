package com.sbb_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    //http://localhost:8080 페이지에 접속하면 root 메서드가 실행
    @GetMapping("/")
    public String root(){ //아무것도 입력하지 않아도 기본적으로 redirect
        return "redirect:/question/list";
    }

}
