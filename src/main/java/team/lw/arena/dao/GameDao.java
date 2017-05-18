package team.lw.arena.dao;

import team.lw.arena.entity.SixPeopleRoom;

import java.util.List;

public interface GameDao extends BaseDao<SixPeopleRoom> {

    void startGame(String ownerID,int isFull);

    List<SixPeopleRoom> getCurrentGame(int begin,int pageSize);
}
