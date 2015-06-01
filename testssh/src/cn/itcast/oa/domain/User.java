package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 * @author tyg
 * 
 */
public class User {
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();

	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明
     private Set<Privilege> privileges=new HashSet<Privilege>();
 
     /**
      * 判断本用户是否有指定名称的权限
      * @return
      */
     public boolean hasPrivilegeByName(String privilege) throws Exception{
    	 if(isAdmin()){
    		 return true;
    	 }
    	 if(isMark()){
    		 return true;
    	 }
    	 for(Role role:roles){
    		
    		 for(Privilege p:role.getPrivileges()){
    			 if(p.getName().equals(privilege)){
    				
    				 return true;
    			 }
    		 }
    	 }
    	
    	 return false;
     }
     /*
      *使用url 判断是否有系统权限
      */
     public boolean hasPrivilegeByUrl(String privilegeUrl) {
    	 if(isAdmin()){
    		 return true;
    	 }
    	 if(isMark()){
    		 return true;
    	 }
    	 //add UI is Same with ADD 
    	 if(privilegeUrl.endsWith("UI")){
    		 privilegeUrl=privilegeUrl.substring(0,privilegeUrl.length()-2);
    	 }
    	 for(Role role:roles){
    		
    		 for(Privilege p:role.getPrivileges()){
    			 if(privilegeUrl.equals(p.getUrl())){
    				
    				 return true;
    			 }
    		 }
    	 }
    	
    	 return false;
     }
private boolean isMark() {
	return "mark".equals(loginName);
	}
/**
 *  是否是超级管理员
 * @return
 */
	private boolean isAdmin() {
		/**
		 * 避免空指针异常
		 */
		return "admin".equals(loginName);
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
