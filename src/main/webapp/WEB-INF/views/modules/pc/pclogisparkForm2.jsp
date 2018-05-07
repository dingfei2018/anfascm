<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流园管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/anfa/city.js?12" type="text/javascript"></script>
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
		<li><a href="${ctx}/pc/pclogispark/">物流园列表</a></li>
		<li class="active"><a href="${ctx}/pc/pclogispark/form?id=${pclogispark.id}">物流园<shiro:hasPermission name="pc:pclogispark:edit">${not empty pclogispark.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:pclogispark:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pclogispark" action="${ctx}/pc/pclogispark/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">物流园名称：</label>
			<div class="controls">
				<form:input path="parkName" htmlEscape="false" maxlength="45" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	
		<div class="control-group" >
		    <label class="control-label">物流园详细地址：</label>
		    <div id="startId" class="controls">
		                        <input id="bookid" name="bookid" type="hidden" value="${book.id}"/>
            		            <select id="loc_province" name="province" style="width:105px"></select> 
								<select id="loc_city" name="city" style="width:105px"></select>
								<select id="loc_area" name="area" style="width:105px"></select>
								<input id="tailAddress" name="tailAddress" class="input-xlarge valid" type="text" value="${book.tailAddress}" maxlength="32" /> 
								<span class="help-inline"><font color="red">*</font> </span>
								<script type="text/javascript">
								$("#startId").region({areaField:"area",cityField:"city",domain:"http://dev.anfa.com",currAreaCode:"${book.regionCode}", crossDomain:true,callback:function(part){showSelectedRegion(part);}});
								</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物流园所在地经纬度：</label>
			<div class="controls">
			    <input id="locationXY" name="locationXY" class="input-xlarge valid" type="text" value="${book.locationXY}" />
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="parkRemark" htmlEscape="false" rows="4" maxlength="45" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pc:pclogispark:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>