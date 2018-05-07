<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>	
	<script type="text/javascript">	
		$(document).ready(function() {			
			$("#inputForm").validate({				
				submitHandler: function(form){
					console.log($('#status').val());
                    if ($("#status").val()==""){
                       // $("#categoryName").focus();
                       top.$.jBox.tip('页面错误，请重新刷新页面','warning');
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
	<style type="text/css">
	  .banner-info li { margin-top: 5px; color: red;}
	  .comany-info {width: 100%; float: left;margin: 10px auto;}
	  .comany-info span, .comany-info span{margin:0px 10px;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pc/verify">认证列表</a></li>
		<li class="active">
		  <a href="<c:url value='${fns:getAdminPath()}/pc/verify/form?id=${verifyCompany.verify.id}'> </c:url>">
		  认证<shiro:hasPermission name="pc:verify:audit">审核</shiro:hasPermission><shiro:lacksPermission name="pc:verify:audit">查看</shiro:lacksPermission></a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="verifyCompany" action="${ctx}/pc/verify/save" method="post" class="form-horizontal">
		<form:hidden path="verify.id"/>
		<form:hidden path="company.id"/>
		<form:hidden path="verify.pcuser.id"/>
		<sys:message content="${message}"/>		
		<div class="banner-info" style="float: left;padding: 0px 50px;height:130px">
	  			<ul>
	  				<li>审核要点：</li>
	  				<li>1. 身份证姓名是否与营业执照的姓名一致</li>
	  				<li>2. 人与身份证合影照片上看看是否长得像</li>
	  			</ul>
	   	</div>
	  		
	  	<div class="comany-info">	  	
	  	    <c:if test="${verifyCompany.verify.status == 1}" var="in_verify" />
	  		<span>公司名称: </span> <input  class="input-xlarge valid" readonly="readonly" type="text" value="${verifyCompany.company.corpname}" maxlength="45">	
	  		<span>营业执照 :</span> <input  class="input-xlarge valid" readonly="readonly" type="text" value="${verifyCompany.company.bussinessCode}" maxlength="45" style="width:155px">	       	
	  	    <span>公司注册年限：</span><form:input path="company.regToYear" htmlEscape="false" style="width:85px" readonly="${!in_verify}"  />	  	
			<span>是否有工商行政处罚：</span> <form:checkbox htmlEscape="false" rows="4" maxlength="45" class="input-xxlarge" path="company.isIcap" value="1" disabled="${!in_verify}" />
			<span>是否存在工商异常经营记录：</span><form:checkbox htmlEscape="false" rows="4" maxlength="45" class="input-xxlarge" path="company.isIcBlack" value="1" disabled="${!in_verify}" />
	     </div>
	  	<div class="my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
	  	 <c:if test="${yyzzimg!=''}" >
          <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
             <a href="${yyzzimg}" itemprop="contentUrl" data-size="1024x1024">
               <img src="${yyzzimg}" itemprop="thumbnail" alt="Image description" /> </a>
               <p>营业执照照片</p>
               <figcaption itemprop="caption description">营业执照照片</figcaption>                                          
          </figure>
          </c:if>
          <c:if test="${yyzzimg!=''}" >
          <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
             <a href="${sfzimg}" itemprop="contentUrl" data-size="1024x1024">
               <img src="${sfzimg}" itemprop="thumbnail" alt="Image description" /> </a>
               <p>人与身份证合影照片</p>
               <figcaption itemprop="caption description">人与身份证合影照片</figcaption>                                          
          </figure>
          </c:if>
          <c:if test="${yyzzimg!=''}" >
          <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
             <a href="${mtimg}" itemprop="contentUrl" data-size="1024x1024">
               <img src="${mtimg}" itemprop="thumbnail" alt="Image description" /> </a>
               <p>公司门头照片</p>
               <figcaption itemprop="caption description">公司门头照片</figcaption>                                          
          </figure>
          </c:if>
       </div>		   
		
	
	  <div style="float: left;margin: 20px auto;">
	  <c:if test="${verifyCompany.verify.status == 1}">	  
	      <shiro:hasPermission name="pc:verify:audit"> 未通过原因: <form:textarea htmlEscape="false" rows="4" maxlength="45" class="input-xxlarge" path="verify.reason"></form:textarea></shiro:hasPermission>
	  </c:if>
	  <c:if test="${verifyCompany.verify.status == 2}">	
	  	   已通过审核
	  </c:if>
	  <c:if test="${verifyCompany.verify.status == 3}">	
	  	   未通过原因: ${verifyCompany.verify.reason}
	  </c:if>
	 </div>	
	 <div style="width: 85%; float: left;" class="form-actions">		    	 
		<shiro:hasPermission name="cms:article:edit">
		   <c:if test="${verifyCompany.verify.status == 1}">	
			   <input id="btnSubmit" class="btn btn-primary" type="submit" name="verify_yes" value="审核通过" />&nbsp;
			   <input id="btnSubmit2" class="btn btn-primary" type="submit" name="verify_no" value="审核不通过" />&nbsp;
		   </c:if>
		</shiro:hasPermission>		   
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	 </div>
	</form:form>
	<%@include file="/WEB-INF/views/include/photoswipe.jsp" %>
</body>
</html>