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
        
        // �˾� ����� ����Ʈ�� ���񽺿��� �޾ƿ��� �ڵ� �ʿ�
    
        
        // �𵨿� �˾� ����� ����Ʈ�� �߰��Ͽ� JSP�� ���� �ϴ� �ڵ� �ʿ�
        

        return "popUp/popUpMain"; // ���� ȭ�� JSP�� �̵�
    }
}
