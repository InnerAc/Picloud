<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${TITLE}</title>
<link rel="stylesheet" href="${RESOURCES}/css/main.css" />
</head>
<body class="signup-page">
	<div class="main-wrapper">
		<div id="main">
			<div class="ab-center signup-wrapper " id="salogin">
				<sf:form method="post" modelAttribute="user" action="register"  class="form-wrapper" id="signup-form">
					<a href="" class="logo-home"><img src="${RESOURCES}/images/logo4.png" alt=""></a>
					<div class="clearfix"></div>
					<div class="form-errors" id="error-msg"><c:if test="${not empty LOGIN_MSG}"><span class="alert alert-error" ><p>${LOGIN_MSG }</p></span></c:if></div>
					<div class="form-group"><sf:input path="email" type="text"  datatype="e" nullmsg="请填写邮箱！"  class="form-control input-lg" placeholder="请输入用户名/邮箱" /></div>
					<div class="form-group"><sf:input path="nickname"   nullmsg="请填写昵称！"  type="text" class="form-control input-lg" placeholder="请输入昵称" /></div>
					<div class="form-group"><sf:input path="password"  nullmsg="请填写密码！"  datatype="s6-16"  errormsg="密码至少6个字符,最多16个字符！" type="password" class="form-control input-lg" placeholder="请输入密码" /></div>
					<div class="form-group"><sf:input path="password" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" type="password" class="form-control input-lg" placeholder="请再次输入密码" /></div>
					<input type="submit" name="submit" value="注册" id="submit" class="btn btn-blue btn-lg btn-block submit">		
					<p class="help-block">已经有账号? <a href="login">返回登录</a></p>		
				</sf:form>
			</div>
		</div>
	</div>
	<script src="${RESOURCES}/js/jquery-2.0.0.min.js"></script>
	<script src="${PLUGIN}/validform/validform-5.3.2.min.js"></script>
	<script>
		$('#signup-form').Validform({
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