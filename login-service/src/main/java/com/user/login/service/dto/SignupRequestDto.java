package com.user.login.service.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignupRequestDto {

  private String username;
  private String email;
  private String phone;
  private String password;

}
