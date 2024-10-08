package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/goodsStore/*")
public class GoodsController {
    @GetMapping("/goodsDetails")
    public String goodsSearch(@RequestParam("goodsName") String goodsName, Model model) {
        // searchData�� ����Ͽ� �˻� ������ ó��
        System.out.println("�˻� ������: " + goodsName);
        
        //DB���� ���� �޾ƿ��� ������ �����־����
        
        // searchData�� �𵨿� �߰��Ͽ� JSP�� ����
        model.addAttribute("goodsName", goodsName);
        
        return "/goodsStore/goodsDetails"; // �˻� ����� ������ JSP ������ �̸�
    }
 

    @GetMapping("/goodsMain")
    public String goodsMain() {
    	
    	// ����� ���� �޾ƿ��� ���� �ʿ�
         
         log.info("���� ������������");
        
        return "/goodsStore/goodsMain"; // �˻� ����� ������ JSP ������ �̸�
    }
//    @GetMapping("/goodsMain")
//    public String goodsMain(Model model) {
//        log.info("���� ������������");
//
//        // ���񽺿��� ���� ����Ʈ�� �޾ƿ�
//        List<Goods> goodsList = goodsService.getGoodsList();
//        
//        // �𵨿� ���� ����Ʈ �߰�
//        model.addAttribute("goodsList", goodsList);
//        
//        return "/goodsStore/goodsMain"; // ���� �������� �̵�
//    }
    @GetMapping("/goodsSearch")
    public String goodsSearch() {
         
    	// ����� ���� �޾ƿ��� ���� �ʿ�
    	
         log.info("���� �˻���������");
        
        return "/goodsStore/goodsSearch"; // �˻� ����� ������ JSP ������ �̸�
    }
}
