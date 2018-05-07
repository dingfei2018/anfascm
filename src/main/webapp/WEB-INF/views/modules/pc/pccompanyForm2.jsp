<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流公司管理</title>
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
		<li><a href="${ctx}/pc/pccompany/">物流公司列表</a></li>
		<li class="active"><a href="${ctx}/pc/pccompany/form?id=${pccompany.id}">物流公司<shiro:hasPermission name="pc:pccompany:edit">${not empty pccompany.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:pccompany:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pccompany" action="${ctx}/pc/pccompany/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公司名称：</label>
			<div class="controls">
				<form:input path="corpname" htmlEscape="false" maxlength="125" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业执照编码-15位：</label>
			<div class="controls">
				<form:input path="bussinessCode" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司负责人姓名：</label>
			<div class="controls">
				<form:input path="chargePerson" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司负责人联系电话：</label>
			<div class="controls">
				<form:input path="chargeMobile" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司联系电话：</label>
			<div class="controls">
				<form:input path="corpTelphone" htmlEscape="false" maxlength="12" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司详细地址：</label>
			<div class="controls">
				<form:input path="corpAddrUuid" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司资质是否已被认证，0 - 还没认证（默认），1 - 已经认证：</label>
			<div class="controls">
				<form:input path="isCertification" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资质认证图片组：</label>
			<div class="controls">
				<form:input path="certImgUuid" htmlEscape="false" maxlength="22" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一个用户只有一个公司，一个店铺：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${pccompany.user.id}" labelName="user.name" labelValue="${pccompany.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pc:pccompany:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>