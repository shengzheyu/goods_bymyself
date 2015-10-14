package cn.itcast.goods.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.goods.user.service.UserService;
import cn.itcast.servlet.BaseServlet;

/**
 * 用户模块控制层（web层 或 表述层  都可以）
 * @author ZY
 *
 */
public class UserServlet extends BaseServlet {
	private UserService userservice = new UserService();
}
