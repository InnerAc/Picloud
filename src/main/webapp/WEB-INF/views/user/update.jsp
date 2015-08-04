<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
                <div class="settings-wrapper">
                <div class="setting-item">
                    <h3 data-this="account">账户设置</h3>
                    <div class="setting-profile clearfix">

                        <form action="" method="post" accept-charset="utf-8" id="choose_avatar_form" class="hide"><div style="display:none">
                        </div>                                  
                            <input type="file" id="choose_avatar" name="file" accept="image/*">
                            <input type="hidden" name="MAX_FILE_SIZE" value="500">
                        </form>
                        <div class="avatar-wrapper l" id="avatar"><img src="${RESOURCES}/images/avatar.png" alt="" class="user-avatar img-responsive"></div>
                        <div class="clear-avatar r">
                            <span class="btn btn-default" id="change_avatar">Change Photo</span>
                            <span>或 <a href="javascript:void();" id="clear_avatar">使用默认头像</a></span>
                        </div>                       
                    </div>
                      <div class="form-group clearfix">
                        <label for="user-nickname"><strong>昵称</strong></label>
                          <div class="show-always">${LoginUser.nickname } <a href="#change"> 修改</a></div> 
                            <div class="show-on-demand hide">
                            <form action="" id="form-nickname">
                                <label for="firstName" class="label-split"><strong>昵称</strong></label>
                                <input type="text" class="form-control input-lg form-control-split" id="nickname" name="nickname" >
                                <button class="btn btn-blue btn-lg pull-left" type="button" id="update_username">更新</button>
                                <a id="hide-username-form" class="change-pass-link pull-left" href="#">取消</a>
                            </form>
                            </div>
                        </div> 
                        <div class="form-group clearfix">
                            <form id="form_password" action="" method="post" class="group">
                            <label for="user-password"><strong>密码</strong></label>
                          <div class="show-always">******** <a href="#change"> 修改</a></div> 
                            <div class="show-on-demand hide">
                            <div id="password-form" class="password-form">
                                <div class="form-group clearfix">
                            <label for="current-password">当前密码</label>
                            <input type="password" class="form-control input-lg" id="old_password" name="old_password" autocomplete="off">
                            <span id="old_password_error" class="msg error"></span>
                            </div>
                            <div class="form-group clearfix">
                            <label for="new-password">新密码</label>
                            <input type="password" class="form-control input-lg" id="new_password" name="new_password" autocomplete="off">
                            <span id="new_password_error" class="msg error"></span>
                            </div>

                            <div class="form-group clearfix">
                            <label for="new-password-again">确认密码</label>
                            <div class="clearfix">
                                <input type="password" class="form-control input-lg" id="confirm_new_password" name="confirm_new_password">
                                <span id="confirm_new_password_error" class="msg error"></span>
                            </div>
                            <button class="btn btn-blue btn-lg btn-save-password pull-left" id="update_password" type="button">更新</button>
                            <a id="hide-password-form" class="change-pass-link pull-left" href="#">取消</a>
                            </div>
                                </div>
                            </div>
                            </form>
                          </div>                        
                </div>
                <div class="setting-item custom-logo">
                    <form action="" class="group">
                        <h3>个人Logo</h3>
                        <h5>上传你个人的Logo,  这样你可以快捷地使用添加水印的功能</h5>
                        <h5 class="notes" style="width: auto; margin-left: 0;">为了更好的显示效果，您可以使用透明的PNG格式图片。</h5>
                        <div class="custom-logo clearfix">
                        <label for="user-email">图片Logo </label>
                        <div id="avatar" class="custom-logo-wrapper folder-page pull-left"><img src="${RESOURCES }/images/logo4.png" style=" " class="my_uploads_ custom_brand_image ab-center folder default-logo-dimensions logo folder-page img-responsive"></div>
                        <div class="pull-right clear_avatar">
                        <span class="btn btn-default change_custom_brand_image" data-type="folder">Upload Logo</span>
                        <span class="hide">or <a href="javascript:void();" class="clear_custom_brand_image" data-type="folder">remove logo</a></span>
                    </div>
                    </div>
                    
                <div class="form-group clearfix">
                    <label for="cdhp">文字Logo</label>
                    <input type="text" class="form-control input-lg l" id="cdhp" name="cdhp" value="" placeholder="您需要加在图片上的文字">
                    <span class="notes r">您可以使用您的域名或您的网名作为Logo.</span>
                  </div>
                  <button class="btn btn-white blockUI distxts" type="button">Update</button>
                </form>
                </div>

                </div>
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