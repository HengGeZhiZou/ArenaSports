package team.lw.arena.service;


import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;

import java.io.Serializable;


public interface UserInfoService {
        String loginService(UserLogin userLogin) throws ServiceException;  //����¼  ����id

        boolean checkEmailExistService(String email) throws ServiceException;  //��������Ƿ����

        String registerService(UserLogin userLogin);  //ע��  ����id

        String updatePasswordService(UserLogin userLogin) throws ServiceException;//�޸����� ����

        UserInfo findUserInfo(Serializable id) throws ServiceException; //��ȡ�û���Ϣ

        String  addUserInfoService(UserInfo userInfo); //�����ϸ��Ϣ ����id

        String updateUserInfoService(UserInfo userInfo);//�޸���Ϣ ����id


}
