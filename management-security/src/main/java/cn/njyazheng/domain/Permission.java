package cn.njyazheng.domain;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-06-09
*/
@Table(name="test.permission")
public class Permission   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_available = "available";
	public static final String ALIAS_parentid = "parentid";
	public static final String ALIAS_sort = "sort";
	public static final String ALIAS_type = "type";
	public static final String ALIAS_name = "name";
	public static final String ALIAS_url = "url";
	
	private Integer id ;
	/*
	0-可用,1-不可用
	*/
	private Integer available ;
	private Integer parentid ;
	private Integer sort ;
	/*
	0-menu,1-button
	*/
	private Integer type ;
	private String name ;
	private String url ;
	
	public Permission() {
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
	
	public Integer getParentid(){
		return  parentid;
	}
	public void setParentid(Integer parentid ){
		this.parentid = parentid;
	}
	
	public Integer getSort(){
		return  sort;
	}
	public void setSort(Integer sort ){
		this.sort = sort;
	}
	
	/**
	* 0-menu,1-button
	*@return 
	*/
	public Integer getType(){
		return  type;
	}
	/**
	* 0-menu,1-button
	*@param  type
	*/
	public void setType(Integer type ){
		this.type = type;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public String getUrl(){
		return  url;
	}
	public void setUrl(String url ){
		this.url = url;
	}
	

}
