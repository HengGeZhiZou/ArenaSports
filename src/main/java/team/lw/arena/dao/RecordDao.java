package team.lw.arena.dao;


import team.lw.arena.entity.Record;

import java.util.List;

public interface RecordDao extends BaseDao<Record> {

     int findCount();      //获取总条数

    List<Record> findByPage(int begin, int end);  //按照页码查找记录

    String findUserMaxRecord(String uid);
}
