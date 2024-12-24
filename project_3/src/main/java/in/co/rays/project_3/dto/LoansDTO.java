package in.co.rays.project_3.dto;

import java.util.Date;

public class LoansDTO extends BaseDTO  {
	
	 private Date loanStartDate ;
	 
	 private String customerId ;
	 
	 private Integer loanAmount;
	 
	 private  Integer intrestRate ;

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(Integer intrestRate) {
		this.intrestRate = intrestRate;
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
