package kr.prd.web.profile.controller;

import kr.prd.web.login.vo.LoginData;
import kr.prd.web.profile.service.ProfileService;
import kr.prd.web.profile.vo.ProfileData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @GetMapping("/home")
    @ResponseBody
    //profileProc view에서 userNum을 받아 userNum을 통해 userInfo를 찾아 profileView에 전송
    public ModelAndView profile(@RequestParam(name = "userNum") int userNum) throws Exception
    {
        ModelAndView view = new ModelAndView();
        Map <String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        ProfileData.ProfileVO vo = service.getProfileView(param);
        String path = "image/"+vo.getStoredFileName();
        vo.setStoredFileName(path);
        view.addObject(vo);
        view.setViewName("views/profile/profile");
        return view;
    }

    @GetMapping("/profileFix")
    //세션에 담겨있는 유저정보중 usrNum으로 DB에서 정보를 받아서 profileFixView에 전송
    //이미지는 따로 매핑해둔 inage/{imageName}형식으로 바꿔서 전송 (view에서 url 지정 안해주고 그대로 쓰기위함)
    public ModelAndView profileFix(HttpSession session) throws Exception
    {
        LoginData.UserInfoVO userInfo = (LoginData.UserInfoVO)session.getAttribute("userInfo");
        int userNum = userInfo.getUserNum();
        Map <String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        ProfileData.ProfileVO vo = service.getProfileView(param);
        String path = "image/"+vo.getStoredFileName();
        vo.setStoredFileName(path);
        ModelAndView view = new ModelAndView();
        view.addObject("vo",vo);
        view.setViewName("views/profile/profileFix");
        return view;
    }

    @PostMapping("/profileFix")
    @ResponseBody
    //view에서 submit하면 service.profileFix실행 (user정보 바꿔줌(사진, 이름, 한줄소개))
    public Map<String,Object> updateBoard(@ModelAttribute ProfileData.ProfileRequest profileRequest) {
        Map <String, Object> resultMap = new HashMap<>();
        try {
            int result = service.profileFix(profileRequest);
            if(result > 0){
                resultMap.put("resultCode", 200);
            }
            else {
                resultMap.put("resultCode", 500);
            }

        }catch(Exception e) {
            resultMap.put("resultCode", 500);
            e.printStackTrace();
        }

        return resultMap;
    }




    @GetMapping("/infoFix")
    //유저정보 수정 view (아직 백엔드 작업 못함..)
    public ModelAndView infoFix()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/profile/infoFix");
        return view;
    }


    //로컬에 있는 이미지 저장장소를 url로 만들어 view에서 바로 볼수있게 매핑
    @GetMapping(value = "image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("imageName") String imageName) throws IOException {
        InputStream imageStream = new FileInputStream("C://download/files/" + imageName);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }


}
