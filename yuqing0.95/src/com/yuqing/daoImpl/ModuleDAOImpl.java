package com.yuqing.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.ModuleDAO;
import com.yuqing.model.Module;


public class ModuleDAOImpl extends BaseHibernateDAOImpl implements ModuleDAO{
	private static final Logger log = LoggerFactory
			.getLogger(ModuleDAOImpl.class);

	public static final String NAME = "name";

	public void save(Module transientInstance) {
		log.debug("saving Category instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Module persistentInstance) {
		log.debug("deleting Category instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Module findById(java.lang.Integer id) {
		log.debug("getting Module instance with id: " + id);
		try {
			Module instance = (Module) getSessionFactory().getCurrentSession().get(
					"com.yuqing.model.Module", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Module instance) {
		log.debug("finding Module instance by example");
		try {
			List results = getSessionFactory().getCurrentSession()
					.createCriteria("com.yuqing.model.Module")
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
		log.debug("finding Module instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Module as model where model."
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

	public List findAll() {
		log.debug("finding all Module instances");
		try {
			String queryString = "from Module";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Module merge(Module detachedInstance) {
		log.debug("merging Module instance");
		try {
			Module result = (Module) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Module instance) {
		log.debug("attaching dirty Module instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Module instance) {
		log.debug("attaching clean Module instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Module> getByRoleId(Integer roleId) {
		try{
			Session session = getSessionFactory().getCurrentSession();
			String sql ="select p.module from com.yuqing.model.Power as p where p.role.roleId=? order by p.module.moduleId";
			Query query = session.createQuery(sql);
			query.setParameter(0, roleId);
			return query.list();
		}catch(RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List findParentName() {
		log.debug("finding all Module parentName ");
		try {
			String queryString = "from Module as module where module.parent.moduleId is null";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}