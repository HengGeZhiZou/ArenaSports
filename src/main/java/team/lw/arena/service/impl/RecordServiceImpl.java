package team.lw.arena.service.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.GameDao;
import team.lw.arena.dao.RecordDao;
import team.lw.arena.dao.UserInfoDao;
import team.lw.arena.entity.Record;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.service.RecordService;
import team.lw.arena.util.CreateNewUserId;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
public class RecordServiceImpl implements RecordService {

    @Resource(name = "recordDaoImpl")
    private RecordDao recordDao;

    @Resource(name = "userInfoDaoImpl")
    private UserInfoDao userInfoDao;

    private static int pageSize = 10;

    @Override
    public boolean addSixRoomRecord(SixPeopleRoom sixPeopleRoom,String district,String type) {

        String recordID= CreateNewUserId.crateStateId(recordDao.getMaxRecordID(sixPeopleRoom.getCharacter01()));
        recordDao.save(new Record(
                recordID,
                new Timestamp(System.currentTimeMillis()),
                district,
                type,
                sixPeopleRoom.getCharacter01(),
                sixPeopleRoom.getCharacter02(),
                sixPeopleRoom.getCharacter03(),
                sixPeopleRoom.getCharacter04(),
                sixPeopleRoom.getCharacter05(),
                sixPeopleRoom.getCharacter06()
        ));
        return true;
    }

    @Override
    public Record findByRecordID(String rid) {
        Record record = recordDao.findObjectById(rid);
        for (int i=1;i<=6;i++){
            switch (i){
                case 1:
                    UserInfo userInfo1=userInfoDao.findUserInfo(record.getId01());
                    record.setId01Img(userInfo1.getPortrait());
                    record.setId01Name(userInfo1.getName());
                case 2:
                    UserInfo userInfo2=userInfoDao.findUserInfo(record.getId02());
                    record.setId02Img(userInfo2.getPortrait());
                    record.setId02Name(userInfo2.getName());
                case 3:
                    UserInfo userInfo3=userInfoDao.findUserInfo(record.getId03());
                    record.setId03Img(userInfo3.getPortrait());
                    record.setId03Name(userInfo3.getName());
                case 4:
                    UserInfo userInfo4=userInfoDao.findUserInfo(record.getId04());
                    record.setId04Img(userInfo4.getPortrait());
                    record.setId04Name(userInfo4.getName());
                case 5:
                    UserInfo userInfo5=userInfoDao.findUserInfo(record.getId05());
                    record.setId05Img(userInfo5.getPortrait());
                    record.setId05Name(userInfo5.getName());
                case 6:
                    UserInfo userInfo6=userInfoDao.findUserInfo(record.getId06());
                    record.setId06Img(userInfo6.getPortrait());
                    record.setId06Name(userInfo6.getName());
            }
        }
        return record;
    }


    @Override
    public List<Record> findByPage(Integer currPage,String uid) {
        List<Record> list = recordDao.findByPage(pageSize*(currPage-1), pageSize,uid);
        return getRecordInfo(list);
    }

    @Override
    public List<Record> currRecord(int currPage) {
        List<Record> list = recordDao.findCurrRecord(pageSize*(currPage-1), pageSize);
        return getRecordInfo(list);
    }


    private List<Record> getRecordInfo(List<Record> list){
        for (int j=0;j<list.size();j++){
            for (int i=1;i<=6;i++){
                switch (i){
                    case 1:
                        UserInfo userInfo1=userInfoDao.findUserInfo(list.get(j).getId01());
                        list.get(j).setId01Img(userInfo1.getPortrait());
                        list.get(j).setId01Name(userInfo1.getName());
                        break;
                    case 2:
                        UserInfo userInfo2=userInfoDao.findUserInfo(list.get(j).getId02());
                        list.get(j).setId02Img(userInfo2.getPortrait());
                        list.get(j).setId02Name(userInfo2.getName());
                        break;
                    case 3:
                        if (list.get(j).getId03()!=null){
                            UserInfo userInfo3=userInfoDao.findUserInfo(list.get(j).getId03());
                            list.get(j).setId03Img(userInfo3.getPortrait());
                            list.get(j).setId03Name(userInfo3.getName());
                        }
                       break;
                    case 4:
                        if (list.get(j).getId04()!=null){
                            UserInfo userInfo4=userInfoDao.findUserInfo(list.get(j).getId04());
                            list.get(j).setId04Img(userInfo4.getPortrait());
                            list.get(j).setId04Name(userInfo4.getName());
                        }
                        break;
                    case 5:
                        if (list.get(j).getId05()!=null){
                            UserInfo userInfo5=userInfoDao.findUserInfo(list.get(j).getId05());
                            list.get(j).setId05Img(userInfo5.getPortrait());
                            list.get(j).setId05Name(userInfo5.getName());
                        }
                        break;
                    case 6:
                        if (list.get(j).getId06()!=null){
                            UserInfo userInfo6=userInfoDao.findUserInfo(list.get(j).getId06());
                            list.get(j).setId06Img(userInfo6.getPortrait());
                            list.get(j).setId06Name(userInfo6.getName());
                        }
                        break;
                }
            }
        }
        return list;
    }

}
