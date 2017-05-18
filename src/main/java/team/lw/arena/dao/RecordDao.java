package team.lw.arena.dao;


import team.lw.arena.entity.Record;

import java.util.List;

public interface RecordDao extends BaseDao<Record> {

    String getMaxRecordID(String ownerID);

    List<Record> findByPage(int begin, int pageSize,String uid);  //按照页码查找记录

    List<Record> findCurrRecord(int begin, int pageSize);  //查找最近进行的比赛

}
