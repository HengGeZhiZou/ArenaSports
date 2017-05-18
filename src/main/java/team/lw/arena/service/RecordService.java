package team.lw.arena.service;

import team.lw.arena.entity.Record;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;

import java.util.List;

public interface RecordService {

    List<Record> findByPage(Integer currPage,String uid);

    List<Record> currRecord(int currPage);

    boolean addSixRoomRecord(SixPeopleRoom sixPeopleRoom,String district,String type);

    Record findByRecordID(String rid);

}
