package team.lw.arena.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.RecordDao;
import team.lw.arena.entity.Record;

import java.util.List;

@Repository
@Scope("prototype")
public class RecordDaoImpl extends BaseDaoImpl<Record> implements RecordDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Record> findByPage(int begin, int pageSize, String uid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Record.class);
        detachedCriteria.add(Restrictions.or(
                Restrictions.eq("id01", uid),
                Restrictions.eq("id02", uid),
                Restrictions.eq("id03", uid),
                Restrictions.eq("id04", uid),
                Restrictions.eq("id05", uid),
                Restrictions.eq("id06", uid)
        ))
                .addOrder(Order.asc("date"));
        return (List<Record>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
    }

}
