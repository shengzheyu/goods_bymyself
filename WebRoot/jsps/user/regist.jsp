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
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></Script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
  </head>
  
  <body>
    <div id="divMain">
    	<div id="divTitle" >
    		<span id="spanTitle">新用户注册</span>
    	</div>
    	<div id="divBody">
    	<form action="<c:url value='/UserServlet'/>" method="post" id="registForm">
    		<input type="hidden" name="method" value="regist"/>
    		<table id="tableForm" >
    			<tr>
    				<td class="tdText">用户名</td>
    				<td class="tdInput">
    					<input class="inputClass" type="text" name="loginname" id="loginname" value="${form.loginname}">
    				</td>
    				<td  class="tdError">
    					<lable class="errorClass" id="loginnameError">${errors.loginname}</lable>
    				</td>
    			</tr>
    			<tr>
    				<td class="tdText">登录密码</td>
    				<td><input class="inputClass" type="password" name="loginpass" id="loginpass" value="${form.loginpass}"></td>
    				<td><lable class="errorClass" id="loginpassError">${errors.loginpass}</lable></td>
    			</tr>
    			<tr>
    				<td class="tdText">确认密码</td>
    				<td><input class="inputClass" type="password" name="reloginpass" id="reloginpass" value="${form.reloginpass}"></td>
    				<td><lable class="errorClass" id="reloginpassError">${errors.reloginpass}</lable></td>
    			</tr>
    			<tr>
    				<td class="tdText">Email</td>
    				<td><input class="inputClass" type="text" name="email" id="email" value="${form.email}"></td>
    				<td><lable class="errorClass" id="emailError">${errors.email}</lable></td>
    			</tr>
    			<tr>
    				<td class="tdText">验证码:</td>
    				<td><input class="inputClass" type="text" name="verifyCode" id="verifyCode" value="${form.verifyCode}"></td>
    				<td><lable class="errorClass" id="verifyCodeError">${errors.verifyCode}</lable></td>
    			</tr>
    			<tr>
    				<td class="tdText"></td>
    				<td><div id="divVerifyCode"><img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>"/></div></td>
    				<td><lable><a href="javascript:_hyz()">换一张</a></lable></td>
    			</tr>
    			<tr>
    				<td class="tdText"></td>
    				<td><input type="image" src="<c:url value='/images/regist1.jpg'/>" id="submitBtn">
    				</td>
    				<td></td>
    			</tr>
    		</table>
    		</form>
    	</div>
    </div>
  </body>
</html>
