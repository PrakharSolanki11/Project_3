package in.co.rays.project_3.dto;

import java.util.Date;

public class TransportationDTO extends BaseDTO {

	private String description;
	private int mode;
	private Date date;
	private int cost;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
