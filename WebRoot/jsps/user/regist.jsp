<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>">
  </head>
  
  <body>
    <div id="divMain">
    	<div id="divTitle" >
    		<span id="spanTitle">新用户注册</span>
    	</div>
    	<div id="divBody">
    		<table id="tableForm">
    			<tr>
    				<td>用户名</td>
    				<td><input type="text" name="loginname" id="loginname"></td>
    				<td>用户名不能为空！</td>
    			</tr>
    			<tr>
    				<td>登录密码</td>
    				<td><input type="password" name="loginpass" id="loginpass"></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>确认密码</td>
    				<td><input type="password" name="reloginname" id="reloginname"></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>Email</td>
    				<td><input type="text" name="email" id="email"></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>图形验证码</td>
    				<td><input type="text" name="verifycode" id="verifycode"></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td><img src=""/></td>
    				<td><a href="">换一张</a></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td><input type="image" src="<c:url value='/images/regist1.jpg'/>" id="submitBtn">
    				</td>
    				<td></td>
    			</tr>
    		</table>
    	</div>
    </div>
  </body>
</html>
