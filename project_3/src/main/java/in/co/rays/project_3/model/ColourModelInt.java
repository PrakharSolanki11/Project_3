package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ColourDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface ColourModelInt {
 
	 
	 
	public long add(ColourDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(ColourDTO dto)throws ApplicationException;
	public void update(ColourDTO dto)throws ApplicationException,DuplicateRecordException;
	public ColourDTO findByPK(long pk)throws ApplicationException;
	public ColourDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(ColourDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(ColourDTO dto)throws ApplicationException;
	public List getRoles(ColourDTO dto)throws ApplicationException;
}
