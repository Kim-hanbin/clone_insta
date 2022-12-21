package kr.prd.web.chat.service;

import kr.prd.web.chat.dao.ChatDao;
import kr.prd.web.chat.entity.ChatData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor  // 생성자를 통한 의존성 주입
public class ChatService {

    private final ChatDao chatDao;
    public ChatData.ChatRoomInfoVO getChatRoomList(Map<String, Object> param) throws Exception{

        return chatDao.getChatRoomList(param);
    }

}
