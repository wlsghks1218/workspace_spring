package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

    
    // ��й�ȣ ���� ó��
    // ���� DB ������ ��й�ȣ ��ȿ�� �˻�� ���񽺿��� ó���ϰ� �˴ϴ�!
    // POST ��û���� ������� ���ο� ��й�ȣ�� �޾� ó���ϰ� �˴ϴ�!
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("userId") String userId, 
                                 @RequestParam("currentPassword") String currentPassword, 
                                 @RequestParam("newPassword") String newPassword, Model model) {
        // �ּ�: ���� ��й�ȣ�� �´��� Ȯ���ϴ� ������ �ʿ�
        // boolean isCurrentPasswordValid = memberService.checkPassword(userId, currentPassword);

        // �ּ�: �� ��й�ȣ�� ������Ʈ�ϴ� ������ �ʿ�
        // if (isCurrentPasswordValid) {
        //     memberService.updatePassword(userId, newPassword);
        //     model.addAttribute("message", "��й�ȣ�� ���������� ����Ǿ����ϴ�.");
        //     return "/member/updateSuccess"; // ���� �� ��� �������� �̵��ϰ� �˴ϴ�!
        // } else {
        //     model.addAttribute("error", "���� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
             return "/member/changePassword"; // ���� �� �ٽ� ��й�ȣ ���� �������� �̵��ϰ� �˴ϴ�!
        // }
    }

   

    // ��ȭ��ȣ ���� ó��
    // POST ��û���� ������� ���ο� ��ȭ��ȣ�� �޾� ó��
    @PostMapping("/updatePhone")
    public String updatePhone(@RequestParam("userId") String userId, 
                              @RequestParam("newPhone") String newPhone, Model model) {
        // �ּ�: ��ȭ��ȣ�� ������Ʈ�ϴ� ������ �ʿ�
        // memberService.updatePhone(userId, newPhone);
        // model.addAttribute("message", "��ȭ��ȣ�� ���������� ����Ǿ����ϴ�.");
         return "/member/updateSuccess"; // ���� �� ��� �������� �̵�
    }


    // �̸��� ���� ó��
    // POST ��û���� ������� ���ο� �̸����� �޾� ó��
    @PostMapping("/updateEmail")
    public String updateEmail(@RequestParam("userId") String userId, 
                              @RequestParam("newEmail") String newEmail, Model model) {
        // �ּ�: �̸����� ������Ʈ�ϴ� ������ �ʿ�
        // memberService.updateEmail(userId, newEmail);
        // model.addAttribute("message", "�̸����� ���������� ����Ǿ����ϴ�.");
         return "/member/updateSuccess"; // ���� �� ��� �������� �̵��ϰ� �˴ϴ�!
    }
    // ���ƿ��� �˾������ ��� ����ִ� �޼���
    @GetMapping("/likedPopUpStores")
    public String getLikedPopUpStores(@RequestParam("userId") String userId, Model model) {
        log.info("���ƿ��� �˾������ ��� ��ȸ: " + userId);
        
        // List<PopUpStore> likedStores = memberService.getLikedPopUpStores(userId);
        // model.addAttribute("likedStores", likedStores);

        return "/member/likedPopUpStores"; // ���ƿ��� �˾������ ��� JSP
    }

    // ��Ͽ��� �����ϴ� �޼���
    @PostMapping("/removeLikedPopUpStore")
    public String removeLikedPopUpStore(@RequestParam("userId") String userId, @RequestParam("storeId") Long storeId, Model model) {
        log.info("���ƿ��� �˾������ ����: " + storeId + " by " + userId);
        
        // boolean isRemoved = memberService.removeLikedPopUpStore(userId, storeId);
        // if (isRemoved) {
        //     model.addAttribute("message", "�˾����� �����Ǿ����ϴ�.");
        // } else {
        //     model.addAttribute("error", "�˾������ ������ �����߽��ϴ�.");
        // }

        return "redirect:/member/likedPopUpStores"; // ���ƿ��� �˾������ ��� �������� �����̷�Ʈ�ϰ� �˴ϴ�!
    }

    // ���ƿ��� ���� ��� ����ִ� �޼���
    @GetMapping("/likedGoods")
    public String getLikedGoods(@RequestParam("userId") String userId, Model model) {
        log.info("���ƿ��� ���� ��� ��ȸ: " + userId);
        
        // List<Goods> likedGoods = memberService.getLikedGoods(userId);
        // model.addAttribute("likedGoods", likedGoods);

        return "/member/likedGoods"; // ���ƿ��� ���� ��� JSP
    }

    // ���ƿ��� ���� ��Ͽ��� �����ϴ� �޼���
    @PostMapping("/removeLikedGoods")
    public String removeLikedGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") Long goodsId, Model model) {
        log.info("���ƿ��� ���� ����: " + goodsId + " by " + userId);
        
        // boolean isRemoved = memberService.removeLikedGoods(userId, goodsId);
        // if (isRemoved) {
        //     model.addAttribute("message", "��� �����Ǿ����ϴ�.");
        // } else {
        //     model.addAttribute("error", "���� ������ �����߽��ϴ�.");
        // }

        return "redirect:/member/likedGoods"; // ���ƿ��� ���� ��� �������� �����̷�Ʈ�ϰ� �˴ϴ�!
    }

    // ���ɻ� ���� �޼���
    @PostMapping("/updateInterests")
    public String updateInterests(@RequestParam("userId") String userId, @RequestParam("interests") String interests, Model model) {
        log.info("���ɻ� ����: " + userId + " -> " + interests);
        
        // memberService.updateInterests(userId, interests);
        // model.addAttribute("message", "���ɻ簡 ���������� ����Ǿ����ϴ�.");

        return "/member/updateSuccess"; // ���� �� ��� �������� �̵��ϰ� �˴ϴ�!
    }

    // ȸ�� Ż�� �޼���
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("userId") String userId, Model model) {
        log.info("ȸ�� Ż�� ��û: " + userId);
        
        // boolean isWithdrawn = memberService.withdraw(userId);
        // if (isWithdrawn) {
        //     model.addAttribute("message", "ȸ�� Ż�� �Ϸ�Ǿ����ϴ�.");
        // } else {
        //     model.addAttribute("error", "ȸ�� Ż�� �����߽��ϴ�.");
        // }

        return "redirect:/"; // ���� �������� �����̷�Ʈ�ϰ� �˴ϴ�!
    }
}
