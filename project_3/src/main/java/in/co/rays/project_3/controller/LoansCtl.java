package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.LoansDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.DesceaseModeInt;
import in.co.rays.project_3.model.LoansModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.ProductModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;


@WebServlet(name = "LoansCtl", urlPatterns = { "/ctl/LoansCtl" })

public class LoansCtl  extends BaseCtl{
	
	@Override
	protected void preload(HttpServletRequest request) {

		ProductModelInt model = ModelFactory.getInstance().getProductModel();
		
		LoansModelInt model1 = ModelFactory.getInstance().getLoansModel();
		try {
			List list = model1.list();
			request.setAttribute("mt", list);
			
			
			HashMap map = new HashMap();
			map.put("SBI101", "SBI101");
			map.put("SBI102", "SBI102");
			map.put("SBI103" ,"SBI103");
			map.put("SBI104" ,"SBI104");
			map.put("SBI105" ,"SBI105");
			map.put("SBI105" ,"SBI105");

			
			
 request.setAttribute("sm", map);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("customerId"))) {
			request.setAttribute("customerId", PropertyReader.getValue("error.require", " customerId"));

			pass = false;
		} 
		
		
		if (DataValidator.isNull(request.getParameter("loanStartDate"))) {
			request.setAttribute("loanStartDate", PropertyReader.getValue("error.require", " loanStartDate"));

			pass = false;
		} /*else if (!DataValidator.isDate(request.getParameter("lastUpdatedDate"))) {
			request.setAttribute("lastUpdatedDate", "lastUpdateDate must contains Digit only");
			System.out.println(pass);
			pass = false;*/

		

		if (DataValidator.isNull(request.getParameter("loanAmount"))) {
			request.setAttribute("loanAmount", PropertyReader.getValue("error.require", " loanAmount"));

			pass = false;
		}
		
		/*
		 * else if (!DataValidator.isPhoneNo(request.getParameter("quantity"))) {
		 * request.setAttribute("quantity", "quantity must contains Digit only"); pass =
		 * false; }
		 */		if (DataValidator.isNull(request.getParameter("intrestRate"))) {
			request.setAttribute("intrestRate", PropertyReader.getValue("error.require", " intrestRate"));

			pass = false;
		}else if (!DataValidator.isPersantage(request.getParameter("intrestRate"))) {
			request.setAttribute("intrestRate", " intrestRate must be between 0 an 100 ");
			System.out.println(pass);
			pass = false;
		}

		return pass;

	}
	
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		LoansDTO dto = new LoansDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setCustomerId(DataUtility.getString(request.getParameter("customerId")));
		dto.setLoanStartDate(DataUtility.getDate(request.getParameter("loanStartDate")));

		dto.setLoanAmount(DataUtility.getInt(request.getParameter("loanAmount")));

		dto.setIntrestRate(DataUtility.getInt(request.getParameter("intrestRate")));

		populateBean(dto, request);

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		LoansModelInt model = ModelFactory.getInstance().getLoansModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			LoansDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		LoansModelInt model = ModelFactory.getInstance().getLoansModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			LoansDTO dto = (LoansDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			LoansDTO dto = (LoansDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.LOANS_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.LOANS_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.LOANS_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.LOANS_VIEW;
	}


}
