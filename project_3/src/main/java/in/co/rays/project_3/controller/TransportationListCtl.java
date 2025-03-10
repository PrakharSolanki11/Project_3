package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.RoleModelInt;
import in.co.rays.project_3.model.TransportationModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * Transportation List functionality controller.to perform Search and List operation.
 * 
 * @author Prakhar Solanki
 *
 */
@WebServlet(name = "TransportationListCtl", urlPatterns = { "/ctl/TransportationListCtl" })
public class TransportationListCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	 /** The log. */
	private static Logger log = Logger.getLogger(TransportationListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		
		
		TransportationModelInt model = ModelFactory.getInstance().getTransportationModel();

		try {

			List clist = model.list(0, 0);

			request.setAttribute("clientName", clist);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}


		HashMap<Integer, String> map = new HashMap();

		map.put(1, "Air");
		map.put(2, "Rail");
		map.put(3, "Road");

		request.setAttribute("mode", map);

	}
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		TransportationDTO dto = new TransportationDTO();


		dto.setDescription(DataUtility.getString(request.getParameter("description")));

		dto.setDate(DataUtility.getDate(request.getParameter("date")));
		
		dto.setMode(DataUtility.getInt(request.getParameter("mode")));

		dto.setCost(DataUtility.getInt(request.getParameter("cost")));
		
		System.out.println(dto);
				
		return dto;
		
	}

	 /**
		 * Contains Display logics.
		 *
		 * @param request the request
		 * @param response the response
		 * @throws ServletException the servlet exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TransportationListCtl doGet Start");
		List list;
		List next;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		TransportationDTO dto = (TransportationDTO) populateDTO(request);

		// get the selected checkbox ids array for delete list
		TransportationModelInt model = ModelFactory.getInstance().getTransportationModel();
		try {
			list = model.search(dto, pageNo, pageSize);

			ArrayList<TransportationDTO> a = (ArrayList<TransportationDTO>) list;

			next = model.search(dto, pageNo + 1, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", 0);

			} else {
				request.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("TransportationListCtl doPOst End");
	}

	  /**
		 * Contains Submit logics.
		 *
		 * @param request the request
		 * @param response the response
		 * @throws ServletException the servlet exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TransportationListCtl doPost Start");
		List list = null;
		List next = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		TransportationDTO dto = (TransportationDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op---->" + op);

// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		TransportationModelInt model = ModelFactory.getInstance().getTransportationModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TRANSPORTATION_CTL, request, response);
				return;
			} else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.TRANSPORTATION_LIST_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					TransportationDTO deletedto = new TransportationDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}
			if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TRANSPORTATION_LIST_CTL, request, response);
				return;
			}
			dto = (TransportationDTO) populateDTO(request); 

			list = model.search(dto, pageNo, pageSize);

			ServletUtility.setDto(dto, request);
			next = model.search(dto, pageNo + 1, pageSize);

			ServletUtility.setList(list, request);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				if (!OP_DELETE.equalsIgnoreCase(op)) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}
			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", 0);

			} else {
				request.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("TransportationListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.TRANSPORTATION_LIST_VIEW;
	}

}