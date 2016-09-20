package com.yuqing.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.WebsiteDAO;
import com.yuqing.model.Website;

public class WebsiteDAOImpl extends BaseHibernateDAOImpl implements WebsiteDAO{
	private static final Logger log = LoggerFactory.getLogger(WebsiteDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String HOME_PAGE = "homePage";
	public static final String CATEGORY = "category";

	public void save(Website transientInstance) {
		log.debug("saving Website instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Website persistentInstance) {
		log.debug("deleting Website instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Website findById(java.lang.Integer id) {
		log.debug("getting Website instance with id: " + id);
		try {
			Website instance = (Website) getSessionFactory().getCurrentSession().get(
					"com.yuqing.model.Website", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Website instance) {
		log.debug("finding Website instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.model.Website")
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
		log.debug("finding Website instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Website as model where model."
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

	public List findByHomePage(Object homePage) {
		return findByProperty(HOME_PAGE, homePage);
	}

	public List findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List findAll() {
		log.debug("finding all Website instances");
		try {
			String queryString = "from Website";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Website merge(Website detachedInstance) {
		log.debug("merging Website instance");
		try {
			Website result = (Website) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Website instance) {
		log.debug("attaching dirty Website instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Website instance) {
		log.debug("attaching clean Website instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Website> getAll() {
		List<Website> webs = new ArrayList<Website>();
		Session session = getSessionFactory().getCurrentSession();
		String sql = "select w.webSiteId,w.name from Website as w";
		Query q = session.createQuery(sql);
		List<Object[]> olist =  q.list();
		for(Object[] os : olist) {
			Website w = new Website();
			w.setWebSiteId((Integer)os[0]);
			w.setName((String)os[1]);
			webs.add(w);
		}
		return webs;
	}

	@Override
	public int getOpinionCountById(Integer webSiteId) {
		Session session = getSessionFactory().getCurrentSession();
		String sql = "select count(*) from com.yuqing.model.Opinion as o where o.website.webSiteId=?";
		Query q = session.createQuery(sql);
		q.setParameter(0, webSiteId);
		Object o = q.uniqueResult();
		return new Integer(((Long)o).intValue());
	}
}