package team.lw.arena.service;


import team.lw.arena.entity.State;

public interface StateService {

    boolean addState(State state);  //��Ӷ�̬

    boolean addLike(State state);  //����

    boolean addComment(State state);  //�������
}
