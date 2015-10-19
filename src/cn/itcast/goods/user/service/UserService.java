package cn.itcast.goods.user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.faces.util.MessageFactory;



import cn.itcast.commons.CommonUtils;
import cn.itcast.goods.user.dao.UserDao;
import cn.itcast.goods.user.domain.User;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

/**
 * 用户模块业务层
 * @author ZY
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * 用户名注册校验
	 * @param loginname
	 * @return
	 */
	public boolean ajaxValidateLoginname(String loginname) {		
		try {
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Email校验
	 * @param loginname
	 * @return
	 */
	public boolean ajaxValidateEmail(String email) {		
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void regist(User user){
		/*
		 * 1.数据的补齐
		 */
		user.setUid(CommonUtils.uuid());
		user.setStatus(false);
		user.setActivationCode(CommonUtils.uuid()+CommonUtils.uuid());
		/*
		 * 2.向数据库插入
		 */
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/*
		 * 3.发邮件
		 */
		/*
		 * 1.加载配置文件
		 * 		把配置文件的内容加载到配置文件中
		 */
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		/*
		 * 2.登录邮件服务器，得到session
		 */
		String host = "host";//服务器主机名
		String name = "username";//登录名
		String pass = "password";//登录密码
		Session session = MailUtils.createSession(host, name, pass);
		
		/*
		 * 3.创建Mail对象
		 */
		String from = "from";//发件人
		String to = user.getEmail();//收件人
		String subject = "subject";//主题
		//MessageFormat.format 方法会把第一个参数中的{0}，使用第二个参数来替换。
		//例如MessageFormat.format("你好{0},你{1}","张三"，"去死吧");返回值就是"你好张三,你去死吧"
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationCode());//内容
		Mail mail = new Mail(from,to,subject,content);
		
		/*
		 * 4.发送邮件
		 * 发送文件的7个String对象（上面的7个），均在配置文件(email_temolate.properties)中有赋值
		 */
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
