<%@include file="taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1  user-scalable=no">
<title>USAMD |Login</title>
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
	});
</script>
<style type="text/css">
img {
	background-image:
		url('<c:url value="/resources/images/homeImage.jpg"/>');
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	width: 100%;
	height: 100%;
}
</style>
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

			<!-- Main content -->
			<section class="content"> <%-- <img src="<c:url value="/resources/images/homeImage.jpg"/>"> --%>
			<div class="login-box ">

				<!-- /.login-logo -->
				<div class="login-box-body">
					<p class="login-box-msg">
						<b>Sign in to USA-MD</b>
					</p>
					<div class="alert alert-danger" id="msgLblRed">
						<strong> ${FAILURE_MESSAGE}</strong>
					</div>
					<form:form id="loginForm" method="post" action="login"
						modelAttribute="loginBean">
						<div class="form-group has-feedback">
							<form:input class="form-control" id="username" name="username"
								path="username" placeholder="Username (Required)" />
							<span style="color: red"><form:errors path="username"
									cssClass="error" /></span> <span
								class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<form:password id="password" name="password" path=""
								class="form-control" placeholder="Password (Required)" />
							<span style="color: red"><form:errors path="password"
									cssClass="error" /></span> <span
								class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						<div class="row">							
							<div class="col-xs-5">
								<button type="submit" class="btn bg-orange btn-block">Sign
									In</button>

							</div>	
						</div>
						<!-- /.col -->
						
					</form:form>
					<br>
					<div class="row">
						<div class="col-xs-8">
							<a href="<c:url value="/forgotPassword"/>">Forgot your
								password?</a>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-8">
							<a href="<c:url value="/register"/>">Register as a new member
								</a>
						</div>
					</div>
				</div>

				<!-- /.login-box-body -->
			</div>
			<!-- /.login-box --> </section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Footer -->
		<%@include file="fragments/footer.jspf"%>
		<!-- /.footer -->

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.1.4 -->

</body>

</html>