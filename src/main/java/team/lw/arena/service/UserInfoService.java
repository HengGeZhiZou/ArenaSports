package team.lw.arena.service;


import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;

import java.io.Serializable;


public interface UserInfoService {
        String loginService(UserLogin userLogin) throws ServiceException;  //检查登录  返回id

        boolean checkEmailExistService(String email) throws ServiceException;  //检查邮箱是否存在

        String registerService(UserLogin userLogin);  //注册  返回id

        String updatePasswordService(UserLogin userLogin) throws ServiceException;//修改密码 返回

        UserInfo findUserInfo(Serializable id) throws ServiceException; //获取用户信息

        String  addUserInfoService(UserInfo userInfo); //添加详细信息 返回id

        String updateUserInfoService(UserInfo userInfo);//修改信息 返回id


}
