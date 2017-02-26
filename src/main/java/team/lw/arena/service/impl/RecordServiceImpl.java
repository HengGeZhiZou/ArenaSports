package team.lw.arena.service.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.RecordDao;
import team.lw.arena.entity.Record;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.service.RecordService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
public class RecordServiceImpl implements RecordService {

    @Resource(name = "recordDaoImpl")
    private RecordDao recordDao;

    private static int pageSize = 4;

    public int  getCount(){
        return recordDao.findCount();
    }

    @Override
    public boolean addSixRoomRecord(SixPeopleRoom sixPeopleRoom) {
//        recordDao.save();
        return false;
    }


    @Override
    public List<Record> findByPage(Integer currPage) {
        List<Record> list=recordDao.findByPage(0,pageSize);
        return list;
    }
}
