package team.lw.arena.service;

import team.lw.arena.entity.Record;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;

import java.util.List;

public interface RecordService {
    List<Record> findByPage(Integer currPage);

    int getCount();

    boolean addSixRoomRecord(SixPeopleRoom sixPeopleRoom) throws ServiceException;
}
