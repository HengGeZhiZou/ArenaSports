package team.lw.arena.service;


import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;

import java.util.List;

public interface StateService {

    String addState(State state);  //��Ӷ�̬

    void addPics(String sid,String picUrl);

    void addLike(String sid);  //����

    void addComment(Comment comment);  //�������

    List<Comment> getAllComment(Integer currPage,String Sid); //���ݶ�̬ID��ȡ������̬������

    List<State> getHotStates(int currPage);  //��ȡ�����ŵĶ�̬

    void deleteState(String sid);

    List<State> findAllState(Integer currPage,String uid);  //��ȡ���û������ж�̬
}
