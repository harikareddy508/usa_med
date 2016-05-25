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
<title>USAMD |Doctor Information</title>
<%@include file="commonInclude.jsp"%>

<script>
	$(document)
			.ready(
					function() {
						$('input').iCheck({
							checkboxClass : 'icheckbox_square-blue',
							radioClass : 'iradio_square-blue',
							increaseArea : '20%' // optional
						});
						$('#msgLblRed').hide();
						if ('${FAILURE_MESSAGE}' != null
								&& '${FAILURE_MESSAGE}' != "") {
							$('#msgLblRed').show();
						}

						$('#msgLblGreen').hide();
						if ('${SUCCESS_MESSAGE}' != null
								&& '${SUCCESS_MESSAGE}' != "") {
							$('#msgLblGreen').show();
						}
						$('.input-group.date').datepicker({
							language : 'pt-BR',
							todayHighlight : true,
							autoclose : true
						});

						$('#idState').on(
								'change',
								'select',
								function() {
									/* alert("i am called.. with state "
											+ $('#idState select')
													.val()) */
									var val = $('#idState select').val();
									//alert(val);
									$.ajax({
										url : '<c:url value="/fetchStates"/>',
										method : 'POST',
										data : {
											stateCd : val
										},
										dataType : 'json',
										success : function(data) {
											//alert("success Response" + data);
											var $el = $("#idCity select");
											$el.empty(); // remove old options
											$el.append($("<option></option>")
													.attr("value", '').text(
															'Please Select'));
											$.each(data, function(i, obj) {
												$el.append($(
														"<option></option>")
														.attr("value", obj)
														.text(obj));
											});

										},
										/* 
										 error : function(data) {
										 alert("error"+data);
										 }, */

										complete : function(data) {

										}

									});
								});

						var val = $('#idState select').val();
						var selCity = '${doctorBean.healthCenter.address.city}';
						//alert(selCity);
						//alert(val);
						$.ajax({
							url : '<c:url value="/fetchStates"/>',
							method : 'POST',
							data : {
								stateCd : val
							},
							dataType : 'json',
							success : function(data) {
								//	alert("success Response" + data);
								var $el = $("#idCity select");
								$el.empty(); // remove old options
								$el.append($("<option></option>").attr("value",
										'').text('Please Select'));
								$.each(data, function(i, obj) {
									if (obj == selCity) {
										//		alert(" i am equal");
										$el.append($("<option></option>").attr(
												"value", obj).attr("selected",
												"selected").text(obj));
									} else {
										$el.append($("<option></option>").attr(
												"value", obj).text(obj))
									}

								});

							},
							/* 
							 error : function(data) {
							 alert("error"+data);
							 }, */

							complete : function(data) {

							}

						});

						$('#addDoctorForm')
								.submit(
										function() {
											if ($('#idExistingChk').is(
													":checked")) {
												if ($('#idExistingHc').val() == "") {
													event.preventDefault();
													$('#msgLblRed')
															.text(
																	"Error: Please select any existing health center!");
													$('#msgLblRed').show();
												}
											}

										});

						$('#divExistingHc')
								.on(
										'change',
										'select',
										function() {
											var val = $('#divExistingHc select')
													.val();
											if (val != "") {
												$('#centerName').attr(
														'disabled', 'disabled');
												$('#addrLine1').attr(
														'disabled', 'disabled');
												$('#addrLine2').attr(
														'disabled', 'disabled');
												$('#idState select').attr(
														'disabled', 'disabled');
												$('#idCity select').attr(
														'disabled', 'disabled');
												$('#zipcode').attr('disabled',
														'disabled');
											} else {
												$('#centerName').removeAttr(
														'disabled');
												$('#addrLine1').removeAttr(
														'disabled');
												$('#addrLine2').removeAttr(
														'disabled');
												$('#idState select')
														.removeAttr('disabled');
												$('#idCity select').removeAttr(
														'disabled');
												$('#zipcode').removeAttr(
														'disabled');
											}
										});

						var val = $('#divExistingHc select').val();
						if (val != "") {
							$('#centerName').attr('disabled', 'disabled');
							$('#addrLine1').attr('disabled', 'disabled');
							$('#addrLine2').attr('disabled', 'disabled');
							$('#idState select').attr('disabled', 'disabled');
							$('#idCity select').attr('disabled', 'disabled');
							$('#zipcode').attr('disabled', 'disabled');
						}

						$('#btnEdit').click(function() {
							$('#centerName').removeAttr('disabled');
							$('#addrLine1').removeAttr('disabled');
							$('#addrLine2').removeAttr('disabled');
							$('#idState select').removeAttr('disabled');
							$('#idCity select').removeAttr('disabled');
							$('#zipcode').removeAttr('disabled');

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
							<h3 class="box-title">Edit Doctor Information</h3>
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
						<form:form action="save" method="post" class="form-horizontal"
							modelAttribute="doctorBean" id="editDoctorForm">
							<div class="box-body">
								<form:input path="doctorId" type="hidden" />
								<form:input path="centerId" type="hidden" />
								<form:input path="healthCenter.address.residentId" type="hidden" />
								<%@include file="fragments/doctorInfo.jspf"%><%-- 
								<form:input path="healthCenter.centerId" type="hidden" /> --%>
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Choose
										Existing Health Center <span style="color: red">*</span>
									</label>
									<div class="col-sm-6" id="divExistingHc">
										<form:select path="healthCenter.centerId" class="form-control"
											id="idExistingHc">
											<form:option value="">Select Health Center</form:option>
											<c:forEach items="${EXISTING_HEALTH_CENTERS}" var="hc">
												<form:option value="${hc.centerId}">${hc.centerId} - ${hc.centerName}</form:option>
											</c:forEach>

										</form:select>
										<span class="glyphicon form-control-feedback"></span>
									</div>
									<div class="form-group col-xs-8 pull-right">
										<div class="col-sm-6">
											<button type="button" id="btnEdit" class="btn bg-primary">Edit</button>
										</div>
									</div>
								</div>
								<%@include file="fragments/healthCenterAddress.jspf"%>



								<div class="form-group col-xs-8 pull-right">

									<div class="col-sm-8">
										<button type="submit" id="btnSave" class="btn bg-orange">Save
											Details</button>
										<a id="btnBack" href="<c:url value="/homePage"/> "
											class="btn bg-red">Back to Home</a>
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