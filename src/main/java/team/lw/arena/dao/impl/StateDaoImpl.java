package team.lw.arena.dao.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.StateDao;
import team.lw.arena.entity.State;

import java.util.List;

@Repository
@Scope("prototype")
public class StateDaoImpl extends BaseDaoImpl<State> implements StateDao{

    @Override
    @SuppressWarnings("unchecked")
    public String getMaxSid(String uid) {
        String hql="select max(sId) from State where id=?";
        List<String> list= (List<String>) this.getHibernateTemplate().find(hql,uid);
        if (list.get(0) != null) return list.get(0);
        return uid+"0000";
    }
}
