package team.lw.arena.dao;


import team.lw.arena.entity.Record;

import java.util.List;

public interface RecordDao extends BaseDao<Record> {

     int findCount();      //��ȡ������

    List<Record> findByPage(int begin, int end);  //����ҳ����Ҽ�¼

    String findUserMaxRecord(String uid);
}
