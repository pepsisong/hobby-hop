package com.hobbyhop.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateProfileDTO {
    @NotBlank(message = "이름을 변경해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9가-힣]", message = "한글, 영어, 숫자만 입력해야 합니다.")
    @Size(min = 1, max = 50, message = "이름은 최소 1자 이상, 최대 50자 이하로 입력해야 합니다.")
    private String username;

    @Email
    @NotBlank(message = "이메일을 변경해주세요.")
    private String email;

    @NotBlank(message = "기존 비밀번호를 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]+$", message = "비밀번호는 알파벳 대소문자, 숫자의 조합으로 입력해야 합니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자리 이상, 15자리 이하로 입력해야 합니다.")
    private String oldPassword;

    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]+$", message = "새 비밀번호는 알파벳 대소문자, 숫자의 조합으로 입력해야 합니다.")
    @Size(min = 8, max = 15, message = "새 비밀번호는 8자리 이상, 15자리 이하로 입력해야 합니다.")
    private String newPassword;

    @NotBlank(message = "새 비밀번호를 다시 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]+$", message = "새 비밀번호는 알파벳 대소문자, 숫자의 조합으로 입력해야 합니다.")
    @Size(min = 8, max = 15, message = "새 비밀번호는 8자리 이상, 15자리 이하로 입력해야 합니다.")
    private String confirmPassword;
//    private String introduce; 이거 넣을지 말지 팀원들이랑 상의하기
}
