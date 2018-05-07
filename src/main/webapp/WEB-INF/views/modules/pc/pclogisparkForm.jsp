<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流园管理</title>
	<meta name="decorator" content="default"/>	
	<script type="text/javascript">
	$(document).ready(function() {
        if($("#link").val()){
            $('#linkBody').show();
            $('#url').attr("checked", true);
        }
		$("#title").focus();
		$("#inputForm").validate({	
			submitHandler: function(form){
				var [target,error,message] = addressValidate();
				if(error){
					target.focus();
					top.$.jBox.tip(message,'warning');
				}else if($("input[name='park.parkName']").val()==""){
					$("input[name='park.parkName']").focus();
					top.$.jBox.tip('物流园名称','warning');
                }else{
                    loading('正在提交，请稍等...');
                    form.submit();
                }
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is

(".input-append")){
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
		<li><a href="${ctx}/pc/pclogispark/">物流园列表</a></li>
		<li class="active"><a href="${ctx}/pc/pclogispark/form?id=${pclogispark.id}">物流园<shiro:hasPermission name="pc:pclogispark:edit">${not empty pclogispark.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:pclogispark:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="parkbook" action="${ctx}/pc/pclogispark/save" method="post" class="form-horizontal">
		<form:hidden path="park.id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">物流园名称：</label>
			<div class="controls">
				<form:input path="park.parkName" htmlEscape="false" maxlength="45" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<c:set var="bookName" value="物流园" />
		<c:set var="regionCode" value="${parkbook.book.regionCode}" />
		<%@include file="/WEB-INF/views/include/address.jsp" %>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="park.parkRemark" htmlEscape="false" maxlength="45" class="input-xlarge"/>
			</div>
		</div>	
		<div class="form-actions">
			<shiro:hasPermission name="pc:pclogispark:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>