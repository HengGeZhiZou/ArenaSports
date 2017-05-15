package team.lw.arena.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.GameDao;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.GameService;
import team.lw.arena.service.RecordService;
import team.lw.arena.service.UserInfoService;
import team.lw.arena.util.InviteUser;

import javax.annotation.Resource;

@Service
@Transactional
@Scope("prototype")
public class GameServiceImpl implements GameService {

    private final GameDao gameDao;

    @Resource(name = "recordServiceImpl")
    private RecordService recordService;

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public String CreateHouse(String houseOwnerId) throws ServiceException {
        SixPeopleRoom sixPeopleRoom = new SixPeopleRoom();
        sixPeopleRoom.setCharacter01(houseOwnerId);
        UserInfo userInfo=userInfoService.findUserInfo(houseOwnerId);
        userInfo.setPlaying(true);
        gameDao.save(sixPeopleRoom);
        userInfoService.updateUserInfoService(userInfo);
        return houseOwnerId;
    }

    @Override
    public String inviteOthers(String ownerID,String inviteeID) throws ServiceException {
         UserInfo userInfo= userInfoService.findUserInfo(inviteeID);
         if (!userInfo.getPlaying()){
             SixPeopleRoom sixPeopleRoom=gameDao.findObjectById(ownerID);
             sixPeopleRoom = InviteUser.getRoom(inviteeID,sixPeopleRoom);
             gameDao.update(sixPeopleRoom);
             userInfo.setPlaying(true);
             userInfoService.updateUserInfoService(userInfo);
             return inviteeID;
         }
         else {
             throw new ServiceException("你邀请的用户正在组队中！");
         }
    }

    @Override
    public void startGame(String houseOwnerId) throws ServiceException {
           gameDao.startGame(houseOwnerId,1);
           if(gameDao.findObjectById(houseOwnerId).getIsFull()!=1){
               throw new ServiceException("房间人数未满，开始比赛失败");
           }
    }


    @Override
    public boolean endGame(String ownerID) throws ServiceException {
        gameDao.startGame(ownerID,1);
        if(gameDao.findObjectById(ownerID).getIsFull()!=1){
            throw new ServiceException("房间人数未满，开始比赛失败");
        }
        SixPeopleRoom sixPeopleRoom=gameDao.findObjectById(ownerID);
        gameDao.delete(ownerID);
        for (int i=1;i<=6;i++){
            switch (i){
                case 1:
                    UserInfo userInfo1=userInfoService.findUserInfo(sixPeopleRoom.getCharacter01());
                    userInfo1.setPlaying(false);
                    userInfoService.updateUserInfoService(userInfo1);
                case 2:UserInfo userInfo2=userInfoService.findUserInfo(sixPeopleRoom.getCharacter02());
                    userInfo2.setPlaying(false);
                    userInfoService.updateUserInfoService(userInfo2);
                case 3:UserInfo userInfo3=userInfoService.findUserInfo(sixPeopleRoom.getCharacter03());
                    userInfo3.setPlaying(false);
                    userInfoService.updateUserInfoService(userInfo3);;
                case 4:UserInfo userInfo4=userInfoService.findUserInfo(sixPeopleRoom.getCharacter04());
                    userInfo4.setPlaying(false);
                    userInfoService.updateUserInfoService(userInfo4);;
                case 5:UserInfo userInfo5=userInfoService.findUserInfo(sixPeopleRoom.getCharacter05());
                    userInfo5.setPlaying(false);
                    userInfoService.updateUserInfoService(userInfo5);;
                case 6:UserInfo userInfo6=userInfoService.findUserInfo(sixPeopleRoom.getCharacter06());
                    userInfo6.setPlaying(false);
                    userInfoService.updateUserInfoService(userInfo6);
            }
        }
        recordService.addSixRoomRecord(sixPeopleRoom);
        return true;
    }
}
