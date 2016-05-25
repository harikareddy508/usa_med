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

		$('#msgLblGreen').hide();
		if ('${SUCCESS_MESSAGE}' != null && '${SUCCESS_MESSAGE}' != "") {
			$('#msgLblGreen').show();
		}
		
		$('#sectionId').change(function(){
				$('#sectionForm').attr('action','sectionInfo');
				$('#sectionForm').submit();
			
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

			<!-- Main content -->
			<section class="content">
			<div class="alert alert-danger alert-dismissable" id="msgLblRed">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<h4>
					<i class="icon fa fa-ban"></i> Alert!
				</h4>
				${FAILURE_MESSAGE}
			</div>
			<div class="alert alert-success alert-dismissable" id="msgLblGreen">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<h4>
					<i class="icon fa fa-check"></i> Success!
				</h4>
				${SUCCESS_MESSAGE}
			</div>
			<form:form action="section" modelAttribute="sectionUIBean" id="sectionForm"
				class="form-horizontal" method="post" enctype="multipart/form-data">
				<div class="row">
					<!-- left column -->
					<div class="col-md-6">
						<!-- general form elements -->
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title"></h3>
							</div>
							<div class="box-body">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Select
										Section: <span style="color: red">*</span>
									</label>
									<div class="col-sm-8">
										<form:select path="sectionId" class="form-control">
											<form:option value="">Please Select</form:option>
											<form:option value="LEFTIMP">Left Important Section</form:option>
											<form:option value="RIGHTIMP">Right Important Section</form:option>
											<form:option value="ARTICLE1">Health Article 1</form:option>
											<form:option value="ARTICLE2">Health Article 2</form:option>
											<form:option value="ARTICLE3">Health Article 3</form:option>
											<form:option value="VIDEO">Health Video</form:option>
										</form:select>
										<span style="color: red"><form:errors path=""
												cssClass="error" /></span>
									</div>
								</div>


								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Upload
										Image: <span style="color: red">*</span>
									</label>
									<div class="col-sm-8">
										<form:input type="file" class="form-control"
											placeholder="Image path for Section" path=""
											name="uploadImage" id="uploadImage" />
										<span style="color: red"><form:errors path=""
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">Image
										Caption: <span style="color: red">*</span>
									</label>
									<div class="col-sm-8">
										<form:input type="text" class="form-control"
											placeholder="Image Caption" path="caption" />
										<span style="color: red"><form:errors path="caption"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">URL
										of associated article: <span style="color: red">*</span>
									</label>
									<div class="col-sm-8">
										<form:input type="text" class="form-control"
											placeholder="URL of associated article" path="actualUrl" />
										<span style="color: red"><form:errors path="actualUrl"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-4 control-label">URL
										Description: <span style="color: red">*</span>
									</label>
									<div class="col-sm-8">
										<form:input type="text" class="form-control"
											placeholder="URL Description" path="urlDesc" />
										<span style="color: red"><form:errors path="urlDesc"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" id="btnSave" class="btn btn-lg bg-red">Update
											Section Details</button>
									</div>
									<!-- /.col -->

									<!-- /.col -->
								</div>


							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="box box-widget">
							<div class="box-header with-border">
								<h3 class="box-title">Uploaded Image !!</h3>
							</div>
							<div class="box-body">
								<div class="form-group">
									<img class="img-responsive pad"
										src="data:image/jpeg;base64,${sectionUIBean.encodedImage}">
								</div>
							</div>
						</div>
					</div>
				</div>






			</form:form> </section>
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