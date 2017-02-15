package team.lw.arena.dao;


import team.lw.arena.entity.MobileUser;

import java.util.List;

public interface PositionDao extends BaseDao<MobileUser> {

    List<MobileUser> getAroundPeople(int begin, int pageSize, Double top, Double left, Double bottom, Double right);

//    void updatePosition(MobileUser mobileUser);

}
