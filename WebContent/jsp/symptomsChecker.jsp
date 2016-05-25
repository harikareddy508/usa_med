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
<title>USAMD |Home Page</title>
<%@include file="commonInclude.jsp"%>

<script>
	$(function() {
		$('#msgLblRed').hide();
		if ('${FAILURE_MESSAGE}' != null && '${FAILURE_MESSAGE}' != "") {
			$('#msgLblRed').show();
		}

		$('#idReset').click(function() {
			$.ajax({
				url : 'resetSelectedSmps',
				method : 'POST',
				dataType : 'json',
				success : function(data) {
					var $el = $("#selectedSmps");
					var $el1 = $("#predictedMedCond");
					$el.empty(); // remove old options
					$el1.empty();

				},
				complete : function(data) {

				}

			});
		});

		$('#idPredict').click(
				function() {
					$.ajax({
						url : 'predictMedCondition',
						method : 'POST',
						dataType : 'json',
						success : function(data) {
							var $el = $("#predictedMedCond");
							$el.empty(); // remove old options		
							$.each(data, function(i, obj) {
								$el
										.append("<tr><td>" + obj.name
												+ "</td><td>" + obj.description
												+ "</span></td></tr>");
							});

						}
					});
				});

	});

	function selectSmp(smpId) {
		$
				.ajax({
					url : 'maintainSelSmpsMap',
					method : 'POST',
					data : {
						smpId : smpId
					},
					dataType : 'json',
					success : function(data) {
						//alert("success Response"	+ data);
						var $el = $("#selectedSmps");
						$el.empty(); // remove old options		
						$
								.each(
										data,
										function(i, obj) {
											$el
													.append("<tr><td>"
															+ obj.bodyPart
															+ "<input type='hidden' value='"
															+ obj.smpId
															+ "'/></td><td>"
															+ obj.description
															+ "</span></td></tr>");
										});

					}
				});
	}

	function mapAction(element) {
		var val = element.id;
		$
				.ajax({
					url : 'fetchBodySymptoms',
					method : 'POST',
					data : {
						bodyPart : val
					},
					dataType : 'json',
					success : function(data) {
						var $el = $("#bodySmps");
						$el.empty(); // remove old options		
						$
								.each(
										data,
										function(i, obj) {
											$el
													.append("<tr><td>"
															+ obj.bodyPart
															+ "<input type='hidden' value='"
															+ obj.smpId
															+ "'/></td><td>"
															+ obj.description
															+ "</td><td><button title='Select Symptom' class='fa fa-plus-circle' value='Add' onclick=\"selectSmp('"
															+ obj.smpId
															+ "'); \" ></button></td></tr>");
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
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header"> </section>

			<!-- Main content -->
			<section class="content"> <!-- /.row -->
			<div class="row">
				<div class="col-sm-4">
					<img id="Image-Maps-Com-image-maps-2016-04-10-040243"
						src="<c:url value="/resources/images/male_human_body.jpg"/>"
						border="1" width="366" height="622" orgWidth="366" orgHeight="622"
						usemap="#image-maps-2016-04-10-040243" alt="" />
					<map name="image-maps-2016-04-10-040243"
						id="ImageMapsCom-image-maps-2016-04-10-040243">
						<area id="Scalp" alt="" title="Scalp" href="#" shape="rect"
							coords="151,11,201,36" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="EY" alt="" title="Eyes" href="#" shape="rect"
							coords="154,36,197,54" style="outline: none;" target="_self"
							onclick="mapAction(this);" />


						<area id="NOSE" alt="" title="Nose" href="#" shape="rect"
							coords="166,43,185,67" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="EAR" alt="" title="Ears" href="#" shape="rect"
							coords="140,35,155,74" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="EAR" alt="" title="Ears" href="#" shape="rect"
							coords="199,33,208,71" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="SHLDER" alt="" title="Shoulder" href="#" shape="rect"
							coords="84,103,123,157" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="SHLDER" alt="" title="Shoulders" href="#" shape="rect"
							coords="218,111,261,165" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="NECK" alt="" title="Neck" href="#" shape="rect"
							coords="140,90,208,119" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="ARMS" alt="" title="Arms" href="#" shape="rect"
							coords="32,211,95,300" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="ARMS" alt="" title="Arms" href="#" shape="rect"
							coords="269,213,331,302" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="STMCH" alt="" title="Stomach" href="#" shape="rect"
							coords="131,229,219,276" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="CHEST" alt="" title="Chest" href="#" shape="rect"
							coords="128,138,243,185" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="THIGH" alt="" title="Thighs" href="#" shape="rect"
							coords="115,304,159,421" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="THIGH" alt="" title="Thighs" href="#" shape="rect"
							coords="192,319,239,435" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="FEET" alt="" title="Feet" href="#" shape="rect"
							coords="92,585,147,615" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
						<area id="FEET" alt="" title="Feet" href="#" shape="rect"
							coords="203,587,264,616" style="outline: none;" target="_self"
							onclick="mapAction(this);" />
					</map>
				</div>
				<div class="col-sm-5">
					<div class="box box-warning">
						<div class="box-header">
							<h3 class="box-title">Please Select Symptoms</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table class="table table-bordered table-stripped bg-olive">
								<thead>
									<tr>
										<th>Body Part</th>
										<th>Symptom</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody id="bodySmps" class="bg-gray">

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="box box-danger">
						<div class="box-header">
							<h3 class="box-title">Selected Symptoms</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table class="table table-bordered table-stripped bg-red">
								<thead>
									<tr>
										<th>Body Part</th>
										<th>Symptom</th>
									</tr>
								</thead>
								<tbody id="selectedSmps" class="bg-gray">
								</tbody>
							</table>


						</div>
					</div>

				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<button class="btn btn-md btn-warning" id="idReset">Reset
							Symptoms</button>
					</div>
					<div class="form-group">
						<button class="btn btn-md btn-primary" id="idPredict">Predict
							Medical Condition</button>
					</div>
				</div>

				<div class="col-sm-5">
					<div class="box box-warning">
						<div class="box-header">
							<h3 class="box-title">Predicted Medical Conditions</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table class="table table-bordered table-stripped bg-teal">
								<thead>
									<tr>
										<th>Medical Condition</th>
										<th>Description</th>
									</tr>
								</thead>
								<tbody id="predictedMedCond" class="bg-gray">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


			</section>
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