package test;
 

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.dhsys.web.entity.TUser;
import com.dhsys.web.persistent.SysModuleDao;
import com.dhsys.web.persistent.SysUserDao;
import com.dhsys.web.service.SysUserService;
 

//@ContextConfiguration(locations={"file:WebContent/WEB-INF/service.xml","file:WebContent/WEB-INF/persistent.xml"})   
public class ServiceTest {//extends AbstractTestNGSpringContextTests{

	 @Autowired
	 private SysUserService service;
	 
	 @Autowired
	 private SysUserDao dao;
	 
	 @Autowired
	 private SysModuleDao sDao;
	
	 public void testlogin(){
		 TUser user = new TUser();
		 user.setUserAccount("190144");
		 user.setPassword("123");
		/* boolean flag =  service.isExistsService(user);
	     if(flag){
	    	 System.out.println("user is login success!");
	     }else{
	    	 System.out.println("user is login fail!");
	     }*/
	    
	 }
	 
	 
	 
 
	 
	 
	 
	 public void testDelUser(){
		 
	 }
	 
	 @Test
	 public void testModule(){
		List list = sDao.getModuleTree(0);
	 }

}
