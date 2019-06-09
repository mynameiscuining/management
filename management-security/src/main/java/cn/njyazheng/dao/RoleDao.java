package cn.njyazheng.dao;
import org.beetl.sql.core.annotatoin.*;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import cn.njyazheng.domain.*;

import java.util.List;

/*
* 
* gen by beetlsql mapper 2019-06-08
*/
public interface RoleDao extends BaseMapper<Role> {
	List<Role> getRolesByUsername(String username);
}
