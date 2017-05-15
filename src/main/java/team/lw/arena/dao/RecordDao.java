package team.lw.arena.dao;


import team.lw.arena.entity.Record;

import java.util.List;

public interface RecordDao extends BaseDao<Record> {

    String getMaxRecordID(String ownerID);

    List<Record> findByPage(int begin, int pageSize,String uid);  //����ҳ����Ҽ�¼

}
