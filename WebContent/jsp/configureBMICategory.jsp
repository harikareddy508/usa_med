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
<title>USAMD |Configure BMI</title>
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
						var val = $('#idBody select').val();						
						if(val==""){
							$('#idCat select').attr('disabled','disabled');
						}
						$('#idBody select')
								.change(
										function() {
										var val = $('#idBody select').val();
										if(val==""){
											$('#idCat select').attr('disabled','disabled');
											event.preventDefault();
										}
										else{
											$('#addBmiForm').attr('action','fetchBMICategory');
											$('#addBmiForm').submit();
											$('#idCat select').removeAttr('disabled');
										}
										
										
										}); 
						$('#idCat select')
								.change(
										function() {
										var val = $('#idparts select').val();
										$('#addBmiForm').attr('action','fetchBMICategory');
										$('#addBmiForm').submit();
										
										}); 

					});
					
					/* function selectSmp(smpId, description, bodyPart){
						$('#bodyPart').val(bodyPart);
						$('#description').val(description);
						$('#smpId').val(smpId);						
					}
					
					function deleteSmp(smpId){
						$('#delSmpId').val(smpId);
						$('#deleteSmpForm').submit();
					} */
					
					
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
				<div class="col-md-8" style="margin-top: 20px;">
					<!-- Horizontal Form -->
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">Add/Update BMI Categories</h3>
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
						<form:form action="configureBMI" method="post"
							class="form-horizontal" modelAttribute="bmiCategoryBean"
							id="addBmiForm">
							<div class="box-body">
								

								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Body
										Type<span style="color: red">*</span>
									</label>
									<div class="col-sm-6" id="idBody">
									<form:input type="hidden" path="catId"/>
										<usamdTag:RTSelect elementName="bodyType"
											referenceTableName="RT_BODY_TYPE"
											placeHolder="Select Body Type"
											code="${bmiCategoryBean.bodyType}">
										</usamdTag:RTSelect>
										<span style="color: red"><form:errors path="bodyType"
												cssClass="error" /></span> <span
											class="glyphicon form-control-feedback"></span>
									</div>
								</div>

								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">BMI Category<span style="color: red">*</span></label>
									<div class="col-sm-6" id="idCat">
										<usamdTag:RTSelect elementName="category"
											referenceTableName="RT_BMI_CATEGORY"
											placeHolder="Select BMI Category"
											code="${bmiCategoryBean.category}">
										</usamdTag:RTSelect>
										<span style="color: red"><form:errors
												path="category" cssClass="error" /></span>
									</div>
								</div>
								
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Percentile Range(Low-High)
									<span style="color: red">*</span></label>
									<div class="col-sm-3">
										<form:input type="text" class="form-control"
											placeholder="Low" path="percentileRangeLow"
											maxlength="14" />
										<span style="color: red"><form:errors
												path="percentileRangeLow" cssClass="error" /></span>
										
									</div>
									<div class="col-sm-3">
										<form:input type="text" class="form-control"
											placeholder="High" path="percentileRangeHigh"
											maxlength="14" />
										<span style="color: red"><form:errors
												path="percentileRangeHigh" cssClass="error" /></span>
										
									</div>
								</div>
								<div class="form-group has-feedback">
									<label for="inputEmail3" class="col-sm-4 control-label">Suggested Health Plan</label>
									<div class="col-sm-8">
										<form:textarea  class="form-control"
											placeholder="Health Plan" path="healthPlan"
											maxlength="1000" rows="10" cols="50"/>
										<span style="color: red"><form:errors
												path="healthPlan" cssClass="error" /></span>
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
													
				<c:if test="${CATEGORY_LIST!=null && CATEGORY_LIST.size()>0 }">
					<div class="box box-info" style="padding: 20px;">
						<div class="box-header with-border">
							<h3 class="box-title">BMI Categories</h3>
						</div>
						<div class="box-body">
							<table class="table table-bordered table-stripped bg-olive">
								<thead>
									<tr>
										<th>Body Type</th>
										<th>Category</th>
										<th>Range(Low-High)</th>
									</tr>
								</thead>
								<tbody id="bodySmps" class="bg-gray">
									<c:forEach items="${CATEGORY_LIST}" var="cat">
										<tr><td><usamdTag:RTLabel referenceTableName="RT_BODY_TYPE" code="${cat.bodyType}"></usamdTag:RTLabel></td>
										<td><usamdTag:RTLabel referenceTableName="RT_BMI_CATEGORY" code="${cat.category}"></usamdTag:RTLabel></td>
										<td>${cat.percentileRangeLow} - ${cat.percentileRangeHigh} </td>
										</tr>
									</c:forEach>								
								
								</tbody>
							</table>
						</div>
					</div></c:if>
				</div>
				<!-- /.form-box -->
			</div>
			
			
			<!-- /.register-box --> </section>
			<!-- /.content -->
		</div>
		<%-- <form:form action="deleteSymptom" method="post"
							class="form-horizontal" id="deleteSmpForm" modelAttribute="bodyPartSymptomBean">
			<form:input path="" name="delSmpId"  id="delSmpId" type="hidden"/>					
		</form:form> --%>
		<!-- /.content-wrapper -->

		<!-- Footer -->
		<%@include file="fragments/footer.jspf"%>
		<!-- /.footer -->

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.1.4 -->

</body>

</html>