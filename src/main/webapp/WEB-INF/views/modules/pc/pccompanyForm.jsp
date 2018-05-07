<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	   function showSelectedRegion(part){
		 //$("#s2id_loc_province a span.select2-chosen").html($("#loc_province").find("option:selected").text());		
		 // $("#s2id_loc_city a span.select2-chosen").html($("#loc_city").find("option:selected").text());
		 // $("#s2id_loc_area a span.select2-chosen").html($("#loc_area").find("option:selected").text());
		   $('#loc_province').trigger('change.select2');
			 $('#loc_city').trigger('change.select2');
			 $('#loc_area').trigger('change.select2');
			 if($('#loc_city').html()==""){
				 $("#s2id_loc_city a span.select2-chosen").html(" - 请选择 - ");
			 }	
			 if($('#loc_area').html()==""){
				 $("#s2id_loc_area a span.select2-chosen").html(" - 请选择 - ");
			 }			 
	   }
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					 if ($("#loc_area").val()==""){
	                        top.$.jBox.tip('省市区没有选择','warning');
	                 }else{
	                	 loading('正在提交，请稍等...');
	 					form.submit();
	                 }					
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

			initPhotoSwipeFromDOM('.my-gallery');
		});
	</script>
	<script src="${ctxStatic}/anfa/data-city.js?13" type="text/javascript"></script>
	<style type="text/css">
     ul.pcuser-right-top { width: 100%; min-height: 100px; display: block; padding: 0px;margin: 0px;}
     .pcuser-right-top li { list-style: none; display: block; float: left; margin: 8px 10px 8px 0px;}
     .pcuse-right-img{ width: 267px; height: 240px; border: 1px solid blue; float: left; margin: 0px 30px 0px 0px;  text-align: center;}
     .pcuse-right-img img {display:block; float:left;}
     .pcuse-right-img p { display:block;  float:left;}	
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pc/pcuser/">平台用户列表</a></li>
		<li class="active"><a href="${ctx}/pc/pccompany/form?id=${pccompany.pcuser.id}">平台用户详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pccompany" action="${ctx}/pc/pccompany/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="pcuser.id"/>
		<sys:message content="${message}"/>		
		
		<ul class="pcuser-right-top" >	  				
	  				<li  >手机号: <form:input path="pcuser.mobile" htmlEscape="false" maxlength="30" class="input-xlarge "/></li>
	  				<li  >登录密码：<input id="password" name="password" class="input-xlarge valid" type="text" value="" maxlength="32" /></li>
	  				<li  >公司名：<form:input path="corpname" htmlEscape="false" maxlength="30" class="input-xlarge "/></li>	  				
	  				<li  >营业执照：<form:input path="bussinessCode" htmlEscape="false" maxlength="30" class="input-xlarge "/></li>
	  				<li  >联系人：<form:input path="chargePerson" htmlEscape="false" maxlength="30" class="input-xlarge "/></li>
	  				<li  >公司电话：<form:input path="corpTelphone" htmlEscape="false" maxlength="30" class="input-xlarge "/></li>
	  	</ul>
	  	<div id="startId" class="label2" >公司地址：
            		            <select id="loc_province" name="province" style="width:105px"></select> 
								<select id="loc_city" name="city" style="width:105px"></select>
								<select id="loc_area" name="area" style="width:105px"></select>
								<input id="tailAddress" name="tailAddress" class="input-xlarge valid" type="text" value="${book.tailAddress}" maxlength="32" /> 
								<script type="text/javascript">
								$("#startId").region({areaField:"area",cityField:"city",domain:"http://dev.anfa.com",currAreaCode:"${book.regionCode}", crossDomain:true,callback:function(part){showSelectedRegion(part);}});
								</script>
		</div>
		<div class="my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
		  <c:if test="${yyzzimg!=null}">
          <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
             <a href="${yyzzimg}" itemprop="contentUrl" data-size="1024x1024">
               <img src="${yyzzimg}" itemprop="thumbnail" alt="Image description" style="height:267px;width:267px" /> </a>
               <p>营业执照照片</p>
               <figcaption itemprop="caption description">营业执照照片</figcaption>                                          
          </figure>
          </c:if>
           <c:if test="${sfzimg!=null}">
          <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
             <a href="${sfzimg}" itemprop="contentUrl" data-size="1024x1024">
               <img src="${sfzimg}" itemprop="thumbnail" alt="Image description" height="267" width="267" /> </a>
               <p>人与身份证合影照片</p>
               <figcaption itemprop="caption description">人与身份证合影照片</figcaption>                                          
          </figure>
          </c:if>
          <c:if test="${mtimg!=null}">
          <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
             <a href="${mtimg}" itemprop="contentUrl" data-size="1024x1024">
               <img src="${mtimg}" itemprop="thumbnail" alt="Image description" height="267" width="267" /> </a>
               <p>公司门头照片</p>
               <figcaption itemprop="caption description">公司门头照片</figcaption>                                          
          </figure>
          </c:if>
       </div>		
		
		<div class="form-actions">
			<shiro:hasPermission name="pc:pcuser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交" style="width:180px"/>&nbsp;</shiro:hasPermission>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<shiro:hasPermission name="pc:pcuser:delete"><a href="${ctx}/pc/pcuser/delete?id=${pccompany.pcuser.id}" onclick="return confirmx('确认要删除该平台用户吗？', this.href)">删除</a></shiro:hasPermission>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@include file="/WEB-INF/views/include/photoswipe.jsp" %>
</body>
</html>