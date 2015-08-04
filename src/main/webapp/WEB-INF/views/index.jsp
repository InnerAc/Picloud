<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="jt" class="com.Picloud.utils.JspUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${TITLE}</title>
<link rel="stylesheet" href="${RESOURCES}/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="${RESOURCES}/css/main.css" />
</head>
<body>
		<jsp:include page="common/header.jsp" />
		 <div class="content">
            <div class="content-wrap">
            你好，${LoginUser.nickname}
            </div>
            </div>
                </section>
    <div class="clearfix"></div>
			<jsp:include page="common/footer.jsp" />
	<script type="text/javascript"
		src="${RESOURCES }/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCES }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${RESOURCES }/js/common.js"></script>
		<script type="text/javascript" src="${PLUGIN}/flot/jquery.flot.min.js"></script>
		<script type="text/javascript" src="${PLUGIN}/flot/jquery.flot.pie.min.js"></script>
		<script type="text/javascript" src="${PLUGIN}/flot/jquery.flot.tooltip.min.js"></script>
	<script type="text/javascript" src="${RESOURCES }/js/index.js"></script>
</body>
</html>