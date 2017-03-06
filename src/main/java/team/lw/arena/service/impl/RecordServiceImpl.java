package team.lw.arena.service.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.RecordDao;
import team.lw.arena.entity.Record;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.service.RecordService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
public class RecordServiceImpl implements RecordService {

    @Resource(name = "recordDaoImpl")
    private RecordDao recordDao;

    private static int pageSize = 4;

    public int getCount() {
        return recordDao.findCount();
    }

    @Override
    public boolean addSixRoomRecord(SixPeopleRoom sixPeopleRoom) {
        System.out.println(sixPeopleRoom.getCharacter01());
        System.out.println(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter01()));
        recordDao.save(new Record(
                new Timestamp(System.currentTimeMillis()),
                "中国",
                "篮球",
                "01"
                ,
                sixPeopleRoom.getCharacter01(),
                sixPeopleRoom.getCharacter02(),
                sixPeopleRoom.getCharacter03(),
                sixPeopleRoom.getCharacter04(),
                sixPeopleRoom.getCharacter05(),
                sixPeopleRoom.getCharacter06()
        ));
//        recordDao.save(new Record(
//                CreateNewUserId.addRaceNum(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter01())),
//                sixPeopleRoom.getCharacter01(),
//                new Timestamp(System.currentTimeMillis()),
//                "中国",
//                "basketball"));
//        recordDao.save(new Record(
//                CreateNewUserId.addRaceNum(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter02())),
//                sixPeopleRoom.getCharacter02(),
//                new Timestamp(System.currentTimeMillis()),
//                "中国",
//                "basketball"));
//        recordDao.save(new Record(
//                CreateNewUserId.addRaceNum(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter03())),
//                sixPeopleRoom.getCharacter03(),
//                new Timestamp(System.currentTimeMillis()),
//                "中国",
//                "basketball"));
//        recordDao.save(new Record(
//                CreateNewUserId.addRaceNum(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter04())),
//                sixPeopleRoom.getCharacter04(),
//                new Timestamp(System.currentTimeMillis()),
//                "中国",
//                "basketball"));
//        recordDao.save(new Record(
//                CreateNewUserId.addRaceNum(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter05())),
//                sixPeopleRoom.getCharacter05(),
//                new Timestamp(System.currentTimeMillis()),
//                "中国",
//                "basketball"));
//        recordDao.save(new Record(
//                CreateNewUserId.addRaceNum(recordDao.findUserMaxRecord(sixPeopleRoom.getCharacter06())),
//                sixPeopleRoom.getCharacter06(),
//                new Timestamp(System.currentTimeMillis()),
//                "中国",
//                "basketball"));
        return true;

    }


    @Override
    public List<Record> findByPage(Integer currPage) {
        List<Record> list = recordDao.findByPage(0, pageSize);
        return list;
    }
}
