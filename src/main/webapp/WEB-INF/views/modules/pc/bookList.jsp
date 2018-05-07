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
		<li class="active"><a href="${ctx}/cms/article/?category.id">地址列表</a></li>
		<shiro:hasPermission name="pc:addressbook:edit"><li><a href="<c:url value='${fns:getAdminPath()}/pc/addressbook/form'>
		</c:url>">地址添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="addressBook" action="${ctx}/pc/addressbook/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>标题：</label><form:input path="regionCode" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>地址</th><th>经纬度</th><th>创建时间</th><th>用户</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="book">
			<tr>
				<td>${book.fullAddress}</td>
				<td>${book.locationXY}</td>
				<td><fmt:formatDate value="${book.createTime}" type="both"/></td>
				<td>${pc:getPCUserName(book.userId)}</td>
				<td>
					<shiro:hasPermission name="cms:article:edit"><a href="${ctx}/pc/addressbook/form?id=${book.id}">修改</a></shiro:hasPermission>
	    			<shiro:hasPermission name="cms:article:audit"><a href="${ctx}/pc/addressbook/delete?id=${book.id}" onclick="return confirmx('确认要删除该地址吗？', this.href)" >删除</a></shiro:hasPermission>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>