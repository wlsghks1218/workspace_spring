package org.kacang.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {
	private String username, password;
	
    @JsonProperty("remember-me") // JSON 필드 "remember-me"와 매핑
    private boolean rememberMe;
}