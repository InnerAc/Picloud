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
<link rel="stylesheet" href="${PLUGIN}/chosen/chosen.css" />
<link rel="stylesheet" href="${RESOURCES}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${PLUGIN}/imageEditor/css/editor.css" />
<link rel="stylesheet" href="${PLUGIN}/imageEditor/css/spectrum.css" />
<link rel="stylesheet" href="${RESOURCES}/css/picserver.css" />
<link rel="stylesheet" href="${RESOURCES}/css/main.css" />
<style>
	.canvas-container{
		margin-left:auto;
		margin-right:auto;
	}
</style>
</head>
<body>
<div id="main">
        <div id="viewer_wrapper" class="viewer-wrapper white media-viewer image">
      <div class="viewer-container">
        <div class="viewer-header">
              <div class="brand l">
                  <div class="logo"><img src="${RESOURCES }/images/logo4.png" height="70" /></div>
              </div>
              <div class="viewer-action r">
                  <div class="button-group">
                      <button class="btn btn-default">Link</button>
                      <button class="btn btn-default" data-toggle="modal" data-target="#imageEditor" id="openEditor">Edit</button>
                      <button class="btn btn-blue">Download</button>
                    <a href="${ROOT}/space/${space.sid}" id="viewer_close" class="btn btn-link"><span class="icn-viewer-close"></span></a>
                  </div>
                  
              </div>
              <div class="viewer-title">
                 <span class="viewer-title-text">${image.name }</span>
              </div>          
        </div>
        <div class="viewer-content-wrapper">
          <div class="media-outer">
            <div class="media-inner">
              <div class="media-wrapper">
                <div class="image-gallery">
                  <img src="${ROOT}/server/${image.key}" alt="" class="media-item ab-center zoom-allowed">
                </div>
              </div>
            </div>
          </div>

        </div>
        <div class="viewer-footer clearfix">
          <a href="#" id="list_arrow_left" class="list-arrow-wrapper l"><span class="icn-list-back ab-center"></span></a>
          <a href="#" id="list_toggle" class="r list-toggle"><span class="icn-hide-list ab-center"></span></a>
          <a href="#" id="list_arrow_right" class="list-arrow-wrapper r"><span class="icn-list-next ab-center"></span></a>
          <div id="bucket_list_wrapper" class="bucket-list-wrapper">
            <ul id="viewer_items_list" class="bucket-file-list clearfix viewer-panel-items-list-animate">
              <li class="thumb-item active" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""  ><img src="test2.jpg" alt=""></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt="" ></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
              <li class="thumb-item" data-toggle="tooltip" data-placement="top" title="Tooltip on left"><a href=""><img src="test2.jpg" alt=""  data-title="123"></a></li>
            </ul>
          </div>
        </div>        
      </div>
    </div>
  </div>
  
         <div class="green-editor modal inmodal in " id="imageEditor" tabindex="-1" role="dialog"
            aria-hidden="false" data-image="${image.name}" data-key="${image.key}"data-imageUrl="${IP}${ROOT}/server/${image.key}" data-visit="${IP}${ROOT}/process/" data-update="${IP}${ROOT}/process/update/">
            <div class="modal-dialog animated bounceInRight">
                <div class='picloud-container' id='picloud-container' ></div>
            </div>
        </div>
<div id="url_base" style="display: none">${IP}${ROOT}</div>
    <!-- Modal -->
    <div class="modal fade" id="logoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog hd-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">取消</span></button>
            <h4 class="modal-title" id="logoModalLabel">选择Logo</h4>
          </div>
          <div class="modal-body">
              <div class="form-group">
                <label class="control-label">选择空间</label>
                <select class="form-control  jet-input" name="account" id='spaces_select'>
                  <c:forEach items="${spaces}" var="space">
                    <option value="${space.sid}">${space.name}</option>
                  </c:forEach>
               </select>
              </div>
                  <div class="form-group">
                <label class="control-label">选择Logo</label>
                <select data-placeholder="请选择图片" name='imageKey' class="chosen-select form-control jet-input"tabindex="-1" id='pictures_select'>
                 </select>
              </div>
          </div>
        </div>
      </div>
    </div>
        <div class="modal" id="confirmModal" tabindex="-1" role="dialog"
                    aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog hd-dialog  ">
                        <div class="modal-content bounceIn">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span><span class="sr-only">取消</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">保存图片</h4>
                            </div>
                            <div class="modal-body">
                                保存图片会覆盖原图片,确认保存吗?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                    data-dismiss="modal">取消</button>
                                <button type="submit" class="btn jet-button btn-primary" id="confirmSave">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
    <div class="clearfix"></div>
    <script type="text/javascript"
    src="${RESOURCES }/js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="${RESOURCES }/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${RESOURCES }/js/common.js"></script>
  <script type="text/javascript" src="${PLUGIN}/chosen/chosen.jquery.js"></script>
  <script type="text/javascript" src="${RESOURCES }/js/imageshow.js"></script>
  <script type="text/javascript" src="${PLUGIN}/imageEditor/js/spectrum.js"></script>
  <script type="text/javascript" src="${PLUGIN}/imageEditor/js/jquery.nstSlider.js"></script>
  <script type="text/javascript" src="${PLUGIN}/imageEditor/js/fabric.js"></script>
  <script type="text/javascript" src="${PLUGIN}/imageEditor/js/crop.js"></script>
  <script type="text/javascript" src="${PLUGIN}/imageEditor/js/editor.js"></script>
  <script type="text/javascript" src="${PLUGIN}/zclip/jquery.zclip.min.js"></script>
</body>
</html>