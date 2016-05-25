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
<title>USAMD |Add Symptoms</title>
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

						$('#idparts select')
								.change(
										function() {
											var val = $('#idparts select')
													.val();
											//alert(val);
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
															var $el = $("#bodySmps");
															$el.empty(); // remove old options		
															$
																	.each(
																			data,
																			function(
																					i,
																					obj) {
																				$el
																						.append("<tr><td>"
																								+ obj.bodyPart
																								+ "<input type='hidden' value='"
															+ obj.smpId
															+ "'/></td><td>"
																								+ obj.description
																								+ "</td><td><button title='Edit Symptom' class='fa fa-edit' value='Edit' onclick=\"selectSmp('"
																								+ obj.smpId
																								+ "','"
																								+ obj.description
																								+ "','"
																								+ obj.bodyPart
																								+ "'); \" ></button>"
																								+ "<button title='Delete Symptom' class='fa fa-trash' value='Delete' onclick=\"deleteSmp('"
																								+ obj.smpId
																								+ "')\"></button>"
																								+ "</td></tr>");
																			});
														}
													});
										});

					});

	function selectSmp(smpId, description, bodyPart) {
		$('#bodyPart').val(bodyPart);
		$('#description').val(description);
		$('#smpId').val(smpId);
	}

	function deleteSmp(smpId) {
		$('#delSmpId').val(smpId);
		$('#deleteSmpForm').submit();
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
			<!-- <section class="content-header"> </section> -->
			<!-- Main content -->
			<section class="content">
			<div class="row" style="margin-left: 10%">
				<div class="col-md-6" style="margin-top: 20px;">
					<!-- Horizontal Form -->
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">Add/Update Symptom</h3>
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
						<form:form action="addSymptom" method="post"
							class="form-horizontal" modelAttribute="bodyPartSymptomBean"
							id="addSymptomForm">
							<div class="box-body">
								<%-- 	
								<%@include file="fragments/doctorInfo.jspf" %>
								<%@include file="fragments/healthCenterAddress.jspf" %> --%>

								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Body
										Part<span style="color: red">*</span>
									</label>
									<div class="col-sm-6" id="idparts">
										<form:input type="hidden" path="smpId" />
										<usamdTag:RTSelect elementName="bodyPart"
											referenceTableName="RT_BODY_PARTS"
											placeHolder="Select Body Part"
											code="${bodyPartSymptomBean.bodyPart}">
										</usamdTag:RTSelect>
										<span style="color: red"><form:errors path="bodyPart"
												cssClass="error" /></span> <span
											class="glyphicon form-control-feedback"></span>
									</div>
								</div>

								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Symptom
										Description</label>
									<div class="col-sm-6">
										<form:input type="text" class="form-control"
											placeholder="Symptom Description" path="description"
											maxlength="30" />
										<span style="color: red"><form:errors
												path="description" cssClass="error" /></span>
									</div>
								</div>


								<div class="form-group col-xs-8 pull-right">

									<div class="col-sm-6">
										<button type="submit" id="btnAdd"
											class="btn bg-orange btn-block">Save</button>
									</div>
									<!-- /.col -->

									<!-- /.col -->
								</div>
							</div>
						</form:form>

					</div>
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">Symptoms</h3>
						</div>
						<div class="box-body">
							<table class="table table-bordered table-stripped bg-olive">
								<thead>
									<tr>
										<th>Body Part</th>
										<th>Symptoms</th>
									</tr>
								</thead>
								<tbody id="bodySmps" class="bg-gray">
									<c:if test="${BODY_SYMPTOMS!=null && BODY_SYMPTOMS.size()>0 }">
										<c:forEach items="${BODY_SYMPTOMS}" var="smp">
											<tr>
												<td>${smp.bodyPart}</td>
												<td>${smp.description}</td>
												<td><button title='Edit Symptom' class='fa fa-edit'
														value='Edit'
														onclick="selectSmp('${smp.smpId}','${smp.description}','${smp.bodyPart}')"></button>
													<button title='Delete Symptom' class='fa fa-trash'
														value='Delete' onclick="deleteSmp('${smp.smpId}')"></button>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /.form-box -->
			</div>


			<!-- /.register-box --> </section>
			<!-- /.content -->
		</div>
		<form:form action="deleteSymptom" method="post"
			class="form-horizontal" id="deleteSmpForm"
			modelAttribute="bodyPartSymptomBean">
			<form:input path="" name="delSmpId" id="delSmpId" type="hidden" />
		</form:form>
		<!-- /.content-wrapper -->

		<!-- Footer -->
		<%@include file="fragments/footer.jspf"%>
		<!-- /.footer -->

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.1.4 -->

</body>

</html>