<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function viewComment(href){
			top.$.jBox.open('iframe:'+href,'查看评论',$(top.document).width()-220,$(top.document).height()-120,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
			return false;
		}
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
		<li class="active"><a href="${ctx}/pc/verify">认证列表</a></li>
	</ul>
	<div class="banner-right-top">	
	  <form:form id="searchForm" modelAttribute="verifyData" action="${ctx}/pc/verify/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<span>认证提交日期：</span>
	  				<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="${paramMap.beginDate}"
	  				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;
	  				<span>-</span>
	  				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="${paramMap.endDate}" 
	  				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;
	  				<span >审核状态：</span>
	  				<form:select path="status" width="120">
                       <form:option value="-1"> 请选择审核状态 </form:option>
                       <form:options items="${statusList}"  />
                   </form:select>
	  				<span >用户名：</span>
	  				<form:input path="pcuser.username" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
	  				<span>公司名称：</span><form:input path="pcuser.pccompany.corpname" htmlEscape="false" maxlength="50" class="input-small" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
	 </form:form>
	</div>
	  				
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>ID</th><th>手机</th><th>公司名称</th><th>用户注册日期</th><th>审核状态</th><th>审核人</th><th>提交日期</th><th>审核日期</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="vdata">
			<tr>
				<td>${vdata.id}</td>
				<td>${vdata.pcuser.mobile}</td>
				<td>${vdata.pcuser.pccompany.corpname}</td>
				<td><fmt:formatDate value="${vdata.pcuser.createTime}" type="both"/></td>
				<td>${vdata.statusDesc}</td>
				<td><c:out value="${vdata.chkUser.loginName}" default="-" /></td>
				<td>
				   <c:if test="${vdata.createTime!=null}">
				      <fmt:formatDate value="${vdata.createTime}"  type="both"/>
				   </c:if>
				   <c:if test="${vdata.createTime==null}">
				       -
				   </c:if>
				</td>
				<td>
				   <c:if test="${vdata.vertifyTime!=null}">
				      <fmt:formatDate value="${vdata.vertifyTime}"  type="both"/>
				   </c:if>
				   <c:if test="${vdata.vertifyTime==null}">
				       -
				   </c:if>
				</td>
				<td>
					<a href="${ctx}/pc/verify/form?id=${vdata.id}">查看资质</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>