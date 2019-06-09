package cn.njyazheng.domain;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-06-08
*/
@Table(name="test.user")
public class User   {
	//正常
	public static final int USER_STATUS_NORMAL=0;
	//暂停
	public static final int USER_STATUS_PAUSE=1;
	//未锁定
	public static final int USER_UNLOCKED=0;
	//锁定
	public static final int USER_LOCKED=1;
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_locked = "locked";
	public static final String ALIAS_status = "status";
	public static final String ALIAS_password = "password";
	public static final String ALIAS_username = "username";
	
	private Integer id ;
	/*
	0-未锁定,1-锁定
	*/
	private Integer locked ;
	/*
	0-正常,1-暂停
	*/
	private Integer status ;
	private String password ;
	private String username ;
	
	public User() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 0-未锁定,1-锁定
	*@return 
	*/
	public Integer getLocked(){
		return  locked;
	}
	/**
	* 0-未锁定,1-锁定
	*@param  locked
	*/
	public void setLocked(Integer locked ){
		this.locked = locked;
	}
	
	/**
	* 0-正常,1-暂停
	*@return 
	*/
	public Integer getStatus(){
		return  status;
	}
	/**
	* 0-正常,1-暂停
	*@param  status
	*/
	public void setStatus(Integer status ){
		this.status = status;
	}
	
	public String getPassword(){
		return  password;
	}
	public void setPassword(String password ){
		this.password = password;
	}
	
	public String getUsername(){
		return  username;
	}
	public void setUsername(String username ){
		this.username = username;
	}
	

}
