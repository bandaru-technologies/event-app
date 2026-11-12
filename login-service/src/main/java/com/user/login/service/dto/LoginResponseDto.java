package com.user.login.service.dto;

public class LoginResponseDto {

  private String token;
  private String tokenType;

  public LoginResponseDto() {}

  public LoginResponseDto(String token, String tokenType) {
    this.token = token;
    this.tokenType = tokenType;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }
}
