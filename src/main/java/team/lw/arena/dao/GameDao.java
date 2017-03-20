package team.lw.arena.dao;

import team.lw.arena.entity.SixPeopleRoom;

public interface GameDao extends BaseDao<SixPeopleRoom> {

    void startGame(String ownerID,int isFull);


}
