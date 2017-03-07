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

    @Override
    public boolean addSixRoomRecord(SixPeopleRoom sixPeopleRoom) {
        recordDao.save(new Record(
                new Timestamp(System.currentTimeMillis()),
                "ÖÐ¹ú",
                "ÀºÇò",
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
    public List<Record> findByPage(Integer currPage,String uid) {
        List<Record> list = recordDao.findByPage(pageSize*currPage, pageSize,uid);
        return list;
    }
}
