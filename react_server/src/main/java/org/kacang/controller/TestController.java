package org.kacang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.kacang.domain.BoardDTO;
import org.kacang.domain.LoginRequest;
import org.kacang.domain.ProductVO;
import org.kacang.domain.UserDTO;
import org.kacang.security.CustomLoginSuccessHandler;
import org.kacang.security.CustomLogoutHandler;
import org.kacang.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class TestController {

	@Autowired
	private TestService tservice;
	
    @Autowired
    private PasswordEncoder pwencoder;
	
	@GetMapping(value="/getText", produces = "text/plain; charset=utf-8")
	public String getText(){
		return "안녕하세요";
	}
	
	@GetMapping(value="/getList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<String> getList(){
		List<String> arr = new ArrayList<String>();
		arr.add("api 요청");
		arr.add("성공");
		return arr;
	}
	
	private final ProductVO[] data = {
	        new ProductVO(1, "삼색나물 단호박솥밥", "담백하고 고소한 별미 도시락", 39200, "3namul.jpg", "3namul-info.jpg"),
	        new ProductVO(2, "다섯가지 야채 새우 볶음밥", "새우, 닭가슴살, 야채 듬뿍", 35400, "5vege.jpg", "5vege-info.jpg"),
	        new ProductVO(3, "코코넛 닭가슴살 커리", "코코넛 커리로 특별하게", 39200, "coconut.jpg", "coconut-info.jpg"),
	        new ProductVO(4, "통단호박 크랜베리 콕콕 샐러드", "단호박 고유의 맛을 그대로", 22200, "cranberry.jpg", "cranberry-info.jpg"),
	        new ProductVO(5, "갈비맛 빅볼 닭가슴살", "균형잡힌 구성으로 제대로 된 한끼!", 35400, "galbi.jpg", "galbi-info.jpg"),
	        new ProductVO(6, "갈릭 케이준 치킨 샐러드", "with 홀그레인 머스타드 드레싱", 32400, "garlic.jpg", "garlic-info.jpg")
    };

    @GetMapping(value="/api/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductVO[] getProducts() {
        return data;
    }
	    
    @GetMapping("/boardList")
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = tservice.getBoardList();
        return boardList;
    }
    
    @GetMapping("/board/{idx}")
    public BoardDTO getBoardInfo(@PathVariable("idx") int idx) {
    	log.warn("bdno = " + idx);
    	BoardDTO boardInfo = tservice.getBoardInfo(idx);
    	return boardInfo;
    }
    
    @PostMapping("/board")
    public String insertBoard(@RequestBody BoardDTO boardDTO) {
        try {
            tservice.insertBoard(boardDTO);
            return "success";
        } catch (Exception e) {
            log.error("Error while creating board: ", e);
            return "fail";
        }
    }
    
    @PostMapping("/api/checkUsername")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        boolean isDuplicate = tservice.isUsernameDuplicate(username);

        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);

        return ResponseEntity.ok(response);
    }
    
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;
    
    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @PostMapping("/api/login") // 경로 임의 설정
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException{
    	// AuthenticationManager를 사용하여 인증 수행
    		UsernamePasswordAuthenticationToken authenticationToken = 
    				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
    		
    		// authenticate 메서드를 통해 실제 인증이 이루어짐
    		Authentication authentication = 
    				authenticationManager.authenticate(authenticationToken);
    				
    		// 검증 완료 후 세션에 저장
    		SecurityContextHolder.getContext().setAuthentication(authentication);	
    		
		    customLoginSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    			
    		// 인증이 성공하면 CustomUserDetailsService가 호출되어 사용자가 반환됨
    		return new ResponseEntity<String>("Login successful" , HttpStatus.OK);
    }
    
    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
    	log.info("여기 타는건가여");
        // Spring Security의 LogoutHandler 호출
    	CustomLogoutHandler logoutHandler = new CustomLogoutHandler();
        logoutHandler.logout(request, response, null);

        // 로그아웃 성공 응답 반환
        return ResponseEntity.ok("Logout successful");
    }
    
    
    @PostMapping("/api/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
    	userDTO.setPassword(pwencoder.encode(userDTO.getPassword()));
        boolean success = tservice.registerUser(userDTO);
        if (success) {
            return ResponseEntity.ok("회원가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }
    
    @GetMapping("/api/user")
    public Authentication getUser() {
      return SecurityContextHolder.getContext().getAuthentication();
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "forward:/index.html";
    }
    
    @PutMapping("/boardUpdate/{idx}")
    public ResponseEntity<String> updateBoard(@PathVariable("idx") int idx, @RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        String content = payload.get("content");
        
        if (title == null || content == null || title.isEmpty() || content.isEmpty()) {
            return ResponseEntity.badRequest().body("Title or Content is missing");
        }
        log.info("title : " + title + "content : " + content + "idx" + idx );
        int result = tservice.updateBoard(idx, title, content);
        log.info(result);
        if (result > 0) {
            return ResponseEntity.ok("Update successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found");
        }
    }
    
    @DeleteMapping("/board/{idx}")
    public ResponseEntity<String> deleteBoard(@PathVariable("idx") int idx){
    	int result = tservice.deleteBoard(idx);
    	return ResponseEntity.ok("Delete successful");
    }
}
