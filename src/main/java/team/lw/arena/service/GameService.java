package team.lw.arena.service;


import team.lw.arena.entity.SixPeopleRoom;

public interface GameService {

    String CreateHouse(String houseOwnerId);

    String inviteOthers(SixPeopleRoom sixPeopleRoom);
}

