package team.lw.arena.service;


import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;

import java.io.Serializable;


public interface UserInfoService {

        UserLogin getUserLogin(String email) throws ServiceException;

        String loginService(UserLogin userLogin) throws ServiceException;  //����¼  ����id

        boolean checkEmailExistService(String email) throws ServiceException;  //��������Ƿ����

        String registerService(String username,String password);  //ע��  ����id

        String updatePasswordService(UserLogin userLogin) throws ServiceException;//�޸����� ����

        UserInfo findUserInfo(Serializable id) throws ServiceException; //��ȡ�û���Ϣ

        String  addUserInfoService(UserInfo userInfo); //�����ϸ��Ϣ ����id

        String updateUserInfoService(UserInfo userInfo);//�޸���Ϣ ����id

        void addPortrait(String uid,String imaPath);  //�����û�ͷ��

        String addToken(String email);//�û���½ʱ����token

        void deleteToken(String email);//�û��˳�ʱɾ��token
}
