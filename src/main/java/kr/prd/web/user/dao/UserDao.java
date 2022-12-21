package kr.prd.web.user.dao;

import kr.prd.web.user.entity.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<UserVO> getPostList();

}
