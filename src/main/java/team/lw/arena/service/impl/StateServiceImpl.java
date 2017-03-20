package team.lw.arena.service.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.StateDao;
import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;
import team.lw.arena.service.StateService;
import team.lw.arena.util.CreateNewUserId;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
public class StateServiceImpl implements StateService {

    @Resource(name = "stateDaoImpl")
    private StateDao stateDao;
    private int pageSize =6;
    @Override
    public String addState(State state) {
        String stateId=CreateNewUserId.crateStateId(stateDao.getMaxSid(state.getId()));
        state.setsId(stateId);
        state.setCommentsId(stateId);
        state.setDate(new Timestamp(System.currentTimeMillis()));
        state.setLike(0);
        state.setPosition("China");
        stateDao.save(state);
        return stateId;
    }

    @Override
    public void addLike(String sid) {
        State state=stateDao.findObjectById(sid);
        state.setLike(state.getLike()+1);
        stateDao.update(state);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        stateDao.saveComment(comment);
    }

    @Override
    public void deleteState(String sid) {
        stateDao.delete(sid);
    }

    @Override
    public List<State> findAllState(Integer currPage,String uid) {
        return  stateDao.findByPageState((currPage-1)*pageSize,pageSize,uid);
    }
}
