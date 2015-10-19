package cn.itcast.goods.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.commons.CommonUtils;
import cn.itcast.goods.user.dao.UserDao;
import cn.itcast.goods.user.domain.User;
import cn.itcast.goods.user.service.UserService;
import cn.itcast.servlet.BaseServlet;

/**
 * 用户模块控制层（web层 或 表述层  都可以）
 * @author ZY
 *
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	
	/**
	 * ajax用户名是否校验
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateLoginname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取用户名
		 */
		String loginname = req.getParameter("loginname");
		/*
		 * 2.通过Service得到校验结果
		 */
		boolean b = userService.ajaxValidateLoginname(loginname);
		/*
		 * 3.发给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * ajax Email是否校验
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取Email
		 */
		String email = req.getParameter("email");
		/*
		 * 2.通过Service得到校验结果
		 */
		boolean b = userService.ajaxValidateEmail(email);
		/*
		 * 3.发给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * ajax 验证码是否正确校验
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取输入框中的验证码
		 */
		String verifyCode = req.getParameter("verifyCode");
		/*
		 * 2.获取图片上的真实的验证码
		 */
		String vcode = (String) req.getSession().getAttribute("vCode");
		/*
		 * 3.进行忽略大小写比较，得出结果
		 */
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		/*
		 * 4.发送给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}
	/**
	 * 注册功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {			
			
		/*
		 * 1.封装表单数据到User对象中
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2.校验之，如果校验失败，保存错误信息，返回regist.jsp显示
		 * 校验是重点
		 */
		Map<String,String> errors = validateRegist(formUser, req.getSession());
		if(errors.size() > 0){
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/regist.jsp";
		}
		/*
		 * 3.使用Service完成业务
		 */
		userService.regist(formUser);
		/*
		 * 4.保存成功信息，转发到msg.html显示
		 */
		req.setAttribute("code","success");
		req.setAttribute("msg", "注册成功，请马上到邮箱激活！");
		return "e:/jsps/msg.jsp";
	}
	/*
	 * 注册校验
	 * 对表单的字段进行捉个校验，如果有错，使用当前字段名称为key，错误信息为value，保存到map中
	 * 返回map
	 */
	 private Map<String,String> validateRegist(User formUser,HttpSession session){
		 Map<String,String> errors = new HashMap<String,String>();
		 /*
		  * 1.校验登录名
		  */
		 String loginname = formUser.getLoginname();
		 if(loginname==null || loginname.trim().isEmpty()){
			 errors.put("loginname", "用户名不能为空！");
		 }else if(loginname.length()<3||loginname.length()>20){
			 errors.put("loginname", "用户名长度必须在3~20之间！");
		 }else if(!userService.ajaxValidateLoginname(loginname)){
			 errors.put("loginname", "用户名已被注册！");
		 }
		 
		 /*
		  * 2.校验登录密码
		  */
		 String loginpass = formUser.getLoginpass();
		 if(loginpass==null || loginpass.trim().isEmpty()){
			 errors.put("loginname", "密码不能为空！");
		 }else if(loginpass.length()<3||loginpass.length()>20){
			 errors.put("loginname", "密码长度必须在3~20之间！");
		 }
		 
		 /*
		  * 3.确认 密码 校验
		  */
		 String reloginpass = formUser.getLoginpass();
		 if(reloginpass==null || reloginpass.trim().isEmpty()){
			 errors.put("reloginpass", "确认密码不能为空！");
		 }else if(!reloginpass.equals(loginpass)){
			 errors.put("reloginpass", "两次输入不一致！");
		 }
		 
		 /*
		  * 4.Email校验
		  */
		 String email = formUser.getEmail();
		 if(email==null || email.trim().isEmpty()){
			 errors.put("email", "Emial不能为空！");
		 }else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			 errors.put("email", "Email格式错误！");
		 }else if(!userService.ajaxValidateEmail(email)){
			 errors.put("email", "Email已被注册！");
		 }
		 
		 /*
		  * 5.验证码校验
		  */
		 String verifyCode = formUser.getVerifyCode();
		 String vcode = (String) session.getAttribute("vCode");
		 if(verifyCode==null || verifyCode.trim().isEmpty()){
			 errors.put("verifyCode", "验证码不能为空！");
		 }else if(!verifyCode.equalsIgnoreCase(vcode)){
			 errors.put("verifyCode", "验证码错误！");
		 }
		return errors;
	 }
	
	/**
	 * 激活功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String activation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("activation()...");
		return null;
	}
}
