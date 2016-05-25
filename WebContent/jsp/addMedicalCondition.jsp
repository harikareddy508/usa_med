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
<title>USAMD |Add Medical Condition</title>
<%@include file="commonInclude.jsp"%>

<script>
	$(document)
			.ready(
					function() {
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
						$('#idBodyPart')
								.on(
										'change',
										'select',
										function() {
											var val = $('#idBodyPart select')
													.val();
											$
													.ajax({
														url : 'fetchBodySymptoms',
														method : 'POST',
														data : {
															bodyPart : val
														},
														dataType : 'json',
														success : function(data) {
															//alert("success Response"	+ data);
															var $el = $("#idSmpSelect select");
															$el.empty(); // remove old options
															$el
																	.append($(
																			"<option></option>")
																			.attr(
																					"value",
																					'')
																			.text(
																					'Please Select'));
															$
																	.each(
																			data,
																			function(
																					i,
																					obj) {
																				$el
																						.append($(
																								"<option></option>")
																								.attr(
																										"value",
																										obj.smpId)
																								.text(
																										obj.description));
																			});

														},
														complete : function(
																data) {

														}

													});
										});

						$('#btnAddSmp')
								.click(
										function() {
											var smpId = $('#idSmpSelect select')
													.val();
											$
													.ajax({
														url : 'maintainSelSmpsMap',
														method : 'POST',
														data : {
															smpId : smpId
														},
														dataType : 'json',
														success : function(data) {
															var smpRowCnt = 0;
															var $el = $("#smpTable");
															$el.empty(); // remove old options		
															$
																	.each(
																			data,
																			function(
																					i,
																					obj) {
																				smpRowCnt++;

																				$el
																						.append("<tr><td>"
																								+ smpRowCnt
																								+ "</td><td>"
																								+ obj.bodyPart
																								+ "<input type='hidden' value='"
															+ obj.smpId
															+ "'/></td><td>"
																								+ obj.smpId
																								+ "</td><td>"
																								+ obj.description
																								+ "</span></td><td> <button type='button' class='fa fa-trash-o' id='delete"
																								+ smpRowCnt
																								+ "' onclick=\"deleteSmp('"
																								+ obj.smpId
																								+ "'); \"></button></td></tr>");
																			});

														}
													});
										});

					});

	function deleteSmp(smpId) {
		//alert(smpId);
		$
				.ajax({
					url : 'deleteSelSmpsMap',
					method : 'POST',
					data : {
						smpId : smpId
					},
					dataType : 'json',
					success : function(data) {
						var smpRowCnt = 0;
						var $el = $("#smpTable");
						$el.empty(); // remove old options		
						$
								.each(
										data,
										function(i, obj) {
											smpRowCnt++;

											$el
													.append("<tr><td>"
															+ smpRowCnt
															+ "</td><td>"
															+ obj.bodyPart
															+ "<input type='hidden' value='"
															+ obj.smpId
															+ "'/></td><td>"
															+ obj.smpId
															+ "</td><td>"
															+ obj.description
															+ "</span></td><td> <button type='button' class='fa fa-trash-o' id='delete"
															+ smpRowCnt
															+ "' onclick=\"deleteSmp('"
															+ obj.smpId
															+ "'); \"></button></td></tr>");
										});
					}
				});
	}
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

			<!-- Main content -->
			<section class="content">
			<div class="row" style="margin-left: 10%">
				<div class="col-md-10" style="margin-top: 20px;">
					<!-- Horizontal Form -->
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">Add Medical Condition</h3>
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
						<form:form action="addMedicalCondition" method="post"
							class="form-horizontal" modelAttribute="medicalConditionBean"
							id="addMedicalCondtionForm">
							<div class="box-body">
								<!------------------- Medical Condition -------------------------------------->
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Condition
										Name<span style="color: red">*</span>
									</label>
									<div class="col-sm-6">
										<form:input type="hidden" class="form-control" path="mcId" />
										<form:input type="text" class="form-control"
											placeholder="Condition Name" path="name" maxlength="14" />
										<span style="color: red"><form:errors path="name"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Description<span
										style="color: red">*</span></label>
									<div class="col-sm-6">
										<form:textarea type="text" class="form-control"
											placeholder="Descriptiom" path="description" maxlength="1000" />
										<span style="color: red"><form:errors
												path="description" cssClass="error" /></span>
									</div>
								</div>
								<!-------------------./ Medical Condition Ends-------------------------------------->
							</div>
							<!-------------------Add Symptoms -------------------------------------->

							<div class="row" style="margin-left: 0%">
								<div class="col-md-10" style="margin-top: 10px;">
									<!-- Horizontal Form -->
									<div class="box box-info" style="padding: 20px;">
										<div class="box-header with-border">
											<h3 class="box-title">Add/Update Symptoms</h3>
										</div>
										<div class="form-group has-feedback">
											<label for="inputEmail3" class="col-sm-4 control-label">Body
												Part<span style="color: red">*</span>
											</label>
											<div class="col-sm-6" id="idBodyPart">
												<usamdTag:RTSelect elementName="humanPart"
													referenceTableName="RT_BODY_PARTS"
													placeHolder="Select Body Part">
												</usamdTag:RTSelect>
												<span style="color: red"><form:errors path=""
														cssClass="error" /></span> <span
													class="glyphicon form-control-feedback"></span>
											</div>

										</div>

										<div class="form-group has-feedback">
											<label for="inputEmail3" class="col-sm-4 control-label">Symptom
												Description</label>
											<div class="col-sm-6" id="idSmpSelect">
												<Select class="form-control" maxlength="14" id="smpDesc"
													name="smpDesc">
													<option value="">Select Symptom</option>
													<!-- <option value="HD">Headache</option>
													<option value="IR">Irritation</option>
													<option value="MG">migrain</option> -->

												</Select>
											</div>
										</div>


										<div class="form-group col-xs-8 pull-right">

											<div class="col-sm-6">
												<button type="button" id="btnAddSmp"
													class="btn bg-orange btn-block">Add</button>
											</div>
											<!-- /.col -->

											<!-- /.col -->
										</div>

										<table id="example2"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>SNO</th>
													<th>Body Part</th>
													<th>Symptom Code</th>
													<th>Symptom Description</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody id="smpTable">
												<c:if test="${LOAD == 'Y'}">
													<c:forEach items="${SELECTED_SMPS_LIST}" var="smp"
														varStatus="cnt">
														<tr>
															<td>${cnt.index+1}</td>
															<td>${smp.smpId}</td>
															<td>${smp.bodyPart}</td>
															<td>${smp.description}</td>
															<td>
																<button type='button' class='fa fa-trash-o'
																	onclick="deleteSmp('${smp.smpId}')"></button>
															</td>

														</tr>
													</c:forEach>
												</c:if>
											</tbody>
										</table>
									</div>
								</div>
								<div class="form-group col-xs-8 pull-right">
								<div class="col-sm-8">
									<button type="submit" id="btnAddCond" class="btn bg-orange">Save
										Medical Condition</button>
								</div>
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