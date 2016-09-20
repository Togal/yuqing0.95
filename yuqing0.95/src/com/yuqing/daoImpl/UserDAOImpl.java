package com.yuqing.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.UserDAO;
import com.yuqing.model.User;


public class UserDAOImpl extends BaseHibernateDAOImpl implements UserDAO{
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String NICK = "nick";
	public static final String AREA = "area";
	public static final String PHONE = "phone";

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update (User u) {
		try {
			Session s = getSessionFactory().getCurrentSession();
			s.update(u);
		}catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void updateRoleById(int userId,int roleId) {
		try{
			Session currentSession = getSessionFactory().getCurrentSession();
			String sql = "update User u set u.role.roleId=? where u.userId=?";
			Query q = currentSession.createQuery(sql);
			q.setInteger(0, roleId);
			q.setInteger(1, userId);
			q.executeUpdate();
		}catch (RuntimeException e) {
			throw e;
		}
		
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSessionFactory().getCurrentSession().get("com.yuqing.model.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.model.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByNick(Object nick) {
		return findByProperty(NICK, nick);
	}

	public List findByArea(Object area) {
		return findByProperty(AREA, area);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public int getTotalCount() {
		Session currentSession = getSessionFactory().getCurrentSession();
		String sql = "select count(*) from User";
		Query q = currentSession.createQuery(sql);
		Object o = q.uniqueResult();
		return new Integer(((Long)o).intValue());
	}

	@Override
	public boolean isExist(String name) {
		Session s = getSessionFactory().getCurrentSession();
		String sql = "select u.userId from User as u where u.name=?";
		Query q = s.createQuery(sql);
		q.setParameter(0, name);
		return q.uniqueResult() == null ? false:true;
	}
}