package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.LoansDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface LoansModelInt {

	
	 
		public long add(LoansDTO dto)throws ApplicationException,DuplicateRecordException;
		public void delete(LoansDTO dto)throws ApplicationException;
		public void update(LoansDTO dto)throws ApplicationException,DuplicateRecordException;
		public LoansDTO findByPK(long pk)throws ApplicationException;
		public LoansDTO findByLogin(String login)throws ApplicationException;
		public List list()throws ApplicationException;
		public List list(int pageNo,int pageSize)throws ApplicationException;
		public List search(LoansDTO dto,int pageNo,int pageSize)throws ApplicationException;
		public List search(LoansDTO dto)throws ApplicationException;
		public List getRoles(LoansDTO dto)throws ApplicationException;


}

