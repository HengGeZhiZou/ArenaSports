package team.lw.arena.service;


import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;

import java.util.List;

public interface GameService {

    String CreateHouse(String houseOwnerId) throws ServiceException;

    String inviteOthers(String ownerID,String inviteeId) throws ServiceException;

    void start(String ownerID,String type,String district) throws  ServiceException;

//    void startGame(String  houseOwnerId) throws ServiceException;

//    boolean endGame(String ownerID) throws ServiceException;

    List<UserInfo> PopularGame(int currPage);
}

