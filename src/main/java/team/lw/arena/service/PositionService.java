package team.lw.arena.service;


import team.lw.arena.entity.MobileUser;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;

import java.util.List;

public interface PositionService {

    List<UserInfo> getAroundPeople(MobileUser mobileUser) throws ServiceException; //��ȡ��Χ�ڵ���

    void updatePosition(MobileUser mobileUser); //�����Լ�λ����Ϣ


}
