package in.co.rays.project_3.test;

import in.co.rays.project_3.dto.ColourDTO;
import in.co.rays.project_3.dto.PriorityDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ColourModelHibImp;
import in.co.rays.project_3.model.ColourModelInt;
import in.co.rays.project_3.model.PriorityModelHibImp;
import in.co.rays.project_3.model.PriorityModelInt;

public class ColoutTest {

	  
	 
	 
	public static ColourModelInt model = new ColourModelHibImp();
	
	
public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
	  
	  
	add();
	
}
		
	


	private static void add() throws ApplicationException, DuplicateRecordException {
		ColourDTO dto = new ColourDTO();
		 
		 dto.setColour("Red");
		 model.add(dto);
		 System.out.println("Data inserted");
	}

		
	}

