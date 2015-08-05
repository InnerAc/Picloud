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
<link rel="stylesheet" href="${RESOURCES}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${RESOURCES}/css/main.css" />
</head>
<body>
			<jsp:include page="../common/header.jsp" />
		 <div class="content">
            <div class="content-wrap">
                <div class="content-toolbar clearfix">
                    <ul class="toolbar-action clearfix">
                        <li><a href=""><i class="fa fa-copy"></i>Move</a></li>
                        <li><a href=""><i class="fa fa-trash"></i>Delete</a></li>
                    </ul>
                    <ul class="list-style r">
                        <li class="active"><a href="" id="list-square"><span  class="icn-square"></span></a></li>
                        <li><a href="" id="list-table"><span class="icn-table"></span></a></li>
                    </ul>
                </div>
                <div class="photo-list">
                <c:forEach items="${images}" var="image">
                      <div class="photo">
                        <div class="photo-wrap">
                            <div class="photo-img">
                            <a href="${ROOT }/server/${image.key}/view"><img src="${ROOT}/process/${image.key}/scale[198,-]" alt=""></a></div>
                            <div class="photo-info">
                                <div class="photo-name">
                                    <a href="${ROOT }/server/${image.key}/view">${image.name}</a>
                                </div>
                                <div class="photo-meta">
                                    <span class="icn-timer"></span>${jt.format(image.createTime) }<span class="icn-storage"></span>${jt.cutLength4(image.size) }KB
                                </div>
                            </div>
                        </div>
                    </div>              
                </c:forEach>
                </div>
            </div>
            </div>
            <div id="space" data-space="${space.sid}"></div>
                </section>
    <div class="clearfix"></div>
			<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript"
		src="${RESOURCES }/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCES }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${PLUGIN}/uploadify/jquery.Huploadify.js"></script>
	<script>
    $(function() {
		var sid = $('#space').attr('data-space');
        $(".photo").click(function() {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $(this).children('.clicked').remove();
            } else {
                $(this).addClass('active').append('<div class="clicked"><span class="icn-tick"></span></div>');
            }
        });
		
        var up = $('#upload').Huploadify({
        auto:true,
        fileTypeExts:'*.jpg;*.png;*.gif;*.bmp',
        multi:true,
        fileSizeLimit:99999999,
        breakPoints:true,
        saveInfoLocal:true,
        showUploadedPercent:true,//是否实时显示上传的百分比，如20%
        showUploadedSize:true,
        removeTimeout:9999999,
        uploader:'' + sid + '/upload',
        onUploadStart:function(){
        },
        onUploadSuccess:function(file){
            //alert('上传成功');
        },
        onUploadComplete:function(){
            console.log('上传完成')
        },
    });
});

</script>
</body>
</html>