package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.TransportationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.RoleModelInt;
import in.co.rays.project_3.model.TransportationModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * transportation functionality controller.to perform add,delete and update operation
 * 
 * @author Prakhar Solanki
 *
 */
@WebServlet(urlPatterns = { "/ctl/TransportationCtl" })
public class TransportationCtl extends BaseCtl {

	/** The Constant serialVersionUID.*/
	private static final long serialVersionUID = 1L;
	/** The log. */
	private static Logger log = Logger.getLogger(TransportationCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();

		map.put(1, "Air");
		map.put(2, "Rail");
		map.put(3, "Road");

		request.setAttribute("Mode", map);

		/*
		 * ap<Integer, String> map1 = new HashMap(); map1.put(1, "Cash"); map1.put(2,
		 * "online"); map1.put(3, "netbanking"); map1.put(4, "cheque");
		 * 
		 * request.setAttribute("transaction_typee", map1);
		 */

	}


	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", "Description  must contains alphabets only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("cost"))) {
			request.setAttribute("cost", PropertyReader.getValue("error.require", "Cost"));
			System.out.println(pass);
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("cost"))) {
			request.setAttribute("cost", "Location must contains numbers only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("date"))) {
			request.setAttribute("date", "Please Enter Valid Date");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("mode"))) {
			request.setAttribute("mode", PropertyReader.getValue("error.require", "Mode"));
			pass = false;
		}

	
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		TransportationDTO dto = new TransportationDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setDescription(DataUtility.getString(request.getParameter("description")));

		dto.setDate(DataUtility.getDate(request.getParameter("date")));
		
		dto.setMode(DataUtility.getInt(request.getParameter("mode")));

		dto.setCost(DataUtility.getInt(request.getParameter("cost")));
		
		log.debug("TransportationRegistrationCtl Method populatedto Ended");

		return dto;

	}

	/**
	 * Contain Display Logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("TransportationCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		TransportationModelInt model = ModelFactory.getInstance().getTransportationModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			TransportationDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Contain Submit Logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out
				.println("-------------------------------------------------------------------------dopost run-------");
		// get model
		TransportationModelInt model = ModelFactory.getInstance().getTransportationModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			TransportationDTO dto = (TransportationDTO) populateDTO(request);
			System.out.println(" in do post method jkjjkjk++++++++" + dto.getId());
			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {

					try {
						long pk = model.add(dto); 
						
						dto = model.findByPK(pk);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TransportationDTO dto = (TransportationDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.TRANSPORTATION_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRANSPORTATION_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRANSPORTATION_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("TransportationCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TRANSPORTATION_VIEW;
	}

}
