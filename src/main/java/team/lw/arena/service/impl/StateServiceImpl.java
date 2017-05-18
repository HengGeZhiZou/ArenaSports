package team.lw.arena.service.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.StateDao;
import team.lw.arena.dao.UserInfoDao;
import team.lw.arena.entity.Comment;
import team.lw.arena.entity.State;
import team.lw.arena.entity.UserInfo;
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

    @Resource(name = "userInfoDaoImpl")
    private UserInfoDao userInfo;


    private int pageSize = 6;

    private int hotStatePageSize = 6;

    @Override
    public String addState(State state) {
        String stateId = CreateNewUserId.crateStateId(stateDao.getMaxSid(state.getId()));
        state.setsId(stateId);
        state.setCommentsId(stateId);
        state.setDate(new Timestamp(System.currentTimeMillis()));
        state.setLike(0);
        state.setPosition("China");
        stateDao.save(state);
        return stateId;
    }

    @Override
    public void addPics(String sid,String picUrl) {
        State state=stateDao.findObjectById(sid);
        state.setPhoto(picUrl);
        stateDao.update(state);
    }

    @Override
    public void addLike(String sid) {
        State state = stateDao.findObjectById(sid);
        state.setLike(state.getLike() + 1);
        stateDao.update(state);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        stateDao.saveComment(comment);
    }

    @Override
    public List<Comment> getAllComment(Integer currPage, String sid) {

       List<Comment> lists= stateDao.findByPageComments((currPage - 1) * pageSize, pageSize, sid);
       for (int i=0;i<lists.size();i++){
           UserInfo user=userInfo.findUserInfo(lists.get(i).getReviewerId());
           lists.get(i).setUsername(user.getName());
           lists.get(i).setImg(user.getPortrait());
       }
        return lists;
    }

    @Override
    public List<State> getHotStates(int currPage) {
        List<State> states = stateDao.getHotStates((currPage - 1) * hotStatePageSize, hotStatePageSize);
        for (int i=0;i<states.size();i++){
            UserInfo user=userInfo.findUserInfo(states.get(i).getId());
            int browseTimes=states.get(i).getBrowseTimes();
            states.get(i).setBrowseTimes(++browseTimes);
            stateDao.update(states.get(i));
            states.get(i).setUsername(user.getName());
            states.get(i).setPortrait(user.getPortrait());
        }
        return states;
    }

    @Override
    public void deleteState(String sid) {
        stateDao.delete(sid);
    }

    @Override
    public List<State> findAllState(Integer currPage, String uid) {
        List<State> states = stateDao.findByPageState((currPage - 1) * pageSize, pageSize, uid);
        for (int i = 0; i < states.size(); i++) {
            UserInfo user = userInfo.findUserInfo(uid);
            int browseTimes=states.get(i).getBrowseTimes();
            states.get(i).setBrowseTimes(++browseTimes);
            stateDao.update(states.get(i));
            states.get(i).setUsername(user.getName());
            states.get(i).setPortrait(user.getPortrait());
        }
        return states;
    }
}
