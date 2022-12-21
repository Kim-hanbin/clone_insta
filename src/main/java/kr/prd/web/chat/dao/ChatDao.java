package kr.prd.web.chat.dao;

import kr.prd.web.chat.entity.ChatData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChatDao {
    ChatData.ChatRoomInfoVO getChatRoomList(Map<String, Object> param) throws SQLException;
}
