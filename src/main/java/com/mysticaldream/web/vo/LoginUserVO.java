package com.mysticaldream.web.vo;

import com.mysticaldream.web.annotation.PasswordMatching;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @description: UserVO
 * @date: 2022/6/1 16:19
 * @author: MysticalDream
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@PasswordMatching(message = "两次输入的密码不匹配")
public class LoginUserVO {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 32, message = "用户名长度范围是1-32位")
    @Pattern(regexp = "(?=^\\w+$)(.+)", message = "用户名只能由由数字、26个英文字母或者下划线组成")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码长度范围是8-20位")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(.+)", message = "密码必须包含大小写字母和数字的组合，不能使用特殊字符")
    private String password;

    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码长度范围是8-20位")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(.+)", message = "密码必须包含大小写字母和数字的组合，不能使用特殊字符")
    private String confirmPassword;

}
