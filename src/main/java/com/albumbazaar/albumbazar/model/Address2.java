package com.albumbazaar.albumbazar.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.form.LocationForm;

@Entity
@Table(
        name = "address2"
)
public class Address2 {

	@Id
	@Column(name = "id")
    private Long pin;
    @Size(max = 100)
    private String town;
    @Size(max = 100)
    private String city;
    @Size(max = 100)
    private String district;
    @Size(max = 100)
	private String state;
	@Size(max = 100)
	private String policeStation;
	@Size(max = 100)
	private String postOffice;
	
	public Address2() {}

	public Address2(final LocationForm locationDetails) {
		this.pin = Long.parseLong(locationDetails.getPin());
		this.town = locationDetails.getTown();
		this.city = locationDetails.getCity();
		this.district = locationDetails.getDistrict();
		this.state = locationDetails.getState();
		this.postOffice = locationDetails.getPostOffice();
		this.policeStation = "Barakar";
	}
    

	@Override
	public String toString() {
		return "Address2 [city=" + city + ", district=" + district + ", pin=" + pin + ", policeStation=" + policeStation
				+ ", postOffice=" + postOffice + ", state=" + state + ", town=" + town + "]";
	}



	public Long getPin() {
		return pin;
	}



	public void setPin(Long pin) {
		this.pin = pin;
	}



	public String getTown() {
		return town;
	}



	public void setTown(String town) {
		this.town = town;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getPoliceStation() {
		return policeStation;
	}



	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}



	public String getPostOffice() {
		return postOffice;
	}



	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

    

    
}
