package com.yuqing.daoImpl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.ReplyDAO;
import com.yuqing.model.Interest;
import com.yuqing.model.Reply;
import com.yuqing.model.ReplyCountAndTime;


public class ReplyDAOImpl extends BaseHibernateDAOImpl implements ReplyDAO{
	
	private static final Logger log = LoggerFactory.getLogger(ReplyDAOImpl.class);

	public void save(Reply transientInstance) {
		log.debug("saving Reply instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Reply persistentInstance) {
		log.debug("deleting Reply instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reply findById(java.lang.Integer id) {
		log.debug("getting Reply instance with id: " + id);
		try {
			Reply instance = (Reply) getSessionFactory().getCurrentSession().get("com.yuqing.model.Reply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Reply instance) {
		log.debug("finding Reply instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.model.Reply")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Reply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reply as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findAll() {
		log.debug("finding all Reply instances");
		try {
			String queryString = "from Reply";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Reply merge(Reply detachedInstance) {
		log.debug("merging Reply instance");
		try {
			Reply result = (Reply) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reply instance) {
		log.debug("attaching dirty Reply instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reply instance) {
		log.debug("attaching clean Role instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Reply> getRepliesByOpinionId(int opinionId) {
		String queryString = "from Reply r where r.opinion.opinionId=?";
		Query q = getSessionFactory().getCurrentSession().createQuery(queryString);
		q.setParameter(0, opinionId);
		return q.list();
	}

	@Override
	public Integer getCountByOpinionIdAndRegion(int opinionId, String location) {
		String queryString = "select count(*) from Reply r where r.opinion.opinionId=? and region like ?";
		Query q = getSessionFactory().getCurrentSession().createQuery(queryString);
		q.setParameter(0, opinionId);
		q.setParameter(1, "%"+location+"%");
		Object o = q.uniqueResult();
		return new Integer(((Long)o).intValue());
	}


	public List<ReplyCountAndTime> getTimeByInterest(Interest i) {
	//	String queryString = "select r.time from Reply r where r.opinion.title like ? and r.time order by r.time";
	//	queryString = "COUNT(DATE_FORMAT(r.time,'%y-%m-%d')), DATE_FORMAT(r.time,'%Y-%m-%d') FROM Reply r WHERE r.content like ? GROUP BY  DATE_FORMAT(r.time,'%Y-%m-%d') ORDER BY r.time";
	//	Query q = getSessionFactory().getCurrentSession().createQuery(queryString);
		String qStr = "SELECT COUNT(DATE_FORMAT(TIME,'%y-%m-%d')), DATE_FORMAT(TIME,'%Y-%m-%d') FROM reply WHERE content like ? GROUP BY  DATE_FORMAT(TIME,'%Y-%m-%d') ORDER BY TIME";
		SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery(qStr);
		sqlQuery.setParameter(0, "%"+ i.getContent()+"%");
		List obs = sqlQuery.list();
		List<ReplyCountAndTime> l = new ArrayList<ReplyCountAndTime>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Object o:obs) {
			Object[] oa = (Object[]) o;
			Date date = null;
			try {
				date = sdf.parse(oa[1]+"");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			l.add(new ReplyCountAndTime(((BigInteger)oa[0]).intValue(),date));
		}
	//	System.out.println(l);
		return l;
	}

	@Override
	public List<ReplyCountAndTime> getCountAndTimeByOpinionId(int opinionId) {
		String qStr = "SELECT COUNT(DATE_FORMAT(TIME,'%y-%m-%d %H')), DATE_FORMAT(TIME,'%Y-%m-%d %H') FROM reply WHERE targetid=? GROUP BY  DATE_FORMAT(TIME,'%Y-%m-%d %H') ORDER BY DATE_FORMAT(TIME,'%Y-%m-%d %H')";
		SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery(qStr);
		sqlQuery.setParameter(0, opinionId);
		List obs = sqlQuery.list();
		List<ReplyCountAndTime> l = new ArrayList<ReplyCountAndTime>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh");
		for(Object o:obs) {
			Object[] oa = (Object[]) o;
			Date date = null;
			try {
				date = sdf.parse(oa[1]+"");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			l.add(new ReplyCountAndTime(((BigInteger)oa[0]).intValue(),date));
		}
	//	System.out.println(l);
		return l;
	}
}
