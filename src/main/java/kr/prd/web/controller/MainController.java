package kr.prd.web.controller;

import kr.prd.web.chat.entity.ChatData;
import kr.prd.web.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    ChatService chatService;

    @GetMapping("/view1")
    public ModelAndView main(){
        ModelAndView view = new ModelAndView();
        view.setViewName("views/main/main");
        return view;
    }

    @GetMapping("/view2")
    public ModelAndView mainView(){
        ModelAndView view = new ModelAndView();
        view.setViewName("views/main/main_view");
        return view;
    }

    @GetMapping("posting")
    public ModelAndView getPostingPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("views/main/postingPage");
        return view;
    }

    @GetMapping("/getPostList")
    public ModelAndView getPostList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("views/profile/profile");
        return view;
    }


   @GetMapping("/getLogin")
    public ModelAndView getLogin(){
        ModelAndView view =new ModelAndView();
        view.setViewName("views/login/login");
        return view;
    }

    @GetMapping("/view2/chatRoom")
    public ModelAndView getChatRoomList(@RequestParam(name = "userId") int userId) throws Exception{
        ModelAndView view = new ModelAndView();
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        ChatData.ChatRoomInfoVO chtRoomInfo = chatService.getChatRoomList(param);
        view.addObject(chtRoomInfo);
        view.setViewName("views/chat/chatRoom_view");
        return view;
    }
}
