<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.controller.PatientListCtl"%>
<%@page import="in.co.rays.project_3.dto.PatientDTO"%>
<%@page import="in.co.rays.project_3.controller.PatientListCtl"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_3.model.RoleModelInt"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/utility.js"></script>

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/rain.jpg');
	background-size: cover;
	background-repeat: no-repeate;
	padding-top: 6%;
}

.p1 {
	padding: 4px;
	width: 200px;
	font-size: bold;
}

.text {
	text-align: center;
}
</style>
</head>

<body class="hm">
	<%@include file="Header.jsp"%>
	<%@include file="calendar.jsp"%>
	<div></div>
	<div>
		<form class="pb-5" action="<%=ORSView.PATIENT_LIST_CTL%>"
			method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PatientDTO"
				scope="request"></jsp:useBean>
			<%
				List list1 = (List) request.getAttribute("mt");
			HashMap map = (HashMap) request.getAttribute("sm");

			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				RoleDTO rbean1 = new RoleDTO();
				RoleModelInt rmodel = ModelFactory.getInstance().getRoleModel();

				List list = ServletUtility.getList(request);

				Iterator<PatientDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 class="text-primary font-weight-bold pt-3">
					<u>Patient List</u>
				</h1>
			</center>
			<div class="row">
				<div class="col-md-4"></div>
				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>

			<div class="row">

						<div class="col-sm-2"></div>
				<div class="col-sm-2">

						<input type="text" name="name" placeholder="Enter name"
							class="form-control"
							oninput="handleLetterInput(this, 'nameError', 100)"
							onblur="validateLetterInput(this, 'nameError', 100)"
							value="<%=ServletUtility.getParameter("name", request)%>">

						<font color="red" class="pl-sm-5" id="nameError"></font>
					</div>
					<div class="col-sm-2">
						<input type="text" name="mobile" placeholder="Enter mobile"
							class="form-control"
							value="<%=ServletUtility.getParameter("mobile", request)%>">
					</div>
					&emsp;
					<div class="col-sm-2"><%=HTMLUtility.getList("decease", String.valueOf(dto.getDecease()), map)%></div>
					&emsp; &emsp;
					<div class="col-sm-2">
						<input type="text" name="dateOfVisit"
							placeholder="Enter dateOfVisit" class="form-control"
							value="<%=ServletUtility.getParameter("dateOfVisit", request)%>">
					</div>
					<%-- <div class="col-sm-2">
					<input type="text" name="deceade" placeholder="Enter deceade"
						class="form-control"
						value="<%=ServletUtility.getParameter("deceade", request)%>">
				</div>
 --%>
					<%-- 
					&emsp;
				<div class="col-sm-2"><%=HTMLUtility.getList("nname", String.valueOf(dto.getName()), list1)%></div>
				&emsp; --%>

					<%-- 		&emsp;
				<div class="col-sm-2"><%=HTMLUtility.getList("ddob", String.valueOf(dto.getDob()), list1)%></div>
				&emsp;
 --%>

					<div class="col-sm-2">
						<input type="submit" class="btn btn-primary btn-md"
							style="font-size: 15px" name="operation"
							value="<%=PatientListCtl.OP_SEARCH%>"> &emsp; <input
							type="submit" class="btn btn-dark btn-md" style="font-size: 15px"
							name="operation" value="<%=PatientListCtl.OP_RESET%>">
					</div>


					<div class="col-sm-2"></div>
				</div>

				</br>
				<div style="margin-bottom: 20px;" class="table-responsive">
					<table class="table table-bordered table-dark table-hover">
						<thead>
							<tr style="background-color: blue;">

								<th width="10%"><input type="checkbox" id="select_all"
									name="Select" class="text"> Select All</th>
								<th width="5%" class="text">S.NO</th>
								<th width="15%" class="text">Name</th>
								<th width="15%" class="text">lastUpatedDate</th>
								<th width="15%" class="text">Mobile</th>
								<th width="20%" class="text">decease</th>
								<th width="5%" class="text">Edit</th>
							</tr>
						</thead>
						<%
							while (it.hasNext()) {
									dto = it.next();

									/* 	RoleDTO rbean = rmodel.findByPK(dto.getRoleId()); */
						%>
						<tbody>
							<tr>
								<td align="center"><input type="checkbox" class="checkbox"
									name="ids" value="<%=dto.getId()%>"></td>
								<td class="text"><%=index++%></td>
								<td class="text"><%=dto.getName()%></td>
								<td class="text"><%=DataUtility.getDateString(dto.getDateOfVisit())%></td>
								<td class="text"><%=dto.getMobile()%></td>
								<td class="text"><%=dto.getDecease()%></td>

								<%-- <td class="text"><%=rbean.getName()%></td> --%>
								<td class="text"><a href="PatientCtl?id=<%=dto.getId()%>">Edit</a></td>
							</tr>
						</tbody>
						<%
							}
						%>
					</table>
				</div>
				<table width="100%">
					<tr>
						<td><input type="submit" name="operation"
							class="btn btn-warning btn-md" style="font-size: 17px"
							value="<%=PatientListCtl.OP_PREVIOUS%>"
							<%=pageNo > 1 ? "" : "disabled"%>></td>

						<td><input type="submit" name="operation"
							class="btn btn-primary btn-md" style="font-size: 17px"
							value="<%=PatientListCtl.OP_NEW%>"></td>

						<td><input type="submit" name="operation"
							class="btn btn-danger btn-md" style="font-size: 17px"
							value="<%=PatientListCtl.OP_DELETE%>"></td>

						<td align="right"><input type="submit" name="operation"
							class="btn btn-warning btn-md" style="font-size: 17px"
							style="padding: 5px;" value="<%=PatientListCtl.OP_NEXT%>"
							<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
					</tr>
					<tr></tr>
				</table>

				<%
					}
					if (list.size() == 0) {
				%>
				<center>
					<h1 style="font-size: 40px; color: #162390;">Patient List</h1>
				</center>
				</br>
				<div class="row">
					<div class="col-md-4"></div>

					<%
						if (!ServletUtility.getErrorMessage(request).equals("")) {
					%>
					<div class=" col-md-4 alert alert-danger alert-dismissible">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>
							<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
						</h4>
					</div>
					<%
						}
					%>




					<%
						if (!ServletUtility.getSuccessMessage(request).equals("")) {
					%>

					<div class="col-md-4 alert alert-success alert-dismissible"
						style="background-color: #80ff80">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>
							<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
						</h4>
					</div>
					<%
						}
					%>
					<div style="padding-left: 48%;">
						<input type="submit" name="operation"
							class="btn btn-primary btn-md" style="font-size: 17px"
							value="<%=PatientListCtl.OP_BACK%>">
					</div>

					<div class="col-md-4"></div>
				</div>

				<%
					}
				%>

				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>


	</div>


</body>
<%@include file="FooterView.jsp"%></html>