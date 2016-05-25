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
		$('input').iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%' // optional
		});
		$('#msgLblRed').hide();
		if ('${FAILURE_MESSAGE}' != null && '${FAILURE_MESSAGE}' != "") {
			$('#msgLblRed').show();
		}
	});
	//alert("value " + '${homeScreenBean.leftImpSection.image}');
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
			<section class="content-header">
			
			</section>
			<!-- Main content -->
			<section class="content">
			<!-- PROMINENT SECTION -- SECTION 1 -->
			
			<div class="row">
				<div class="col-md-6">
					<%-- <div class="box box-default">
						<div class="box-header">
							<img class="img-responsive" alt=""
								src="<c:url value="/resources/images/zika_mosquito.jpg"/>" />
						</div>
						<div class="box-body">
							<h3>Meet the mosquito that is spreading Zika Virus</h3>
							<h5>
								<u><a href="#">CDC Guidelines on Pregnancy and Travel to
										High-Risk Areas</a></u>
							</h5>
						</div>


					</div> --%>
					<div class="box box-default">
						<div class="box-header">
								<img class="img-responsive" src="data:image/jpeg;base64,${homeScreenBean.leftImpSection.encodedImage}" >
						</div>
						<div class="box-body">
							<h3>${homeScreenBean.leftImpSection.caption}</h3>
							<h5>
								<u><a href="${homeScreenBean.leftImpSection.actualUrl}">${homeScreenBean.leftImpSection.urlDesc}</a></u>
							</h5>
						</div>


					</div>
				</div>
				<!-- ../ PROMINENT SECTION -- SECTION 1 -->
				
				<!-- PROMINENT SECTION -- SECTION 2 -->
				<div class="col-md-6">
					<div class="box box-default">
						<div class="box-header">
							<img class="img-responsive" src="data:image/jpeg;base64,${homeScreenBean.rightImpSection.encodedImage}" >
						</div>
						<div class="box-body">
							<h3>${homeScreenBean.rightImpSection.caption}</h3>
							<h5>
								<u><a href="${homeScreenBean.rightImpSection.actualUrl}">${homeScreenBean.rightImpSection.urlDesc}</a></u>
							</h5>
						</div>


					</div>
				</div><!--../ PROMINENT SECTION -- SECTION 2 -->

				<!-- /.col -->

			</div>
			
			<!-- Small boxes for articles-->
			<!-- row 2 -->
			<div class="row">
			<!--  Article 1 -->
				<div class="col-md-2">
					<div class="box box-default">
						<div class="box-header">
							<img class="img-responsive" src="data:image/jpeg;base64,${homeScreenBean.articleSection1.encodedImage}" >
						</div>
						<div class="box-body">
							<h3>${homeScreenBean.articleSection1.caption}</h3>
							<h5>
								<u><a href="${homeScreenBean.articleSection1.actualUrl}">${homeScreenBean.articleSection1.urlDesc}</a></u>
							</h5>
						</div>


					</div>
				</div><!-- ../  Article 1 -->
				
				<!--  Article 2 -->
				<div class="col-md-2">
					<div class="box box-default">
						<div class="box-header">
							<img class="img-responsive" src="data:image/jpeg;base64,${homeScreenBean.articleSection2.encodedImage}" >
						</div>
						<div class="box-body">
							<h3>${homeScreenBean.articleSection2.caption}</h3>
							<h5>
								<u><a href="${homeScreenBean.articleSection2.actualUrl}">${homeScreenBean.articleSection2.urlDesc}</a></u>
							</h5>
						</div>


					</div>
				</div><!-- ../ Article 2 -->
				
				<!--  Article 3 -->
				<div class="col-md-2">
					<div class="box box-default">
						<div class="box-header">
							<img class="img-responsive" src="data:image/jpeg;base64,${homeScreenBean.articleSection3.encodedImage}" >
						</div>
						<div class="box-body">
							<h3>${homeScreenBean.articleSection3.caption}</h3>
							<h5>
								<u><a href="${homeScreenBean.articleSection3.actualUrl}">${homeScreenBean.articleSection3.urlDesc}</a></u>
							</h5>
						</div>


					</div>
				</div><!--  ../ Article 3 -->
				
				<!--  Embedded Video section -->
				<div class="col-md-6">
					<div class="box box-default">
						<div class="box-header">
							<div class="embed-responsive embed-responsive-16by9">
							<!-- 	<iframe class="embed-responsive-item"
									src="https://www.youtube.com/embed/FJrX5Z9BAhw" frameborder="0"
									allowfullscreen></iframe> -->
									<iframe class="embed-responsive-item"
									src="${homeScreenBean.videoSection.actualUrl}" frameborder="0"
									allowfullscreen></iframe>
							</div>
						</div> 
						<div class="box-body">
							
						</div>


					</div>
				</div><!-- ../ Embedded Video section -->


			</div>

			<!-- /.row --> </section>
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