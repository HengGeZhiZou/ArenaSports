package team.lw.arena.service;


import team.lw.arena.entity.State;

public interface StateService {

    boolean addState(State state);  //添加动态

    boolean addLike(State state);  //点赞

    boolean addComment(State state);  //添加评论
}
