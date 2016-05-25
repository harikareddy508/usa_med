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
<title>USAMD |Search Doctor</title>


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
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Search Criteria</h3>
				</div>
				<!-- /.box-header -->
				<form:form action="searchDoctor" method="post"
					class="form-horizontal" modelAttribute="doctorBean"
					id="searchDoctorForm">
					<div class="box-body">
						<div class="row">
							<div class="col-sm-4">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Specialization
									</label>
									<div class="col-sm-6">
										<usamdTag:RTSelect elementName="specialization"
											referenceTableName="RT_SPECIALITY"
											placeHolder="Specialization"
											code="${doctorBean.specialization}">
										</usamdTag:RTSelect>
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Doctor
										Id </label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Doctor Id" path="doctorId" maxlength="42" />
									</div>
								</div>
								<!-- /.form-group -->

								<!-- /.col -->
							</div>
							<div class="col-sm-3">
								<label for="inputEmail3" class="col-sm-4 control-label">Zipcode</label>
								<div class="col-sm-6">
									<form:input type="text" class="form-control"
										placeholder="Zipcode" path="healthCenter.address.zipcode"
										maxlength="14" />
								</div>
								<!-- /.form-group -->

								<!-- /.col -->
							</div>
							<div class="col-sm-2">
								<button type="submit" id="btnSearch"
									class="btn bg-orange">
									Search</button>
								<!-- /.form-group -->

								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
					</div>
				</form:form>
				<!-- /.box-body -->

			</div>
			<!-- /.box --> <!-- Main content --> <!-- /.row -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Search Results</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="example2" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Doctor Id</th>
										<th>Specialization</th>
										<th>Health Center</th>
										<th>Address</th>
										<th>Experience</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${SEARCH_DOC_RESULTS}" var="doctor">
										<tr>
											<td><usamdTag:FmtInput fname="${doctor.firstName}"
													lname="${doctor.lastName}"></usamdTag:FmtInput></td>
											<td><usamdTag:RTLabel referenceTableName="RT_SPECIALITY"
													code="${doctor.specialization}"></usamdTag:RTLabel></td>
											<td>${doctor.healthCenter.centerName}</td>
											<td><usamdTag:FmtInput
													address="${doctor.healthCenter.address}"></usamdTag:FmtInput></td>
											<td><usamdTag:FmtInput fromDate="${doctor.dateOfPractice}"></usamdTag:FmtInput>  </td>
											<td><a class="fa fa-edit"
												href="<c:url value="/editDoctor/${doctor.doctorId}"/>"></a>
												<a class="fa fa-trash-o"
												href="<c:url value="/deleteDoctor/${doctor.doctorId}"/>"></a></td>

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