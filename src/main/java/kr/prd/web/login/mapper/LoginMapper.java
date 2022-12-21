package kr.prd.web.login.mapper;

import kr.prd.web.login.vo.LoginData;
import kr.prd.web.profile.vo.ProfileData;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface LoginMapper {
    
    int insertUserInfo(LoginData.UserInfoVO userInfo) throws SQLException; //유저정보 DB에 등록

    int userIdCheck(Map<String, Object> param) throws SQLException; //유저 Id값 유효성 검사

    LoginData.UserInfoVO getUserInfo(Map<String, Object> param) throws SQLException; //유저정보 받아오기

    int insertProfileInfo(LoginData.UserInfoVO userInfo) throws SQLException;


}
