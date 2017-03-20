package team.lw.arena.service;


import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;

import java.util.List;

public interface StateService {

    String addState(State state);  //��Ӷ�̬

    void addLike(String sid);  //����

    void addComment(Comment comment);  //�������

    void deleteState(String sid);

    List<State> findAllState(Integer currPage,String uid);
}
