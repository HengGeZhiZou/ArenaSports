package team.lw.arena.dao.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.GameDao;
import team.lw.arena.entity.SixPeopleRoom;

@Repository
@Scope("prototype")
public class GameDaoImpl extends BaseDaoImpl<SixPeopleRoom> implements GameDao{

}
