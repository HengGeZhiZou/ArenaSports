package team.lw.arena.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.GameDao;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.GameService;
import team.lw.arena.service.RecordService;

import javax.annotation.Resource;

@Service
@Transactional
@Scope("prototype")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Resource(name = "recordServiceImpl")
    private RecordService recordService;

    @Override
    public String CreateHouse(String houseOwnerId)  {
        SixPeopleRoom sixPeopleRoom = new SixPeopleRoom();
        sixPeopleRoom.setCharacter01(houseOwnerId);
        gameDao.save(sixPeopleRoom);
        return houseOwnerId;
    }

    @Override
    public String inviteOthers(SixPeopleRoom sixPeopleRoom) {
        gameDao.update(sixPeopleRoom);
        return sixPeopleRoom.getCharacter01();
    }

    @Override
    public void startGame(String houseOwnerId) throws ServiceException {
           gameDao.startGame(houseOwnerId,1);
           if(gameDao.findObjectById(houseOwnerId).getIsFull()!=1){
               throw new ServiceException("房间人数未满，开始比赛失败");
           }
    }


    @Override
    public boolean endGame(String ownerID) {
        SixPeopleRoom sixPeopleRoom=gameDao.findObjectById(ownerID);
        gameDao.delete(ownerID);
        recordService.addSixRoomRecord(sixPeopleRoom);
        return true;
    }
}
