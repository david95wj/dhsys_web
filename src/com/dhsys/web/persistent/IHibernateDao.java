package com.dhsys.web.persistent;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Hibernate 数据库接口
 * @author wangyuxiang
 * @param <T> 通用泛型接口
 * @date 2015-04-30
 */

public class IHibernateDao<T> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	private Class entityClass;

	 

	public IHibernateDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	public T get(Serializable id) {
		return (T) hibernateTemplate.get(entityClass, id);
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	public HibernateTemplate getHibernateDao() {
		return hibernateTemplate;
	}
	
	public void delete(T entity){
		hibernateTemplate.delete(entity);
	}
	

}
