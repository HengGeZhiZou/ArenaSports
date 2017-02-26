package team.lw.arena.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.GameDao;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.GameService;

@Service
@Transactional
@Scope("prototype")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    private SixPeopleRoom sixPeopleRoom = new SixPeopleRoom();

    @Override
    public String CreateHouse(String houseOwnerId)  {
        sixPeopleRoom.setCharacter01(houseOwnerId);
        gameDao.save(sixPeopleRoom);
        return houseOwnerId;
    }

    @Override
    public String inviteOthers(SixPeopleRoom sixPeopleRoom) {
        gameDao.update(sixPeopleRoom);
        return sixPeopleRoom.getCharacter01();
    }
}
