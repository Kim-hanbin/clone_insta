package kr.prd.web.profile.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class ProfileData {

    @Data
    public static class ProfileRequest {
        private int userNum;
        private String userName;
        private String profileIntro;
        private MultipartFile profileImg;
    }

    @Getter
    @Setter
    public static class ProfileVO {
        private int userNum;
        private String userId;
        private String userName;
        private String profileIntro;
        private int contentCount;
        private int friendCount;
        private String originalFileName;
        private String storedFileName;
    }

    @Getter
    @Builder //빌더패턴 사용(클래스를 객체화 해줌)
    @AllArgsConstructor //맴버변수 전체가 매개변수로 있는 생성자
    @NoArgsConstructor //기본생성자
    public static class ProfileFix {
        private int userNum;
        private String userName;
        private String profileIntro;
        @Setter
        private String originFileName;
        @Setter
        private String storedFileName;
    }

}
