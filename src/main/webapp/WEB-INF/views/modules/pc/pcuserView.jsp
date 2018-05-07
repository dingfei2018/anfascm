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
		<li><a href="${ctx}/pc/pcuser/">平台用户列表</a></li>
		<li class="active"><a href="${ctx}/pc/pcuser/form?id=${pcuser.id}">用户信息-${pcuser.username}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pcuser" action="${ctx}/pc/pcuser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">用户登陆用用户名：</label>
			<div class="controls">
				<c:out value="${pcuser.username}"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户显示用户名：</label>
			<div class="controls">
			    <c:out value="${pcuser.realname}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户手机号码：</label>
			<div class="controls">
			    <c:out value="${pcuser.mobile}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否开通在线店铺：</label>
			<div class="controls">
			   <form:checkboxes path="isOnshop" items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value"  htmlEscape="false" class="" disabled="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否专线用户：</label>
			<div class="controls">
			    <form:checkboxes path="isTransline" items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value" htmlEscape="false" class="" disabled="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型：</label>
			<div class="controls">
			    <form:checkboxes path="usertype" items="${fns:getPcuserTypeMap()}"  htmlEscape="false" class="" disabled="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">该用户添加时间：</label>
			<div class="controls">
				<fmt:formatDate value="${pcuser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">该用户信息最后更新时间：</label>
			<div class="controls">
				<fmt:formatDate value="${pcuser.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱地址：</label>
			<div class="controls">
			    <c:out value="${pcuser.email}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
			    <c:out value="${pcuser.remark}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建者：</label>
			<div class="controls">
			    <c:out value="${pcuser.createId}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色：</label>
			<div class="controls">
			    <c:out value="${pcuser.roleId}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls">
			    <c:out value="${pcuser.titleImgUuid}" default="-"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户账户当前状态：</label>
			<div class="controls">
			    <form:checkboxes path="state" items="${fns:getPcuserStatusMap()}" htmlEscape="false" class="" disabled="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在部门：</label>
			<div class="controls">
			    <c:out value="${pcuser.departId}" default="-"></c:out>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">账户是否被锁定：</label>
			<div class="controls">
			    <form:checkboxes path="isLocked" items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value"   htmlEscape="false" class="" disabled="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>