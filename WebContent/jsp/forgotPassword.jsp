<%@include file="taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LMS|Forgot Password</title>
<%@include file="commonInclude.jsp"%>

<script>
	$(function() {
		$('input').iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%' // optional
		});
		$('#msgLblRed').hide();
		if ('${FAILURE_MESSAGE}' != null && '${FAILURE_MESSAGE}' != "") {
			$('#msgLblRed').show();
		}

		$('#msgLblGreen').hide();
		if ('${SUCCESS_MESSAGE}' != null && '${SUCCESS_MESSAGE}' != "") {
			$('#msgLblGreen').show();
		}
	});
</script>
</head>
<body
	class="hold-transition skin-green-light layout-top-nav layout-boxed">
	<div class="wrapper">
		<!-- Header-Content -->
		<%@include file="fragments/header.jspf"%>
		<!--/ .Header-Content -->


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper"
			style="background-position: center;background-repeat: no-repeat;background-size:cover;width=100%;height:100%;background-image:url('<c:url value="/resources/images/homeImage.jpg"/>'); ">
			<!-- Content Header (Page header) -->
			<section class="content-header"> </section>

			<div class="register-box">


				<div class="register-box-body">
					<p class="login-box-msg">
						<b>Forgot Password</b>
					</p>
					<div class="alert alert-danger" id="msgLblRed">
						<strong> ${FAILURE_MESSAGE}</strong>
					</div>
					<div class="alert alert-success" id="msgLblGreen">
						<strong> ${SUCCESS_MESSAGE}</strong>
					</div>
					<form:form id="forgotPasswordForm" method="post"
						action="forgotPassword" modelAttribute="loginBean">
						<form:input path="validationType" value="1" type="hidden"/>
						<div class="form-group has-feedback">
							<form:input class="form-control" id="username" name="username"
								path="username" placeholder="Username (Required)" />
							<span style="color: red"><form:errors path="username"
									cssClass="error" /></span> <span
								class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>



						<div class="form-group has-feedback">
							<usamdTag:RTSelect elementName="securityQuesCd"
								referenceTableName="RT_SECURITY_QUESTIONS"
								placeHolder="Security Question (Required)" code="${loginBean.securityQuesCd}">
							</usamdTag:RTSelect>
							<span style="color: red"><form:errors
									path="securityQuesCd" cssClass="error" /></span> <span
								class="glyphicon form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<form:input type="password" class="form-control"
								path="securityAns" placeholder="Security Answer (Required)" />
							<span style="color: red"><form:errors path="securityAns"
									cssClass="error" /></span> <span
								class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<form:input type="password" class="form-control" path="password"
								placeholder="Password" />
							<span style="color: red"><form:errors path="password"
									cssClass="error" /></span> <span
								class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<form:input type="password" class="form-control"
								placeholder="Confirm password (Required)" path="retypePassword" />
							<span style="color: red"><form:errors path="retypePassword"
									cssClass="error" /></span> <span
								class="glyphicon glyphicon-log-in form-control-feedback"></span>
						</div>

						<div class="row">
							<div class="col-xs-12">
								<button type="submit" class="btn bg-orange btn-block">Reset
									Password</button>
							</div>
							<!-- /.col -->
						</div>
					</form:form>
					<br> <a href="<c:url value="/login"/>" class="text-center">Go
						to login page</a>
				</div>
				<!-- /.form-box -->
			</div>
			<!-- /.register-box -->
		</div>
	</div>


</body>
</html>