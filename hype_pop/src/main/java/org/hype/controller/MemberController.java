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

    // 비밀번호 변경
    // 현재 DB에 저장된 비밀번호를 확인한 뒤 비밀번호 변경 요청을 처리합니다!
    // POST 요청으로 비밀번호를 변경합니다.
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("userId") String userId, 
                                 @RequestParam("currentPassword") String currentPassword, 
                                 @RequestParam("newPassword") String newPassword, Model model) {
        // TODO: 현재 비밀번호가 맞는지 확인하는 로직 추가
        // boolean isCurrentPasswordValid = memberService.checkPassword(userId, currentPassword);

        // TODO: 비밀번호가 맞으면 변경하는 로직 추가
        // if (isCurrentPasswordValid) {
        //     memberService.updatePassword(userId, newPassword);
        //     model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        //     return "/member/updateSuccess"; // 변경 후 성공 페이지로 이동
        // } else {
        //     model.addAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
        return "/member/changePassword"; // 변경 실패 시 비밀번호 변경 페이지로 이동
        // }
    }

    // 전화번호 변경
    // POST 요청으로 전화번호를 변경합니다.
    @PostMapping("/updatePhone")
    public String updatePhone(@RequestParam("userId") String userId, 
                              @RequestParam("newPhone") String newPhone, Model model) {
        // TODO: 전화번호를 변경하는 로직 추가
        // memberService.updatePhone(userId, newPhone);
        // model.addAttribute("message", "전화번호가 성공적으로 변경되었습니다.");
        return "/member/updateSuccess"; // 변경 후 성공 페이지로 이동
    }

    // 이메일 변경
    // POST 요청으로 이메일을 변경합니다.
    @PostMapping("/updateEmail")
    public String updateEmail(@RequestParam("userId") String userId, 
                              @RequestParam("newEmail") String newEmail, Model model) {
        // TODO: 이메일을 변경하는 로직 추가
        // memberService.updateEmail(userId, newEmail);
        // model.addAttribute("message", "이메일이 성공적으로 변경되었습니다.");
        return "/member/updateSuccess"; // 변경 후 성공 페이지로 이동
    }

    // 좋아하는 팝업 스토어 조회
    @GetMapping("/likedPopUpStores")
    public String getLikedPopUpStores(@RequestParam("userId") String userId, Model model) {
        log.info("좋아하는 팝업 스토어 조회: " + userId);
        
        // TODO: 사용자가 좋아하는 팝업 스토어 목록을 조회하는 로직 추가
        // List<PopUpStore> likedStores = memberService.getLikedPopUpStores(userId);
        // model.addAttribute("likedStores", likedStores);

        return "/member/likedPopUpStores"; // 좋아하는 팝업 스토어 JSP로 이동
    }

    // 좋아하는 팝업 스토어 삭제
    @PostMapping("/removeLikedPopUpStore")
    public String removeLikedPopUpStore(@RequestParam("userId") String userId, @RequestParam("storeId") Long storeId, Model model) {
        log.info("좋아하는 팝업 스토어 삭제: " + storeId + " by " + userId);
        
        // TODO: 팝업 스토어를 삭제하는 로직 추가
        // boolean isRemoved = memberService.removeLikedPopUpStore(userId, storeId);
        // if (isRemoved) {
        //     model.addAttribute("message", "팝업 스토어가 삭제되었습니다.");
        // } else {
        //     model.addAttribute("error", "팝업 스토어를 삭제하는 데 실패했습니다.");
        // }

        return "redirect:/member/likedPopUpStores"; // 좋아하는 팝업 스토어 목록 페이지로 리다이렉트
    }

    // 좋아하는 상품 조회
    @GetMapping("/likedGoods")
    public String getLikedGoods(@RequestParam("userId") String userId, Model model) {
        log.info("좋아하는 상품 조회: " + userId);
        
        // TODO: 사용자가 좋아하는 상품 목록을 조회하는 로직 추가
        // List<Goods> likedGoods = memberService.getLikedGoods(userId);
        // model.addAttribute("likedGoods", likedGoods);

        return "/member/likedGoods"; // 좋아하는 상품 JSP로 이동
    }

    // 좋아하는 상품 삭제
    @PostMapping("/removeLikedGoods")
    public String removeLikedGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") Long goodsId, Model model) {
        log.info("좋아하는 상품 삭제: " + goodsId + " by " + userId);
        
        // TODO: 상품을 삭제하는 로직 추가
        // boolean isRemoved = memberService.removeLikedGoods(userId, goodsId);
        // if (isRemoved) {
        //     model.addAttribute("message", "상품이 삭제되었습니다.");
        // } else {
        //     model.addAttribute("error", "상품을 삭제하는 데 실패했습니다.");
        // }

        return "redirect:/member/likedGoods"; // 좋아하는 상품 목록 페이지로 리다이렉트
    }

    // 관심사 업데이트
    @PostMapping("/updateInterests")
    public String updateInterests(@RequestParam("userId") String userId, @RequestParam("interests") String interests, Model model) {
        log.info("관심사 업데이트: " + userId + " -> " + interests);
        
        // TODO: 관심사를 업데이트하는 로직 추가
        // memberService.updateInterests(userId, interests);
        // model.addAttribute("message", "관심사가 성공적으로 변경되었습니다.");

        return "/member/updateSuccess"; // 변경 후 성공 페이지로 이동
    }

    // 회원 탈퇴
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("userId") String userId, Model model) {
        log.info("회원 탈퇴 요청: " + userId);
        
        // TODO: 회원 탈퇴 처리 로직 추가
        // boolean isWithdrawn = memberService.withdraw(userId);
        // if (isWithdrawn) {
        //     model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
        // } else {
        //     model.addAttribute("error", "회원 탈퇴에 실패했습니다.");
        // }

        return "redirect:/"; // 메인 페이지로 리다이렉트
    }
}