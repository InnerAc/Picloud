<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
<jsp:include page="../common/header.jsp" />

 <div class="content">
            <div class="content-wrap">
                <div class="content-toolbar clearfix">
                    <ul class="toolbar-action clearfix">
                        <li class="current"><a href="">最近修改</a></li>
                        <li><a href="">上传时间</a></li>
                    </ul>
                </div>
                <div class="space-list">
                    <ul class="spaces">
                       <c:forEach items="${spaces}" var="space">  
                       		<c:choose>
                       			<c:when test="${space.number  eq 0}">
			                        <li class="space-li">
			                            <div class="thumbnail-wrapper"><a href="${ROOT}/space/${space.sid}?page=0"><div class="thumbnail">
			                                <img src="${RESOURCES}/images/spacer-col.gif" class="sicn ">
			                                <div class="progress-wrapper ab-center js-empty-folder">Empty Space</div>
			                            </div></a></div>
			                            <div class="name-area"><a href="" class="thumb-title">${space.name}</a></div>
			                            <div class="actions clearfix">
			                                <div class="action-list l">
			                                    <span>裁剪</span> <span>缩放</span>
			                                </div>
			                            </div>
			                            <div class="space-footer">
			                                <span class="time">${jt.formatDate(space.time)} </span>
			                            </div>
			                        </li>                       			
                       			</c:when>
                       			
                       			<c:otherwise>
		                           <li class="space-li">
		                            <div class="thumbnail-wrapper"><a href="${space.sid}"><div class="thumbnail">
		                                <div class="thumb"><img src="1.png" alt=""></div>
		                                <div class="thumb"><img src="2.png" alt=""></div>
		                                <div class="thumb"><img src="3.png" alt=""></div>
		                                <div class="thumb"><img src="3.png" alt=""></div>
		
		                            </div></a></div>
		                            <div class="name-area"><a href="" class="thumb-title">${space.name}</a></div>
		                            <div class="actions clearfix">
		                                <div class="action-list l">
		                                    <span>裁剪</span> <span>缩放</span>
		                                </div>
		                            </div>
		                            <div class="space-footer">
		                                <span class="time">16 minutes ago</span>
		                            </div>
		                        </li>                                           			
                       			</c:otherwise>
                       		</c:choose>
                        </c:forEach>
                        <li class="space-li">
                           <sf:form method="post" modelAttribute="space" action="add" class="new-space">
                                <sf:input path="name" type="text"  placeholder="请输入空间名字"/>
                                <sf:textarea path="description" id="" placeholder="请输入空间描述"></sf:textarea>
                                <button class="btn btn-blue">添加</button>
                            </sf:form>
                            <div class="space-footer">
                                <span class="time">right now</span>
                            </div>
                        </li>                                             
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <div class="clearfix"></div>
			<jsp:include page="../common/footer.jsp" />
		</div>
	</div>
	<script type="text/javascript"
		src="${RESOURCES }/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCES }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${RESOURCES }/js/common.js"></script>
</body>
</html>