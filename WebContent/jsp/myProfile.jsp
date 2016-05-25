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
<title>USAMD |My Profile</title>
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
										url : 'fetchStates',
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
						var selCity = '${webUserBean.address.city}';
						//alert(selCity);
						//alert(val);
						$.ajax({
							url : 'fetchStates',
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

							}

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
				<div class="col-md-6" style="margin-top: 20px;">

					<!-- Horizontal Form -->
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">My Profile</h3>
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
						<form:form action="myProfile" method="post"
							class="form-horizontal" modelAttribute="webUserBean"
							id="profileForm">
							<form:input type="hidden" path="username" maxlength="15" />
							<div class="box-body">
								<%@ include file="fragments/userInfo.jspf"%>
								<!-- Address section -->
								<div>
									<hr>
									<h4>
										<strong><i class="fa fa-map-marker margin-r-5"></i>My
											Address</strong>
									</h4>
								</div>
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Address
										Line 1<span style="color: red">*</span>
									</label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Address Line 1" path="address.addrLine1"
											maxlength="30" />
										<span style="color: red"><form:errors
												path="address.addrLine1" cssClass="error" /></span> <span
											class=" fa fa-map-marker form-control-feedback"></span>
									</div>
								</div>
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Address
										Line 2</label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Address Line 2" path="address.addrLine2"
											maxlength="30" />
										<span style="color: red"><form:errors
												path="address.addrLine2" cssClass="error" /></span> <span
											class=" fa fa-map-marker form-control-feedback"></span>
									</div>
								</div>

								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">State<span
										style="color: red">*</span></label>
									<div class="col-sm-6" id="idState">
										<usamdTag:RTSelect elementName="address.state"
											referenceTableName="RT_STATES" placeHolder="Select State"
											code="${webUserBean.address.state}">
										</usamdTag:RTSelect>
										<span style="color: red"><form:errors
												path="address.state" cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">City<span
										style="color: red">*</span></label>
									<div class="col-sm-6" id="idCity">
										<form:select class="form-control" path="address.city"
											maxlength="14">
											<option value="">Select City</option>
										</form:select>
										<span style="color: red"><form:errors
												path="address.city" cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Zipcode<span
										style="color: red">*</span></label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Zipcode" path="address.zipcode" maxlength="14" />
										<span style="color: red"><form:errors
												path="address.zipcode" cssClass="error" /></span> <span
											class=" fa fa-map-marker form-control-feedback"></span>
									</div>
								</div>
								<div class="row">
									<!-- /.col -->
									<div class="col-xs-12">
										<button type="submit" id="btnSave"
											class="btn bg-orange btn-block">Save My Profile</button>
									</div>
									<!-- /.col -->
								</div>
							</div>
						</form:form>

					</div>
					<!-- /.form-box -->
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