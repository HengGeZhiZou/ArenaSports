package team.lw.arena.dao;


import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;

import java.util.List;


public interface StateDao extends BaseDao<State> {

    String getMaxSid(String uid);  //  ���ҵ�ǰ�û��Ķ�̬���id

    void saveComment(Comment comment);   //����ÿһ������

    List<State> findByPageState(int begin,int pageSize,String sid);
}
