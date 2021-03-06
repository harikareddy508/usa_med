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
<%@include file="commonInclude.jsp"%>
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.css" />">
<!-- DataTables -->
<script
	src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>
<!-- SlimScroll -->
<script
	src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js"/>"></script>
<title>USAMD |Locate Doctor</title>


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

		$('#example2').DataTable({
			"paging" : true,
			"lengthChange" : false,
			"searching" : false,
			"ordering" : true,
			"info" : true,
			"autoWidth" : true
		});
		
		$('#msgLblRed').hide();
						if ('${FAILURE_MESSAGE}' != null
								&& '${FAILURE_MESSAGE}' != "") {
							$('#msgLblRed').show();
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
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header"> </section>
			<section class="content">
			<div class="row">
				<div class="col-xs-6">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Locate Doctors</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<!-- /.box-header -->
							<form:form action="locateDoctor" method="post"
								class="form-horizontal" modelAttribute="doctorBean"
								id="searchDoctorForm">
								<div class="box-body">
									<div class="row">
										<div class="col-xs-3">
											<div class="form-group">
													<usamdTag:RTSelect elementName="specialization"
														referenceTableName="RT_SPECIALITY"
														placeHolder="Specialization"
														code="${doctorBean.specialization}">
													</usamdTag:RTSelect>
												
											</div>
										</div>
										<div class="col-sm-3">
												<form:input type="text" class="form-control"
													placeholder="My Zipcode"
													path="healthCenter.address.zipcode" maxlength="14" />
											
											<!-- /.form-group -->

											<!-- /.col -->
										</div>
										<div class="col-sm-3">
											<button type="submit" id="btnSearch"
												class="btn bg-orange">
												Locate</button>
											<!-- /.form-group -->

											<!-- /.col -->
										</div>
										<!-- /.row -->
									</div>
								</div>
							</form:form>
							<!-- /.box-body -->

						</div>
					</div>
				</div>
			</div>
			<!-- /.box --> <!-- Main content --> <!-- /.row -->
			<div class="row">
				<div class="col-xs-6">
					<div class="box">
						<!-- <div class="box-header">
							<h3 class="box-title">Nearby Doctors</h3>
						</div> -->
						<!-- /.box-header -->
						<div class="box-body">
							<table id="example2" class="table table-bordered">

								<tr>
									<th>Nearby Doctors</th>
								</tr>

								<tbody>
								<div class="alert alert-info alert-dismissable" id="msgLblRed">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<h4>
								<i class="icon fa fa-info"></i>Info!
							</h4>
							${FAILURE_MESSAGE}
						</div>
									<c:forEach items="${SEARCH_DOC_RESULTS}" var="doctor">
										<tr>
											<td><h3>
													Dr.
													<usamdTag:FmtInput fname="${doctor.firstName}"
														lname="${doctor.lastName}"></usamdTag:FmtInput>
													<img class="direct-chat-img pull-right"
														src="<c:url value="/resources/images/doctor.png"/>"
														alt="message user image">
													<!-- /.direct-chat-img -->
												</h3>
												<h4 class="text-aqua">
													<Strong><usamdTag:RTLabel
															referenceTableName="RT_SPECIALITY"
															code="${doctor.specialization}"></usamdTag:RTLabel></Strong>
												</h4> <usamdTag:FmtInput fromDate="${doctor.dateOfPractice}"></usamdTag:FmtInput>
												Experience
												<h4>
													<Strong>Health Center :</Strong>
													${doctor.healthCenter.centerName}<br> <i
														class="fa fa-map-marker margin-r-5"></i><strong>Location</strong><br>
													<usamdTag:FmtInput address="${doctor.healthCenter.address}"></usamdTag:FmtInput>
													<br> <Strong>Email :</Strong> ${doctor.emailId}<span
														class="pull-right"> <Strong>Phone :</Strong>
														${doctor.phoneNumber}
													</span>
												</h4></td>
										</tr>

									</c:forEach>
								</tbody>

							</table>
						</div>

					</div>
				</div>
			</div>



			</section>
			<!-- /.content -->
			<section class="content"> </section>

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