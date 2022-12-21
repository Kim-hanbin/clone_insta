package kr.prd.web.login.service;

import kr.prd.web.login.mapper.LoginMapper;
import kr.prd.web.login.vo.LoginData;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper mapper; //lombok을 통해 의존성 주입
    private final PasswordEncoder passwordEncoder; //패스워드 암호화

    public int insertUserInfo(LoginData.UserInfoVO userInfo) {
        //패스워드만 암호화해서 유저정보 DB에 등록 (결과값 필요)
        int result = 0;
        try{
            String encodedPasswd = passwordEncoder.encode(userInfo.getUserPasswd());
            userInfo.setUserPasswd(encodedPasswd);
            result = mapper.insertUserInfo(userInfo);
            mapper.insertProfileInfo(userInfo);
        } catch (Exception e){
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public int userIdCheck(String userId) throws Exception{
        //view에서 받은 userId가 DB에 저장되있는 아이디들과 겹치는게 있는지 확인(결과값 필요)
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);

        return mapper.userIdCheck(param);
    }

    public String PhoneNumberCheck(String userPhone) throws CoolsmsException {
        //랜덤으로 4자리 비밀번호 생성 후 CoolSMS api를 통해 view에서 받은 userPhone에 인증번호 전송

        String api_key = "NCS7Y4IBGQ3US2TZ";
        String api_secret = "CA2IAO4ZXAIGVFQJENCVVRTRBNJ2TYT9";
        Message coolsms = new Message(api_key, api_secret);

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhone);    // 수신전화번호 (받아온 userPhone)
        params.put("from", "01024425348");    // 발신전화번호 (userPhone에 보낼 발신번호)
        params.put("type", "sms"); // 어떤 타입으로 보낼지 (카톡 or 문자)
        params.put("text", "인증번호는 [" + numStr + "] 입니다."); // 뭐라고 보낼지

        coolsms.send(params); // 메시지 전송

        return numStr;

    }

    public int loginProcess(Map<String, Object> param, HttpSession session) {
        //view에서 받은 유저정보와 DB에 저장된 유저 정보를 비교해서 로그인
        int result = 0; //로직 에러

        try {
            LoginData.UserInfoVO user = mapper.getUserInfo(param);
            String userPasswd = param.get("userPasswd").toString();

            if(user!=null) {
                if (passwordEncoder.matches(userPasswd, user.getUserPasswd())) {
                    result = 1;
                    user.setUserPasswd("");
                    session.setAttribute("userInfo", user);
                } else {
                    result = -1; //비밀번호 오류
                }
            }else{
                result = -2; //아이디 오류
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
