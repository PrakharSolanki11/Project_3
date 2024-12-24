<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.controller.ProductDetailsCtl"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=ORSView.APP_CONTEXT%>/js/utility.js"></script>

<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	/* box-shadow: 9px 8px 7px #001a33; */
	background-image: linear-gradient(to bottom right, orange, black);
	background-repeat: no repeat;
	background-size: 100%;
	padding-bottom: 11px;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/unsplash.jpg');
	background-size: cover;
	padding-top: 6%;
}
</style>

</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>

		<main>
		<form action="<%=ORSView.PRODUCT_DETAILS_CTL%>" method="post">
			<jsp:useBean id="dto"
				class="in.co.rays.project_3.dto.ProductDetailsDTO" scope="request"></jsp:useBean>
			<div class="row pt-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card input-group-addon">
						<div class="card-body">

							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (dto.getId() != null && id > 0) {
							%>
							<h3 class="text-center default-text text-primary">Update
								ProductDetails</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text text-primary">Add
								ProductDetails</h3>
							<%
								}
							%>
							<!--Body-->
							<div>
								<%
									List list = (List) request.getAttribute("mt");
									HashMap map = (HashMap) request.getAttribute("sm");
								%>

								<H4 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H4>

								<H4 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H4>

								<input type="hidden" name="id" value="<%=dto.getId()%>">

							</div>


							<div class="md-form">
								<span class="pl-sm-5"><b>name</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="name"
											placeholder="Enter name" id="name" placeholder="Enter name"
											oninput="handleLetterInput(this, 'nameError', 100)"
											onblur="validateLetterInput(this, 'nameError', 100)"><%=DataUtility.getStringData(dto.getName())%>
									</div>
								</div>

								<font color="red" class="pl-sm-5" id="nameError"> <%=ServletUtility.getErrorMessage("name", request)%></font></br>


							<div class="md-form">
								<span class="pl-sm-5"><b>Description</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
											<textarea type="text" class="form-control" name="description"
											placeholder="Enter description" id="description"
											
											placeholder="Enter Description"
											oninput="handleLetterInput(this, 'descriptionError', 200)"
											onblur="validateLetterInput(this, 'descriptionError', 200)"><%=DataUtility.getStringData(dto.getDescription())%></textarea>
									</div>
								</div>

								<font color="red" class="pl-sm-5" id = "descriptionError"> <%=ServletUtility.getErrorMessage("description", request)%></font></br>


									<span class="pl-sm-5"><b>price</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-venus-mars grey-text"
													style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" 
											 name="price"  
											placeholder="Enter price"
											oninput="handleIntegerInput(this, 'priceError', 10)"
											onblur="validateIntegerInput(this, 'priceError', 10)"
											value="<%=DataUtility.getStringData(dto.getPrice()).equals("0")? "" : DataUtility.getStringData(dto.getPrice())%>">

									</div>
								</div>
								<font color="red" class="pl-sm-5" id = "priceError"> <%=ServletUtility.getErrorMessage("price", request)%></font></br>





									<%-- 
								<%
								if (dto.getId()==null||id<=0) {
								%> --%>



									<span class="pl-sm-5"><b>DateOfPurchase</b> <span
										style="color: red;">*</span></span></br>
									<div class="col-sm-12">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar grey-text"
														style="font-size: 1rem;"></i>
												</div>
											</div>
											<input type="text" id="datepicker2" name="dateOfPurchase"
												class="form-control" placeholder="dateOfPurchase"
												readonly="readonly"
												value="<%=DataUtility.getDateString(dto.getDateOfPurchase())%>">
										</div>
									</div>
									<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("dateOfPurchase", request)%></font></br>



									<span class="pl-sm-5"><b>Category</b><span
										style="color: red;">*</span></span></br>
									<div class="col-sm-12">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-user grey-text" style="font-size: 1rem;"></i>
												</div>
											</div>
											<%=HTMLUtility.getList("category", String.valueOf(dto.getCategory()), map)%>
										</div>
									</div>


									<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("category", request)%></font></br>

									<%
										if (dto.getId() != null && id > 0) {
									%>

									<div class="text-center">

										<input type="submit" name="operation"
											class="btn btn-success btn-md" style="font-size: 17px"
											value="<%=ProductDetailsCtl.OP_UPDATE%>"> <input
											type="submit" name="operation" class="btn btn-warning btn-md"
											style="font-size: 17px"
											value="<%=ProductDetailsCtl.OP_CANCEL%>">

									</div>
									<%
										} else {
									%>
									<div class="text-center">

										<input type="submit" name="operation"
											class="btn btn-success btn-md" style="font-size: 17px"
											value="<%=ProductDetailsCtl.OP_SAVE%>"> <input
											type="submit" name="operation" class="btn btn-warning btn-md"
											style="font-size: 17px"
											value="<%=ProductDetailsCtl.OP_RESET%>">
									</div>

								</div>
								<%
									}
								%>
							</div>
						</div>
		</form>
		</main>
		<div class="col-md-4 mb-4"></div>

	</div>

</body>
<%@include file="FooterView.jsp"%>

</html>