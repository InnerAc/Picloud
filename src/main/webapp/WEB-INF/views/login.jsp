<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${TITLE}</title>
<link rel="stylesheet" href="${RESOURCES}/css/main.css" />
</head>
<body class="login-page">
	<div class="main-wrapper">
		<div id="main">
			<div class="ab-center login-wrapper " id="salogin">
				<form action="" method="post" class="form-wrapper" id="login-form">
					<a href="" class="logo-home"><img src="${RESOURCES}/images/logo4.png" alt=""></a>
					<div class="clearfix"></div>
					<div class="form-errors" id="error-msg"><c:if test="${not empty LOGIN_MSG}"><span class="alert alert-error" ><p>${LOGIN_MSG }</p></span></c:if></div>
					<div class="form-group"><input type="text"  name="email" datatype="e"  nullmsg="请填写邮箱！"  title="email" id="email"   class="form-control input-lg" placeholder="请输入用户名/邮箱"></div>
					<div class="form-group"><input type="password"   datatype="*" nullmsg="请填写密码！"  name="password" title="pwd" id="pwd"   class="form-control input-lg" placeholder="请输入密码"></div>
					<div class="clearfix">
						<label for="remember" class="custom-controls checkbox l"><input type="checkbox" name="remember" value="1" checked="checked" id="remember"><span class="custom-control-indicator"></span>记住我</label>						<a href="" class="btn-link r">忘记密码?</a>
					</div>	
					<input type="submit" name="submit" value="登录" id="submit" class="btn btn-blue btn-lg btn-block submit">		
					<p class="help-block">还没有账号? <a href="register">立即注册</a></p>		
				</form>
			</div>
		</div>
	</div>
		<script src="${RESOURCES}/js/jquery-2.0.0.min.js"></script>
	<script src="${PLUGIN}/validform/validform-5.3.2.min.js"></script>
	<script>
		$('#login-form').Validform({
			tiptype:function(msg,o,cssctl){
				if(o.type == 3){
					msg_dom = '<span class="alert alert-error" ><p>' + msg + '</p></span>';
					$("#error-msg").html(msg_dom);	
				}else if(o.type == 2){
					$("#error-msg").html(' ');					
				}
			}
		});
	</script>
</body>
</html>