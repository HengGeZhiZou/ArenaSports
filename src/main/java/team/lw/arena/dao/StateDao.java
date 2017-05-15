package team.lw.arena.dao;


import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;

import java.util.List;


public interface StateDao extends BaseDao<State> {

    String getMaxSid(String uid);  //  查找当前用户的动态最大id

    void saveComment(Comment comment);   //保存每一条评论

    List<Comment> findByPageComments(int begin,int pageSize,String Sid); // 获取sid动态的评论

    List<State> findByPageState(int begin,int pageSize,String sid); //获取该用户的所有动态

    List<State> getHotStates(int begin,int pageSize);   //获取热门动态

}
