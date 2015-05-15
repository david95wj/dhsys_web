package com.dhsys.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;



 

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TRole;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_SysUser;
import com.dhsys.web.persistent.DeptDao;
import com.dhsys.web.persistent.SysRoleDao;
import com.dhsys.web.persistent.SysUserDao;
 

/**
 * 系统用户服务层
 * @author wangyuxiang
 *
 */
@Service
public class SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private DeptDao deptDao;
	/**
	 * 查询系统用户是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	public TUser isExistsService(String userName,String password){
		  return sysUserDao.isExistsDao(userName, password);
	}
	
	/**
	 * 查询系统用户 支持分页查询
	 * @param page
	 * @param rows
	 * @param user
	 * @return pageList 系统用户集合
	 */
	public List<TUser> findSysUserService(String page, String rows, P_SysUser user){
		List userList = new ArrayList();
		List pageList = new ArrayList();
		try{
		  String sql = "select u.userid,u.useraccount,u.createdate,u.adminflag,u.userip,d.deptname from DH_SYSUSER u left join DH_DEPT d on u.deptid = d.deptid where 1 = 1";	
		  if(user!=null){
			  if(user.getUserAccount()!=null&&!user.getUserAccount().equals("")){
				  sql += " and u.userAccount = '" + user.getUserAccount() + "'"; 
			  }
			  if(user.getAdminFlag()!=null&&!user.getAdminFlag().equals("")){
				  sql += " and u.adminFlag = '" + user.getAdminFlag() + "'";
			  }
			  if(user.getCreateDate()!=null&&!user.getCreateDate().equals("")){
				  sql += " and u.createDate = '" + user.getCreateDate() + "'";
			  }
			  if(user.getDeptId()!=null&&!user.getDeptId().equals("")){
				  sql += " and u.deptId = " + user.getDeptId();
			  }
			  sql += " order by u.userId";
			  int currentPage = Integer
						.parseInt((page == null || page == "0") ? "1" : page); 
			  int pageSize = Integer
						.parseInt((rows == null || rows == "0") ? "10" : rows);
			  userList = sysUserDao.findUsersDao(sql, currentPage, pageSize);
			  if(userList.size() > 0){
				  P_SysUser p_user = null;
				  for(int i=0;i<userList.size();i++){
					  p_user = new P_SysUser();
					  Object[] object = (Object[]) userList.get(i);
					  p_user.setUserId(Integer.valueOf(object[0].toString()));
					  p_user.setUserAccount(object[1]==null?"":object[1].toString());
					  p_user.setCreateDate(object[2]==null?"":object[2].toString());
					  p_user.setAdminFlag(object[3]==null?"":object[3].toString());
					  p_user.setUserIp(object[4]==null?"":object[4].toString());
					  p_user.setDeptName(object[5]==null?"":object[5].toString());
					  pageList.add(p_user);
				  }
			  }
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageList;
		
	 }
	
	/**
	 * 删除系统用户
	 * @param userId
	 * @return 删除标识,若删除失败需要说明原因
	 */
	public Map deleteUserByIdService(String userId){
	    Map respMap = new HashMap();
		boolean delFlag = sysUserDao.deleteUserByIdDao(userId);
		if(delFlag){
			respMap.put("success",true);
			respMap.put("msg", "用户删除成功");
		}else{
			respMap.put("success",false);
			respMap.put("reason", "该系统用户下有角色信息");
			respMap.put("msg", "用户删除失败");
		}
		return respMap;
	}
	
	/**
	 * 释放系统用户下的角色信息
	 * @param userId
	 * @return
	 */
	public Map releaseRoleService(String userId){
		 Map respMap = new HashMap();
			boolean releaseFlag = sysUserDao.releaseRoleDao(userId);
			if(releaseFlag){
				respMap.put("success",true);
				respMap.put("msg", "角色释放成功");
			}else{
				respMap.put("success",false);
				respMap.put("reason", "该系统用户下没有角色信息");
				respMap.put("msg", "角色释放失败");
			}
			return respMap;
	}
	
	/**
	 * 新增系统用户
	 * @param user
	 * @return
	 */
	public boolean addUserService(P_SysUser user){
		boolean addFlag = false;
		try{
		  TUser u = new TUser();
		  BeanUtils.copyProperties(user, u);
		  List<TRole> roles = new ArrayList<TRole>();
			if (user.getRoleIds() != null) {
				for (String roleId : user.getRoleIds().split(",")) {
					roles.add(sysRoleDao.getRoleById(Integer.valueOf(roleId)));
				}
			}
		  u.setRoles(new HashSet<TRole>(roles));
		  Integer deptId = user.getDeptId();
		  TDept dept = deptDao.getDeptByIdDao(deptId);
		  u.setDh_Dept(dept);
		  addFlag = sysUserDao.addUserDao(u);
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		return addFlag;
	}
	
	/**
	 * 加载系统用户
	 * @return
	 */
	public P_SysUser getUserByIdService(Integer userId){
		 P_SysUser u = new P_SysUser();
		try{
		 TUser user = sysUserDao.getUserByIdDao(userId);
		
		 BeanUtils.copyProperties(user, u);
		 if(user.getDh_Dept()!=null){
			 u.setDeptId(user.getDh_Dept().getDeptId());
			 u.setDeptName(user.getDh_Dept().getDeptName());
		 }
		 if (user.getRoles() != null && !user.getRoles().isEmpty()) {
				String roleIds = "";
				String roleNames = "";
				boolean b = false;
				for (TRole role : user.getRoles()) {
					if (b) {
						roleIds += ",";
						roleNames += ",";
					} else {
						b = true;
					}
					roleIds += role.getRoleId();
					roleNames += role.getRoleName();
				}
				u.setRoleIds(roleIds);
				u.setRoleNames(roleNames);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * 更新系统用户
	 * @param user
	 * @return
	 */
	public boolean updateUserService(P_SysUser user){
		 boolean updateFlag = false;
		if(user!=null){
			TUser u = new TUser();
			BeanUtils.copyProperties(user,u); 
	        TDept dept = deptDao.getDeptByIdDao(user.getDeptId());
	        u.setDh_Dept(dept);
	        List<TRole> roles = new ArrayList<TRole>();
			if (user.getRoleIds() != null) {
				for (String roleId : user.getRoleIds().split(",")) {
					roles.add(sysRoleDao.getRoleById(Integer.valueOf(roleId)));
				}
			}
		    u.setRoles(new HashSet<TRole>(roles));
	        updateFlag = sysUserDao.updateUserDao(u);
		    
		}
		return updateFlag;
	}
	
}
