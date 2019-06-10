package cn.njyazheng.dao;
import org.beetl.sql.core.mapper.BaseMapper;
import cn.njyazheng.domain.*;

import java.util.List;


/*
* 
* gen by beetlsql mapper 2019-06-09
*/
public interface PermissionDao extends BaseMapper<Permission> {
	
    List<Permission> getPermissionsByUsername(String name);
}
