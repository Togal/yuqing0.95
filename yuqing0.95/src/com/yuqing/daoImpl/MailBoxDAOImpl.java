package com.yuqing.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.MailBoxDAO;
import com.yuqing.model.MailBox;



public class MailBoxDAOImpl extends BaseHibernateDAOImpl implements MailBoxDAO{
	private static final Logger log = LoggerFactory.getLogger(MailBoxDAO.class);
	// property constants
	public static final String EMAIL_NAME = "emailName";
	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String TOF = "tof";

//	public void add(MailBox transientInstance) {
//		System.out.println("测试1");
//		Session session = getSessionFactory().getCurrentSession();
//		String sql = "insert into mail_box values(emailName,emailAddress)";
//		Query q = session.createQuery(sql);
//	}
	
	public void save(MailBox transientInstance) {
		log.debug("saving MailBox instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MailBox persistentInstance) {
		log.debug("deleting MailBox instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MailBox findById(java.lang.Integer id) {
		log.debug("getting MailBox instance with id: " + id);
		try {
			MailBox instance = (MailBox) getSessionFactory().getCurrentSession().get("com.yuqing.MailBox",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MailBox instance) {
		log.debug("finding MailBox instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.MailBox")
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
		log.debug("finding MailBox instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MailBox as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEmailName(Object emailName) {
		return findByProperty(EMAIL_NAME, emailName);
	}

	public List findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	public List findByTof(Object tof) {
		return findByProperty(TOF, tof);
	}

	public List findAll() {
		log.debug("finding all MailBox instances");
		try {
			String queryString = "from MailBox";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MailBox merge(MailBox detachedInstance) {
		log.debug("merging MailBox instance");
		try {
			MailBox result = (MailBox) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MailBox instance) {
		log.debug("attaching dirty MailBox instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MailBox instance) {
		log.debug("attaching clean MailBox instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public boolean delete(Integer id) {
		String str = "delete from MailBox m where m.emailId=?";
		Query q = getSessionFactory().getCurrentSession().createQuery(str);
		q.setParameter(0, id);
		q.executeUpdate();
		return q.executeUpdate()>0?true:false;
	}
}