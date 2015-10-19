package cn.itcast.goods.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.goods.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * 用户模块持久层
 * @author ZY
 *
 */
public class UserDao {
		private QueryRunner qr = new TxQueryRunner();
		
		/**
		 * 校验用户名是否注册
		 * @param loginname
		 * @return
		 * @throws SQLException
		 */
		public boolean ajaxValidateLoginname(String loginname) throws SQLException{
			String sql = "select count(1) from t_user where loginname=?";
			Number number = (Number)qr.query(sql, new ScalarHandler(),loginname);
			return number.intValue() == 0;
		}
		
		/**
		 * 校验Email是否注册
		 * @param email
		 * @return
		 * @throws SQLException
		 */
		public boolean ajaxValidateEmail(String email) throws SQLException{
			String sql = "select count(1) from t_user where email=?";
			Number number = (Number)qr.query(sql, new ScalarHandler(),email);
			return number.intValue() == 0;
		}
		
		/**
		 * 添加用户
		 * @param user
		 * @throws SQLException 
		 */
		public void add(User user) throws SQLException{
			/*
			 * 1.写Sql语句
			 */
			String sql = "insert into t_user value(?,?,?,?,?,?)";
			/*
			 * 2.准备好各种参数
			 */
			Object[] params = {user.getUid(),user.getLoginname(),user.getLoginpass(),
					user.getEmail(),user.isStatus(),user.getActivationCode()};
			/*
			 * 3.update语句，把sql语句和参数一起执行,抛异常，结束
			 * 执行sql语句一般都是update，查询的时候用query
			 */
			qr.update(sql, params);
		}
}
