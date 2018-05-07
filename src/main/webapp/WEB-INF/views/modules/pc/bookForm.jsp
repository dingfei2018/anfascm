<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	   $(document).ready(function() {
		//$("#name").focus();
		   $("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
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
		
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pc/addressbook/">平台地址列表</a></li>
		<li class="active"><a href="${ctx}/pc/addressbook/form?id=${pcbookk.book.id}">地址<shiro:hasPermission name="pc:addressbook:edit">${not empty pcbookk.book.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:addressbook:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pcbook" action="${ctx}/pc/addressbook/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>		
		<c:set var="bookName" value="" />
	  	<c:set var="regionCode" value="${pcbook.book.regionCode}" />
		<%@include file="/WEB-INF/views/include/address.jsp" %>
		<div class="form-actions">
			<shiro:hasPermission name="pc:addressbook:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>