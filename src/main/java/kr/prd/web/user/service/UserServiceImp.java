package kr.prd.web.user.service;

import kr.prd.web.user.dao.UserDao;
import kr.prd.web.user.entity.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserDao dao;

    public List<UserVO> getPostList() {

        List<UserVO> uservo = dao.getPostList();

        return uservo;

    }


}
