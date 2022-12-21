package kr.prd.web.login.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LoginData{
    @Setter
    @Getter
    @NoArgsConstructor
    //저장될 유저 정보
    public static class UserInfoVO {
        private int userNum;
        private String userId;
        private String userPasswd;
        private String userName;
        private String userBirth;
        private String userGender;
        private String userPhone;
        private String userAddr1;
        private String userAddr2;
        private String userRoleId;
    }

    @Getter
    @AllArgsConstructor
    //로그인시 받을 정보
    public static class LoginRequest {
        private String securedUserId;
        private String securedUserPasswd;
    }
}
