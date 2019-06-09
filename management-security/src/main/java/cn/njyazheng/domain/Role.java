package cn.njyazheng.domain;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-06-08
*/
@Table(name="test.role")
public class Role   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_available = "available";
	public static final String ALIAS_name = "name";
	
	private Integer id ;
	/*
	0-可用,1-不可用
	*/
	private Integer available ;
	private String name ;
	
	public Role() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 0-可用,1-不可用
	*@return 
	*/
	public Integer getAvailable(){
		return  available;
	}
	/**
	* 0-可用,1-不可用
	*@param  available
	*/
	public void setAvailable(Integer available ){
		this.available = available;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	

}
