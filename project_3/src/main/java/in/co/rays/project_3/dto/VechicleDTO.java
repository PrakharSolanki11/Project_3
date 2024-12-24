package in.co.rays.project_3.dto;

import java.util.Date;

public class VechicleDTO extends BaseDTO {
	
	
	 private String number ;
	 private Date purchaseDate;
	 private Integer insuranceAmount;
	 private  String colour ;
	 
	 
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Integer getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(Integer insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 
	 

}
