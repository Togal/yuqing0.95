package com.yuqing.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.PowerDAO;
import com.yuqing.model.Power;


public class PowerDAOImpl extends BaseHibernateDAOImpl implements PowerDAO{
	private static final Logger log = LoggerFactory.getLogger(PowerDAOImpl.class);

	// property constants

	public void save(Power transientInstance) {
		log.debug("saving Power instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Power persistentInstance) {
		log.debug("deleting Power instance");
		try {
			System.out.println(persistentInstance.getPowerId());
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			
			System.out.println("success");
			log.debug("delete successful");
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			log.error("delete failed", re);
			throw re;
		}
	}

	public Power findById(java.lang.Integer id) {
		log.debug("getting Power instance with id: " + id);
		try {
			Power instance = (Power) getSessionFactory().getCurrentSession()
					.get("com.yuqing.model.Power", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Power instance) {
		log.debug("finding Power instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("com.yuqing.model.Power")
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
		log.debug("finding Power instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Power as model where model."
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
		log.debug("finding all Power instances");
		try {
			String queryString = "from Power";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Power merge(Power detachedInstance) {
		log.debug("merging Power instance");
		try {
			Power result = (Power) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Power instance) {
		log.debug("attaching dirty Power instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Power instance) {
		log.debug("attaching clean Power instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	public List findByRoleId(Integer id) {
		log.debug("finding Power instance by RoleId");
		try {
			String queryString = "from Power as model where model.role.roleId=?";
			System.out.println(queryString);
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, id);
			return queryObject.list();
		}catch(RuntimeException re) {
			log.error("find by RoleId filed",re);
			throw re;
		}
	}
	
	public void UpdatePower(Integer powerId, String op, Integer value) {
		log.debug("update power intance");
		try{
			String queryString = "update Power as model set model."+op+" = "+value+" where model.powerId=?";
			 getSessionFactory().getCurrentSession().createQuery(queryString).setParameter(0, powerId).executeUpdate();
		}catch(RuntimeException re) {
			log.error("update power failed",re);
			throw re;
		}
		
	}

	@Override
	public void deleteByModule(Integer moduleId) {
		log.debug("delete by moduleid  power intance");
		try{
			String hql = "delete from Power as power where power.module.moduleId=?";
			 getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, moduleId).executeUpdate();
		}catch(RuntimeException re) {
			log.error("delete by moduleid power failed",re);
			throw re;
		
		}
	}

	@Override
	public void addModuleRole(Power power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteModuleRole(Power power) {
		log.debug("delete by modul Role  power intance");
		try{
			String hql = "delete from Power as power where power.module.moduleId=? and power.role.roleId=?";
			 getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0,power.getModule().getModuleId()).setParameter(1, power.getRole().getRoleId()).executeUpdate();
		}catch(RuntimeException re) {
			log.error("delete by moduleid power failed",re);
			throw re;
		
		}
	}
}