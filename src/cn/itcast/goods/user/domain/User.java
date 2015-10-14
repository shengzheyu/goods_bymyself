package cn.itcast.goods.user.domain;
/**
 * 用户模块实体类
 * @author ZY
 *
 */

/*
 * 属性哪里来
 * 	1.t_user表：因为我们要把t_USER表中查询出的数据封装到User对象中
 * 	2.该模块所有表单：因为我们需要把表单数据封装到User对象中
 */
public class User {
	private String uid;//主键
	private String loginname;//登录名
	private	String loginpass;//登录密码
	private String email;//邮箱
	private int status;//状态，true表示已激活，否则未激活
	private String activationCode;//激活码，是唯一值！即每个用户的激活码是不同的
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass="
				+ loginpass + ", email=" + email + ", status=" + status
				+ ", activationCode=" + activationCode + "]";
	}
	
	
}
