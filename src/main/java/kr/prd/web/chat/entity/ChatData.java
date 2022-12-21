package kr.prd.web.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import java.util.Date;

public class ChatData {


    @Setter
    @Getter
    @NoArgsConstructor
    public static class ChatRoomInfoVO {
        private int userId;
        private int chatId;
        private String chatTitle;
        private Date chatCreateTime;
    }

    @Setter
    @Getter
    public static class MessageInfoVO {
        private int chatId;
        private String chatTitle;
        private Date chatCreateTime;
    }

    @Getter
    @AllArgsConstructor
    public static class ChatRoomUserInfoVO {
        private int messageId;
        private int userId;
        private int chatId;
        private Text messageContents;
        private Date messageCreateTime;

    }
}
