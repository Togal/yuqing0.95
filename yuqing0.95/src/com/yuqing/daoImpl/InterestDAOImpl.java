package com.yuqing.daoImpl;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.InterestDAO;
import com.yuqing.model.Interest;
import com.yuqing.model.Opinion;
import com.yuqing.model.OpinionReplyCount;


public class InterestDAOImpl extends BaseHibernateDAOImpl implements InterestDAO{
	private static final Logger log = LoggerFactory
			.getLogger(InterestDAOImpl.class);
	public static final String CONTENT = "content";

	public void save(Interest transientInstance) {
		log.debug("saving Interest instance");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Interest persistentInstance) {
		log.debug("deleting Interest instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Interest instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Interest as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Interest findById(java.lang.Integer id) {
		log.debug("getting Interest instance with id: " + id);
		try {
			Interest instance = (Interest) getSessionFactory().getCurrentSession().get(
					"com.yuqing.model.Interest", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Interest instance) {
		log.debug("finding Interest instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.model.Interest")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public Interest merge(Interest detachedInstance) {
		log.debug("merging Interest instance");
		try {
			Interest result = (Interest) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Interest instance) {
		log.debug("attaching dirty Interest instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Interest instance) {
		log.debug("attaching clean Interest instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public boolean update(Interest i) {
		 Session session = getSessionFactory().getCurrentSession();
		 session.update(i);
		return true;
	}

	public boolean isExit(Integer id) {
		Session session = getSessionFactory().getCurrentSession();
		String sql = "select i.id from Interest as i where i.id="+id;
		Query q = session.createQuery(sql);
		return q.list().size()==0?false:true;
	}

	@Override
	public void delete(int id) {
		Session session = getSessionFactory().getCurrentSession();
		String sql = "delete from Interest as i where i.id="+id;
		Query q = session.createQuery(sql);
		q.executeUpdate();
		
	}

	@Override
	public List<OpinionReplyCount> getInterestTop5ByUserId(Integer userId) {
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery sql = session.createSQLQuery("{call getInterestOpinionTitleAndReplyCount(?)}");
		sql.setParameter(0, userId);
		List objs = sql.list();
		List<OpinionReplyCount> lists = new ArrayList<OpinionReplyCount>();
		for(int i=0;i<objs.size();i++) {
			OpinionReplyCount orc = new OpinionReplyCount();
			Opinion o = new Opinion();
			Object[] objss = (Object[])objs.get(i);
			orc.setReplyCount(new Integer(((BigInteger)objss[1]).intValue()));
			o.setOpinionId(new Integer((Integer)objss[0]));
			o.setTitle((String)objss[2]);
			orc.setOpinion(o);
			lists.add(orc);
		}
		return lists;
	}
	
	@Override
	public JSONArray getInterestReplyCount(Integer userId) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlStr = "SELECT i.content,COUNT(CASE WHEN r.id IS NOT NULL THEN 1 END) FROM (SELECT content FROM interest WHERE user_id = ?) AS i LEFT JOIN reply AS r ON r.targetid IN (SELECT o.opinion_id FROM opinion AS o WHERE INSTR(o.title,i.content)) GROUP BY i.content";
		SQLQuery sql = session.createSQLQuery(sqlStr);
		sql.setParameter(0, userId);
		List<Object[]> objs = sql.list();
		JSONArray jsonArray = new JSONArray();
		for(Object[] os :objs) {
			JSONArray temp = new JSONArray();
			temp.add(os[0]);temp.add(os[1]);
			jsonArray.add(temp);
		}
		return jsonArray;
	}
}