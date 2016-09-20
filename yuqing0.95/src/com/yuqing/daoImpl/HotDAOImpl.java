package com.yuqing.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.HotDAO;
import com.yuqing.model.Hot;


public class HotDAOImpl extends BaseHibernateDAOImpl implements HotDAO{
	private static final Logger log = LoggerFactory.getLogger(HotDAOImpl.class);
	// property constants
	public static final String WORD = "word";
	public static final String HOT_VALUE = "hotValue";

	public void save(Hot transientInstance) {
		log.debug("saving Hot instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Hot persistentInstance) {
		log.debug("deleting Hot instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Hot findById(java.lang.Integer id) {
		log.debug("getting Hot instance with id: " + id);
		try {
			Hot instance = (Hot) getSessionFactory().getCurrentSession().get("com.yuqing.model.Hot", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Hot instance) {
		log.debug("finding Hot instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.model.Hot")
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
		log.debug("finding Hot instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Hot as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWord(Object word) {
		return findByProperty(WORD, word);
	}

	public List findByHotValue(Object hotValue) {
		return findByProperty(HOT_VALUE, hotValue);
	}

	public List findAll() {
		log.debug("finding all Hot instances");
		try {
			String queryString = "from Hot";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Hot merge(Hot detachedInstance) {
		log.debug("merging Hot instance");
		try {
			Hot result = (Hot) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Hot instance) {
		log.debug("attaching dirty Hot instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Hot instance) {
		log.debug("attaching clean Hot instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}