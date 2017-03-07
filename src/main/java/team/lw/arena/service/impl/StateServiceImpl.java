package team.lw.arena.service.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.StateDao;
import team.lw.arena.entity.State;
import team.lw.arena.service.StateService;
import team.lw.arena.util.CreateNewUserId;

import javax.annotation.Resource;

@Service
@Transactional
@Scope("prototype")
public class StateServiceImpl implements StateService {

    @Resource(name = "stateDaoImpl")
    private StateDao stateDao;

    @Override
    public boolean addState(State state) {
        String stateId=CreateNewUserId.crateStateId(stateDao.getMaxSid(state.getId()));
        state.setsId(stateId);
        state.setCommentsId(stateId);
        stateDao.save(state);
        return true;
    }

    @Override
    public boolean addLike(State state) {
        stateDao.update(state);
        return false;
    }

    @Override
    public boolean addComment(State state) {
        stateDao.update(state);
        return false;
    }
}
