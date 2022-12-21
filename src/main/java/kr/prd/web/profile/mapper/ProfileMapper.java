package kr.prd.web.profile.mapper;

import kr.prd.web.profile.vo.ProfileData;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface ProfileMapper {

    int profileFix(ProfileData.ProfileFix userProfile) throws SQLException;

    ProfileData.ProfileVO getProfileView(Map<String, Object> param) throws SQLException;

}
