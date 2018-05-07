<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
      html,body,table{width:100%;text-align:center;}
    

      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
.loginhead{
	width: 722px;
	height: 523px;
	/*border: 1px solid blue;*/
	margin: auto;
	margin-top: 100px;
	background: url("${ctxStatic}/anfa/login_bj.jpg");
	position: relative;
}


.loginhead p{
	position: absolute;
	left: 200px;
	top: 160px;
	color: deepskyblue;
	font-size: 18px;
}

input.input1{
	position: absolute;
	left: 120px;
	top: 220px;
	height: 40px;
	width: 250px;
	text-indent: 20px;
	border: 0px;
    margin:0px;
	padding:0px;
}

input.input2{
	position: absolute;
	left: 120px;
	top: 277px;
	width: 250px;
	height: 40px;
	text-indent: 20px;
	border: 0px;
	margin:0px;
	padding:0px;
}
input.input3{
	position: absolute;
	left: 110px;
	top: 360px;
	width: 248px;
	height: 40px;
	border: 0px;
	background-color: #0b4c94;
	color: white;
	border-radius: 5px;
	cursor: pointer;
    
}
.input3 a{
	text-decoration: none;
	color: white;
}
.label1{
	display: inline-block;
	width: 30px;
    border: 1px solid white;
    height: 38px;
    margin-left: 30px;
    position: absolute;
    left: 62px;
    top: 220px;
    background: url("${ctxStatic}/anfa/login_1.jpg") no-repeat center;
    background-color: white;
    
}

.label2{
	display: inline-block;
	width: 30px;
    border: 1px solid white;
    height: 38px;
    margin-left: 30px;
    position: absolute;
    left: 62px;
    top: 277px;
    background: url("${ctxStatic}/anfa/login_2.jpg") no-repeat center;
    background-color: white;
}
#lbredme{
    position: absolute;
    bottom: 50px;
    left:85px;
}

.dropdown{
    position: absolute;
    bottom: 50px;
    left: 300px;
}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
		
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
	<div class="loginhead"> 
	    <p>欢迎登陆</p>
		<label class="label1"></label><input id="username" name="username"  class="input1" type="text" placeholder="用户名">
		<label class="label2"></label><input id="password" name="password"  class="input2" type="password" placeholder="请输入密码">
		<c:if test="${isValidateCodeLogin}"><div class="validateCode">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div></c:if><%--
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
		<!-- <input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp; -->
		<input class="input3" type="submit" value="登 录"/>
		<label for="rememberMe" id="lbredme" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
		<div id="themeSwitch" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
			<ul class="dropdown-menu">
			  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
			</ul>
			<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
		</div>
	 
	</div>
	 </form>
	<div class="footer">
		Copyright &copy; ${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="" target="_blank">ANAF-DEV</a> ${fns:getConfig('version')} 
	</div>
</body>
</html>