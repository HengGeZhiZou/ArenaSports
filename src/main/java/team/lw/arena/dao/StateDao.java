package team.lw.arena.dao;


import team.lw.arena.entity.State;


public interface StateDao extends BaseDao<State> {

    String getMaxSid(String uid);

}
