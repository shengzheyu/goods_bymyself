//写jquery的代码
$(function(){
	/**
	 * 1. 得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息！
	 */
	$(".errorClass").each(function(){
		showError($(this));//遍历每个元素，使用每个元素来调用showError方法
	});
	
	/**
	 * 2.切换注册按钮的图片
	 */
	$("#submitBtn").hover(
			function(){
				$("#submitBtn").attr("src","/goods/images/regist2.jpg");
			},
			function(){
				$("#submitBtn").attr("src","/goods/images/regist1.jpg");
			}
	);
	
	/**
	 * 3.输入框得到焦点隐藏错误信息
	 */
	$(".inputClass").focus(function(){
//		alert($(this).attr("id"));//打印当前元素id
		var lableId = $(this).attr("id") + "Error";//通过输入框找到对应的lable的id
		$("#"+lableId).text("");//把lable的内容清空！
		showError($("#" + lableId));//隐藏没有信息的lable
	});
	
	/**
	 * 4.输入框失去焦点进行校验
	 */
	$(".inputClass").blur(function(){
//		alert($(this).attr("id"));//打印当前元素id
		var id = $(this).attr("id");
		//id.substring(0,1).toUpperCase()表示取id得第一个字母，并改为大写
		//id.substring(1)表示从id的第二个字母开始取到最后一个字母
		var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";//得到对应的校验函数名
		//eval（）把字符串当成javascript的代码来执行
		//alert(funName);
		eval(funName);//执行函数调用
	});
	
	/**
	 * 5.表单提交时进行校验
	 */
	$("#registForm").submit(function(){
		var bool=true;//设置返回值为true,当进行每个功能的检验时，只要有一个校验是错误，就就将bool赋值为false
		if(!validateLoginname()){
			bool=false;
		}
		if(!validateLoginpass()){
			bool=false;
		}
		if(!validateReloginpass()){
			bool=false;
		}
		if(!validateEmail()){
			bool=false;
		}
		if(!validateVerifyCode()){
			bool=false;
		}
//		validateLoginname();
//		validateLoginpass();
//		validateReloginpass();
//		validateEmail();
//		validateVerifycode();
		return bool;
	});
});

/*
 * 登录名校验方法
 */
function validateLoginname(){
	var id = "loginname";
	var value = $("#" + id).val();//获取输入框内容
	/*
	 * 1.非空校验
	 */
	if(!value){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("用户名不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 2.长度校验
	 */
	if(value.length<3||value.length>20){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("用户名长度必须在3~20之间");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 3.是否注册
	 * 步骤：
	 *  1.先发异步请求
	 *  2.得到服务器结果
	 *  3.考虑是否添加错误信息
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的Servlet
		data:{method:"ajaxValidateLoginname",loginname:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就想下运行了。
		cache:false,
		success:function(result){
			if(!result){//如果校验失败
				$("#"+id+"Error").text("用户名已被注册！");
				showError($("#"+id+"Error"));
				return false;
			}
		}
	});
	
	return true;
}

/*
 * 登录密码校验方法
 */
function validateLoginpass(){
	var id = "loginpass";
	var value = $("#" + id).val();//获取输入框内容
	/*
	 * 1.非空校验
	 */
	if(!value){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("密码不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 2.长度校验
	 */
	if(value.length<3||value.length>20){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("密码长度必须在3~20之间");
		showError($("#"+id+"Error"));
		return false;
	}
	
	return true;
}

/*
 * 确认密码校验方法
 */
function validateReloginpass(){
	var id = "reloginpass";
	var value = $("#" + id).val();//获取输入框内容
	/*
	 * 1.非空校验
	 */
	if(!value){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("确认密码不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 2.两次输入是否一样校验
	 */
	if(value!=$("#loginpass").val()){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("两次输入不一致");
		showError($("#"+id+"Error"));
		return false;
	}
	 
	return true;
}

/*
 * Email校验方法
 */
function validateEmail(){
	var id = "email";
	var value = $("#" + id).val();//获取输入框内容
	/*
	 * 1.非空校验
	 */
	if(!value){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("邮箱地址不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 2.Email格式验证
	 */
	if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("错误的Eamil格式！");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 3.是否注册
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的Servlet
		data:{method:"ajaxValidateEmail",email:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就想下运行了。
		cache:false,
		success:function(result){
			if(!result){//如果校验失败
				$("#"+id+"Error").text("该邮箱已被注册！");
				showError($("#"+id+"Error"));
				return false;
			}
		}
	});
	return true;
}

/*
 * 验证码校验方法
 */
function validateVerifyCode(){
	var id = "verifyCode";
	var value = $("#" + id).val();//获取输入框内容
	/*
	 * 1.非空校验
	 */
	if(!value){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("验证码不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 2.长度校验
	 */
	if(value.length!=4){
		/*
		 * 获取对应的lable
		 * 添加错误信息
		 * 显示lable
		 */
		$("#"+id+"Error").text("错误的验证码格式！");
		showError($("#"+id+"Error"));
		return false;
	}
	/*
	 * 3.是否正确校验
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的Servlet
		data:{method:"ajaxValidateVerifyCode",verifyCode:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就想下运行了。
		cache:false,
		success:function(result){
			if(!result){//如果校验失败
				$("#"+id+"Error").text("Sorry！验证码输入错误");
				showError($("#"+id+"Error"));
				return false;
			}
		}
	});
	return true;
}

/**
 * 判断当前的元素是否存在内容，如果存在则显示，否则不显示
 */
function showError(ele){
	var text = ele.text();//获取元素内容
	if(!text){//如果没有内容
		ele.css("display","none");//隐藏元素
	}else{//如果有内容
		ele.css("display","");//显示元素
	}
}

/*
 *  换一张验证码
 */
function _hyz(){
	/*
	 * 1.获取<img>元素
	 * 2.重新设置它的src
	 * 3.使用毫秒来添加参数
	 */
	$("#imgVerifyCode").attr("src","/goods/VerifyCodeServlet?a="+new Date().getTime());
}
