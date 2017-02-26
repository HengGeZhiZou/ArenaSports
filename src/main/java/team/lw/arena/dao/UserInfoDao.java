package team.lw.arena.dao;



import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;

import java.io.Serializable;


public interface UserInfoDao extends BaseDao<UserLogin> {

    UserLogin getUserLogin(String email);

    String getMaxId(); //��ȡ��ǰ�û������ID ����id

    boolean checkEmailExist(String email);  //����û������ַ�Ƿ����

    String userCheckLogin(UserLogin userLogin);//����¼  ����id�޸ĵ�¼ʱ��

    void updateTime(Serializable id);  //�����û������½ʱ��

    UserInfo findUserInfo(Serializable id);  // ��ȡ�û���Ϣ

    String addUserInfo(UserInfo userInfo) ; //����û���ϸ��Ϣ  ����id

    String updateUserInfo(UserInfo userInfo); //�û��޸���Ϣ  ����id

    void  addToken(UserLogin userLogin);  //Ϊ�û����token
}
