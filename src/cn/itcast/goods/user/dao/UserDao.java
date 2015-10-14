package cn.itcast.goods.user.dao;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * 用户模块持久层
 * @author ZY
 *
 */
public class UserDao {
		private QueryRunner qr = new TxQueryRunner();
}
