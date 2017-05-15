package team.lw.arena.dao.impl;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.StateDao;
import team.lw.arena.entity.Comment;
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

    @Override
    public void saveComment(Comment comment) {
        this.getHibernateTemplate().save(comment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> findByPageComments(int begin, int pageSize, String Sid) {
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Comment.class);
        detachedCriteria.add(Restrictions.eq("commentsId",Sid))
                .addOrder(Order.desc("commentTime"));
        return (List<Comment>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<State> findByPageState(int begin, int pageSize, String uid) {
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(State.class);
        detachedCriteria.add(Restrictions.eq("id",uid))
        .addOrder(Order.desc("date"));
        return  (List<State>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<State> getHotStates(int begin, int pageSize) {
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(State.class);
        detachedCriteria.addOrder(Order.desc("like"));
        return (List<State>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }
}
