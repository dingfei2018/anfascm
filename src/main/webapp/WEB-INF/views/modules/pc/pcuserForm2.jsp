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
		<li class="active"><a href="${ctx}/pc/pcuser/form?id=${pcuser.id}">平台用户<shiro:hasPermission name="pc:pcuser:edit">${not empty pcuser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:pcuser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pcuser" action="${ctx}/pc/pcuser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户登陆用用户名：</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户显示用户名：</label>
			<div class="controls">
				<form:input path="realname" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登陆密码：</label>
			<div class="controls">
				<input id="password" name="password" class="input-xlarge valid" type="text" value="" maxlength="32" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重复登陆密码：</label>
			<div class="controls">
				<input id="passwordAgain" name="passwordAgain" class="input-xlarge valid" type="text" value="" maxlength="32" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户手机号码：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否开通在线店铺：</label>
			<div class="controls">
				<form:radiobuttons path="isOnshop" items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value"   htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否专线用户：</label>
			<div class="controls">
				<form:radiobuttons path="isTransline" items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value"   htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型：</label>
			<div class="controls">
				<form:select path="usertype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getPcuserTypeMap()}"  htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<c:if test="${not empty pcuser.id}">
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
		</c:if>
		<div class="control-group">
			<label class="control-label">邮箱地址：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">创建者：</label>
			<div class="controls">
				<form:input path="createId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色：</label>
			<div class="controls">
				<form:input path="roleId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls">
				<form:input path="titleImgUuid" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">账户当前状态：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getPcuserStatusMap()}" htmlEscape="false" class=""/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">所在部门：</label>
			<div class="controls">
				<form:input path="departId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">账户是否被锁定：</label>
			<div class="controls">
				<form:radiobuttons path="isLocked" items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value"   htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pc:pcuser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>