package com.dhsys.web.persistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dhsys.web.entity.TModule;
import com.dhsys.web.pagemodel.P_Tree;
import com.dhsys.web.utils.GlobalConstant;

 
 
@Repository
public class SysModuleDao extends IHibernateDao<TModule>{

	public List getModuleTree(long adminFlag){
		List moduleList = new ArrayList();
		List<P_Tree> treeList = new ArrayList<P_Tree>();
		try{
		 if(adminFlag==GlobalConstant.ADMIN){
		    moduleList = this.getHibernateDao().find("From TModule");
		    if(moduleList.size() > 0){
		    	P_Tree p_Tree = null;
		    	 
		    	for(int i=0;i<moduleList.size();i++){
		    		p_Tree = new P_Tree();
		    		TModule module = (TModule) moduleList.get(i);
		    		p_Tree.setId(module.getModuleId());
		    		p_Tree.setText(module.getModuleName());
		    	    p_Tree.setIconCls(module.getModuleIcon());
		    	    
		    	    if(module.getpModule()!=null){
		    	    	p_Tree.setState("open");
		    	    	p_Tree.setPid(module.getpModule().getModuleId());
		    	     
		    		}else{
		    			p_Tree.setState("closed");
		    		}
		    	    
		    	    Map<String, Object> attr = new HashMap<String, Object>();
					attr.put("url", module.getModulePath());
					p_Tree.setAttributes(attr);
					treeList.add(p_Tree);
		    	}
		    }
		 }
		 	
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		return treeList;
		
	}
	
	 
}
