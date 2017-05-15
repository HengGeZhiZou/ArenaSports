package team.lw.arena.service;


import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;

import java.util.List;

public interface StateService {

    String addState(State state);  //添加动态

    void addPics(String sid,String picUrl);

    void addLike(String sid);  //点赞

    void addComment(Comment comment);  //添加评论

    List<Comment> getAllComment(Integer currPage,String Sid); //根据动态ID获取该条动态的评论

    List<State> getHotStates(int currPage);  //获取最热门的动态

    void deleteState(String sid);

    List<State> findAllState(Integer currPage,String uid);  //获取本用户的所有动态
}
