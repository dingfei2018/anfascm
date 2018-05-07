<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店铺管理</title>
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
		<li><a href="${ctx}/pc/shop/">店铺列表</a></li>
		<li class="active"><a href="${ctx}/pc/shop/form?id=${shop.id}">店铺<shiro:hasPermission name="pc:shop:edit">${not empty shop.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:shop:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shop" action="${ctx}/pc/shop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">店铺显示名称：</label>
			<div class="controls">
				<form:input path="shopName" htmlEscape="false" maxlength="125" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用于访问店铺的子域名：</label>
			<div class="controls">
				<form:input path="shopSubdomain" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺描述：</label>
			<div class="controls">
				<form:input path="shopDesc" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司简介：</label>
			<div class="controls">
				<form:input path="shopDescShort" htmlEscape="false" maxlength="450" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司网站形象照片-图片库图片组id：</label>
			<div class="controls">
				<form:input path="figureImgGid" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">首页宣传图片-图片库图片组id：</label>
			<div class="controls">
				<form:input path="scrollImgGid" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺首次开通时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shop.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺信息最后更新时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shop.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺主题：</label>
			<div class="controls">
				<form:input path="theme" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户id：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${shop.user.id}" labelName="user.name" labelValue="${shop.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司品牌简述：</label>
			<div class="controls">
				<form:input path="cultureDesc" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌相关图片：</label>
			<div class="controls">
				<form:input path="cultureImgGid" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司价值观：</label>
			<div class="controls">
				<form:input path="cultureJzg" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司使命：</label>
			<div class="controls">
				<form:input path="cultureSm" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否显示公司营业执照，1-显示，0-不显示(默认)：</label>
			<div class="controls">
				<form:input path="showYyzz" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否显示公司法人/责任人身份证，1-显示，0-不显示(默认)：</label>
			<div class="controls">
				<form:input path="showSfz" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否显示公司法人/负责人手机号码，1-显示，0-不显示(默认)：</label>
			<div class="controls">
				<form:input path="showMobile" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否显示公司网点负责人手机号码，1-显示，0-不显示(默认)：</label>
			<div class="controls">
				<form:input path="showNetworkMobile" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pc:shop:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>