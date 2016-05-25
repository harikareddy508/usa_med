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
<title>USAMD |Search Medicine</title>
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
				<div class="col-md-6" style="margin-top: 20px;">
					<!-- Horizontal Form -->
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">Medicine Information</h3>
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
						<form:form action="searchMedicine" method="post"
							class="form-horizontal" modelAttribute="medicineInfoBean"
							id="medicineForm">
							<div class="box-body">
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Medicine
										Name(Use '%' for like search)<span style="color: red">*</span>
									</label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Medicine Name" path="medName" maxlength="30" />
										<span style="color: red"><form:errors path="medName"
												cssClass="error" /></span> <span
											class="glyphicon form-control-feedback"></span>
									</div>
								</div>

								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Color									
									</label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Medicine Color" path="color" maxlength="30" />
										<span
											class="glyphicon form-control-feedback"></span>
									</div>
								</div>


								<div class="form-group col-xs-8 pull-right">
									<div class="col-sm-6">
										<button type="submit" id="btnSearch" class="btn bg-orange">Search</button>
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
			<div class="row" style="margin-left: 10%">
				<div class="col-md-6">
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">Search Medicine Results</h3>
						</div>
						<div class="box-body">
							<table class="table table-bordered table-stripped">
								<thead>
									<tr>
										<th>Medicine Name</th>
										<th>Color</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody id="bodySmps">
									<c:forEach items="${MEDICINE_LIST}" var="med">
										<tr>
											<td>${med.medName}</td>
											<td>${med.color}</td>
											<td><a class="fa fa-edit"
												href="<c:url value="/editMedicine/${med.medId}"/>"></a> <a
												class="fa fa-trash-o"
												href="<c:url value="/deleteMedicine/${med.medId}"/>"></a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


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