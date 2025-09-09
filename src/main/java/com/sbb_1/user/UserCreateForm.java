package com.sbb_1.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//수업에서는 innerclass작성
@Getter
@Setter

public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 ID는 필수입니다")
    private String username;

    // ‘비밀번호’와 ‘비밀번호 확인’에 대한 속성
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

}
