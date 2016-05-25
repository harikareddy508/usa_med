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
<title>USAMD |Calculate BMI</title>
<%@include file="commonInclude.jsp"%>

<script>
	$(document).ready(function() {
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
			<!-- <section class="content-header"> </section> -->
			<!-- Main content -->
			<section class="content">
			
			<div class="row" style="margin-left: 10%">
				<div class="col-md-8" style="margin-top: 10px;">
					<!-- Horizontal Form -->
					<div class="box box-info" style="padding: 10px;">
						<div class="box-header with-border">
							<h3 class="box-title">
								<strong>BMI Calculator</strong>
							</h3>
						</div>
						<div class="alert alert-danger alert-dismissable" id="msgLblRed">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<h4>
								<i class="icon fa fa-ban"></i>Alert!
							</h4>
							${FAILURE_MESSAGE}
						</div>
						<div class="alert alert-success alert-dismissable"
							id="msgLblGreen">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<h4>
								<i class="icon fa fa-check"></i>Success!
							</h4>
							${SUCCESS_MESSAGE}
						</div>
						<form:form action="calculateBMI" method="post"
							class="form-horizontal" modelAttribute="bmiCalViewBean"
							id="bmiCalViewForm">
							<div class="box-body">


								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Age
										in years <span style="color: red">*</span>
									</label>
									<div class="col-sm-4">
										<form:input type="text" class="form-control"
											placeholder="Age in years" path="age" />
										<span style="color: red"><form:errors path="age"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Gender
										<span style="color: red">*</span>
									</label>
									<div class="col-sm-6">
										<form:radiobutton path="gender" value="M" />
										Male
										<form:radiobutton path="gender" value="F" />
										Female <span style="color: red"><form:errors
												path="gender" cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Height
										<span style="color: red">*</span>
									</label>
									<div class="col-sm-2">
										<form:input type="text" class="form-control"
											placeholder="Feet" path="heightFeet" />
										<span style="color: red"><form:errors path="heightFeet"
												cssClass="error" /></span>
									</div>
									<div class="col-sm-2">
										<form:input type="text" class="form-control"
											placeholder="Inches" path="heightInch" />
										<span style="color: red"><form:errors path="heightInch"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Weight
										<span style="color: red">*</span>
									</label>
									<div class="col-sm-4">
										<form:input type="text" class="form-control"
											placeholder="Pounds" path="weight" />
										<span style="color: red"><form:errors path="weight"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group col-xs-8 pull-right">
									<div class="col-sm-8">
										<button type="submit" id="btnAdd" class="btn bg-orange btn-md">Calculate
										</button>
									</div>
									<!-- /.col -->

									<!-- /.col -->
								</div>
							</div>
						</form:form>
					</div>

				</div>
				<!-- /.form-box -->
			</div>
			<c:if test="${BMI != null && !BMI.isEmpty()}">
			<div class="row" style="margin-left: 10%">
				<div class="col-md-8" style="margin-top: 10px;">
					<!-- Horizontal Form -->

					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">
								<strong>Results</strong>
							</h3>
						</div>
						<div class="box-body bg-info">
							<strong>Your BMI is ---------  <span class="text-red">${BMI} kg/m2</span><br>
							BMI Category ----- <span class="text-red" > <usamdTag:RTLabel referenceTableName="RT_BMI_CATEGORY" code="${category}"></usamdTag:RTLabel></span></strong>
						<p><strong>Health Plan : </strong><span>${healthPlan}</span></p>
						</div>
					</div>
				</div>
			</div></c:if>
			<!-- /.register-box --> </section>
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