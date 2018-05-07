<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pc/pcuser/">平台用户列表</a></li>
		<%-- <shiro:hasPermission name="pc:pcuser:edit"><li><a href="${ctx}/pc/pcuser/form">平台用户添加</a></li></shiro:hasPermission> --%>
	</ul>
	
	<form:form id="searchForm" modelAttribute="pcuser" action="${ctx}/pc/pcuser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		  <li><span>手机号：</span><form:input path="mobile" htmlEscape="false" maxlength="50" class="input-small" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		  <li><span>公司名称：</span><form:input path="pccompany.corpname" htmlEscape="false" maxlength="50" class="input-small" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	  	  <li><span>用户类型：</span>
	  	     <form:select path="usertype" class="input-xlarge ">
					<form:option value="-1"> 请选择用户类型 </form:option>
					<form:options items="${fns:getPcuserTypeMap()}"  htmlEscape="false"/>
			 </form:select>
	  	  </li>
	  	  <li>
	  	      <span class="banner-right-span2">注册时间：</span>
	  	      <input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="${paramMap.beginDate}"
	  				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;
	  				<span>-</span>
	  				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="${paramMap.endDate}" 
	  				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;
	  	  </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<!-- <thead><tr><th>ID</th><th>用户登陆名</th><th>用户显示名</th><th>用户注册日期</th><th>用户类型</th><th>账户被锁定</th><th>在线店铺</th><th>专线用户</th><th>操作</th></tr></thead> -->
		<thead><tr><th>用户ID</th><th>登陆手机</th><th>公司名称</th><th>公司电话</th><th>用户类型</th><th>用户注册时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="pcuser">
			<tr>
			    <td>${pcuser.id}</td>
			    <td>${pcuser.mobile}</td>
			    <td>${pcuser.pccompany.corpname}</td>
			    <td>${pcuser.pccompany.corpTelphone}</td>
			    <%--  <td>${pcuser.realname}</td> --%>			    
			    <td>${fns:getPcuserType(pcuser.usertype)}</td>
			    <td><fmt:formatDate value="${pcuser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			    <%--  <td>${pcuser.isLocked==1?'是':'否'}</td>
			    <td>${pcuser.isOnshop==1?'是':'否'}</td>
			    <td>${pcuser.isTransline==1?'是':'否'}</td> --%>
				<%-- <td>
				    <a href="${ctx}/pc/pcuser/view-${pcuser.id}">访问</a>
    				<shiro:hasPermission name="pc:pcuser:edit"><a href="${ctx}/pc/pcuser/form?id=${pcuser.id}">修改</a></shiro:hasPermission>
					<shiro:hasPermission name="pc:pcuser:delete"><a href="${ctx}/pc/pcuser/delete?id=${pcuser.id}" onclick="return confirmx('确认要删除该平台用户吗？', this.href)">删除</a></shiro:hasPermission>
				</td> --%>
				<td><shiro:hasPermission name="pc:pcuser:edit"><a href="${ctx}/pc/pcuser/form?id=${pcuser.id}">查看详情</a></shiro:hasPermission> </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>