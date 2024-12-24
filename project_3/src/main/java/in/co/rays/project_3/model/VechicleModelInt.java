package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.VechicleDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface VechicleModelInt {
 
	 
	public long add(VechicleDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(VechicleDTO dto)throws ApplicationException;
	public void update(VechicleDTO dto)throws ApplicationException,DuplicateRecordException;
	public VechicleDTO findByPK(long pk)throws ApplicationException;
	public VechicleDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(VechicleDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(VechicleDTO dto)throws ApplicationException;
	public List getRoles(VechicleDTO dto)throws ApplicationException;

	  
}

