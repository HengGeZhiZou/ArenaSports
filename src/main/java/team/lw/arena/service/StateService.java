package team.lw.arena.service;


import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;

import java.util.List;

public interface StateService {

    String addState(State state);  //添加动态

    void addLike(String sid);  //点赞

    void addComment(Comment comment);  //添加评论

    void deleteState(String sid);

    List<State> findAllState(Integer currPage,String uid);
}
