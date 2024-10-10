package org.hype.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        
        // 사용자에게 보여줄 데이터 리스트를 준비합니다.
    
        // 준비한 데이터를 모델에 추가하여 JSP로 전달합니다.
        
        return "popUp/popUpMain"; // 메인 JSP로 이동
    }
}