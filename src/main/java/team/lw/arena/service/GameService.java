package team.lw.arena.service;


import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;

public interface GameService {

    String CreateHouse(String houseOwnerId) throws ServiceException;

    String inviteOthers(String ownerID,String inviteeId) throws ServiceException;

    void startGame(String  houseOwnerId) throws ServiceException;

    boolean endGame(String ownerID) throws ServiceException;
}

