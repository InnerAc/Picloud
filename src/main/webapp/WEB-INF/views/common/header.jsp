<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<header class="clearfix">
        <div class="brand l">
            <div class="logo"><img src="${RESOURCES}/images/logo4.png" alt="Picloud"  height="69"></div>
        </div>   
        <div class="login-area r">
            <div class="dropdown user-info pull-right logged-in">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" id="user-dropdown">
                    <img alt="" class="circle" src="${RESOURCES }/images/avatar.png" height="32" width="32">
                </a>
                <ul class="dropdown-menu top-right" role="menu" aria-labelledby="dropdownMenu2">
                    <span class="arrow icn-dd-top-arrow"></span>
                <li role="presentation" class="dropdown-header">
                    <div class="dropdown-title">Jeff Chen</div>
                    <div class="dropdown-detail">564936642@qq.com</div>
                </li>
                <li role="presentation" class="divider"></li>
                <li><a href="/settings">Settings</a></li>
                    <li><a href="/install" target="_blank">Install</a></li>
                  <li id="feedback" class="feedback_popup"><a>Feedback</a></li>
              <li><a href="${ROOT }/user/logout">Logout</a></li>
            </ul>
        </div>
        </div>
        <div class="global-actions r">
            <div id="upload"></div>
        </div>
        <div class="search-box r">
            <form action="">
                <span class="icn-search"></span>
                <input type="text" placeholder="Search...">
            </form>
        </div>                     
    </header>
     <section id="main">
        <div class="sidebar">
            <ul class="nav server-nav">
		<c:forEach items="${BASIC_MODULE}" var="module">
			<li><a href="${ROOT}/${ module.url}"><i class="fa fa-${module.icon }"></i>${module.title }</a></li>
		</c:forEach>                
            </ul>
            <ul class="nav user-nav">
                <div class="heading">Personal info</div>
		<c:forEach items="${PERSONAL_MODULE}" var="module">
			<li><a href="${ROOT}/${ module.url}"><i class="fa fa-${module.icon }"></i>${module.title }</a></li>
		</c:forEach>   
            </ul>
            <div class="storage-info clearfix">
                <div class="heading">Storage info</div>
                <div class="storage-used clearfix">
                    <span class="usage" data-storage-used="35101242" data-storage-left="2112382406">
                        1GB / 2 GB  </span>
                    <div class="js-offer pull-right clearfix">
                        <a href="/plus" class="icon icn-plus" data-tooltip="true" data-placement="top" data-animation="false" title="" data-original-title="Upgrade Now"></a>
                    </div>
                </div>
                <div class="progress clearfix">
                    <div class="progress-bar progress-bar-storage" style="width: 50%"></div>
                </div>
            </div>
        </div>
