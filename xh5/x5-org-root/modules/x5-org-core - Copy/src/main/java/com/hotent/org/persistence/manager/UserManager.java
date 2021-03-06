package com.hotent.org.persistence.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.manager.api.Manager;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.UserQuery;
@Service
public interface UserManager extends Manager<String, DefaultUser>{
	
	/**
	 * 根据账号获取用户信息
	 * @param account
	 * @return
	 */
	public DefaultUser getByAccount(String account);
	
	/**
	/**
	 * 根据关联的用户Id、关系类型, 获取与该用户具有某种关系类型的用户列表
	 * <p>
	 * 	如果关系类型是单向的关系类型，取所有被关联用户为指定用户的关联用户.<br/>
	 *  如果关系类型是双向的关系类型，取所有关联用户或被关联用户是指定的用户的对端用户
	 * </p>
	 * @param userKey
	 * @param relType
	 * @return
	 */
	public List<DefaultUser> queryByUserRelation(String userKey, String relType);
	
	
	/**
	 * 根据关联的用户Id、关系类型, 获取与该用户具有某种关系类型的用户列表
	 * <p>
	 * 	如果关系类型是单向的关系类型，取所有关联用户为指定用户的被关联用户.<br/>
	 *  如果关系类型是双向的关系类型，取所有关联用户或被关联用户是指定的用户的对端用户
	 * </p>
	 * @param relUserId
	 * @param relType
	 * @return
	 */
	List<DefaultUser> queryByUserRelationRel(String relUserId, String relType);
	
	/**
	 * 根据用户组Key 关系类型
	 * 获取用户组有某种关系的用户列表
	 * @param groupKey
	 * @param relType
	 * @return
	 */
	public List<DefaultUser> queryByUserGroupRelation(String groupKey, String relType);
	
	/**
	 * 获取具有某种属性值的用户列表
	 * @param key
	 * @param value
	 * @return
	 */
	public List<DefaultUser> queryUserByAttributeValue(String key, Object value);
	
	

	/**
	 * 获取用户列表
	 * @param query
	 * @return
	 */
	List<DefaultUser> queryByCriteria(UserQuery query);
	
	/**
	 * 获取用户分页列表
	 * @param query
	 * @param page
	 * @return
	 */
	List<DefaultUser> queryByCriteria(UserQuery query, Page page);



}
