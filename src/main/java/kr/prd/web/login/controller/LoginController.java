package kr.prd.web.login.controller;

import kr.prd.web.common.security.ZRsaSecurity;
import kr.prd.web.login.service.LoginService;
import kr.prd.web.login.vo.LoginData;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping("/loginForm") //로그인 view 매핑
    public ModelAndView login() throws Exception{
        ModelAndView view = new ModelAndView();
        view.setViewName("views/login/loginForm");
        return view;
    }

    @GetMapping("/joinForm") //회원가입 view 매핑
    public ModelAndView joinView() throws Exception{
        ModelAndView view = new ModelAndView();
        view.setViewName("views/join/joinForm");

        return view;
    }

    @PostMapping("/joinForm")
    @ResponseBody
    //회원가입 view에서 form으로 받은 정보 DB에 업로드 후 받은결과값 view에 다시 전송
    public Map<String, Object> insertUserInfo(@RequestBody LoginData.UserInfoVO userInfo) throws Exception{
        Map <String, Object> resultMap = new HashMap<>();
        int result = service.insertUserInfo(userInfo);
        if(result > 0){
            resultMap.put("resultCode", 200);
        }else {
            resultMap.put("resultCode",500);
        }

        return resultMap;
    }

    @GetMapping("/check/id")
    @ResponseBody
    //회원가입 view에서 중복아이디 검사 기능 (view에서 id값을 얻어와 db에서 중복되는 아이디 있는지 확인 후 결과값 view에 전송)
    public Map<String, Object> userIdCheck(@RequestParam(name="userId")String userId)throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        int result = service.userIdCheck(userId);
        if(result==0){
            resultMap.put("resultCode",200);
        }else{
            resultMap.put("resultCode",300);
        }

        return resultMap;
    }

    @GetMapping("/check/sendSMS")
    @ResponseBody
    // 회원가입 view에서 받은 userPhone을 받아 service에 phoneNumberCheck(sms전송기능)을 실행
    public String sendSMS(@RequestParam(name="userPhone") String userPhone) throws CoolsmsException {
        return service.PhoneNumberCheck(userPhone);
    }

    @GetMapping("/get/rsa-key")
    @ResponseBody
    //세션에 RSAPrivateKey를 저장 publicKeyModule,publicKeyExponent를 map에 담아 view에 전송
    public Map<String, Object> getRSAKey(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            ZRsaSecurity security = new ZRsaSecurity();
            PrivateKey privateKey = security.getPrivateKey();

            HttpSession session = request.getSession();
            //만약 개인키가 session에 등록되있다면 제거 후 재 등록
            if(session.getAttribute("_rsaPrivateKey_")!=null) {
                session.removeAttribute("_rsaPrivateKey_");
            }
            session.setAttribute("_rsaPrivateKey_", privateKey);

            String publicKeyModule = security.getRsaPublicKeyModulus();
            String publicKeyExponent = security.getRsaPublicKeyExponent();

            resultMap.put("publicKeyModule", publicKeyModule);
            resultMap.put("publicKeyExponent", publicKeyExponent);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @PostMapping("/proc")
    //view에서 받은 암호화된 정보를 세션에 저장해둔 공개키로 복호화 한뒤 service.loginProcess실행
    //결과값이 1(Ok)이면 profileProc로 view 체인지
    public ModelAndView loginProc(@ModelAttribute LoginData.LoginRequest loginRequest, HttpServletRequest req) throws Exception{
        ModelAndView view = new ModelAndView();
        ZRsaSecurity security = new ZRsaSecurity();
        PrivateKey privateKey = null;

        int result = 0;
        if(req.getSession().getAttribute("_rsaPrivateKey_") != null) {
            privateKey = (PrivateKey)req.getSession().getAttribute("_rsaPrivateKey_");

            //사용자 ID 복호화
            String userId = security.decryptRSA(privateKey, loginRequest.getSecuredUserId());
            //사영자 비밀번호 복호화
            String userPasswd = security.decryptRSA(privateKey, loginRequest.getSecuredUserPasswd());

            Map<String, Object> param = new HashMap<>();
            param.put("userId", userId);
            param.put("userPasswd", userPasswd);

            result = service.loginProcess(param, req.getSession());

            if(result >0) {
                LoginData.UserInfoVO user = (LoginData.UserInfoVO)req.getSession().getAttribute("userInfo");
                int userNum = user.getUserNum();
                Map <String, Object> userParam = new HashMap<>();
                userParam.put("userNum",userNum);
                view.addObject("userNum", userNum);
                view.setViewName("views/profile/profileProc");
            }else {
                view.setViewName("views/login/error");
                if(result== -1) {
                    view.addObject("msg","비밀번호가 옳바르지 않습니다.");
                }else if(result == -2) {
                    view.addObject("msg","아이디가 옳바르지 않습니다.");
                }else if(result == 0) {
                    view.addObject("msg","시스템에 문제가 발생했습니다.");
                }
            }

        }else {
            view.setViewName("views/login/error");
            view.addObject("msg", "비정상접근을 통한 로그인시도 입니다.");
        }

        return view;
    }



}
