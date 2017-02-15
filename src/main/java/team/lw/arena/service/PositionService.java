package team.lw.arena.service;


import team.lw.arena.entity.MobileUser;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;

import java.util.List;

public interface PositionService {

    List<UserInfo> getAroundPeople(MobileUser mobileUser) throws ServiceException; //获取范围内的人

    void updatePosition(MobileUser mobileUser); //更新自己位置信息


}
