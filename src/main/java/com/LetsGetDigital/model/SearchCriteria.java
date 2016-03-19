package com.LetsGetDigital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "SearchCriteria")
@NamedQueries({
    @NamedQuery(
            name = "com.LetsGetDigital.model.SearchCriteria.findAll",
            query = "FROM SearchCriteria s"
    )
})
public class SearchCriteria {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
 
	@Column(name = "fromDate", unique = false, nullable = false, length = 50)
    private Date fromDate;
    
    @Column(name = "toDate", unique = false, nullable = false, length = 50)
    private Date toDate;
    
    @Column(name = "pickupLocation", unique = false, nullable = false, length = 50)
    private String pickupLocation;
//    
    @Column(name = "droppOffLocation", unique = false, nullable = false, length = 50)
    private String droppOffLocation;
    
    @Column(name = "pickUpTime", unique = false, nullable = false, length = 50)
    private String pickUpTime;
    
    @Column(name = "dropOffTime", unique = false, nullable = false, length = 50)
    private String dropOffTime;
    
    @Column(name = "countryOfResidence", unique = false, nullable = false, length = 50)
    private String countryOfResidence;
    
    public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}





	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDroppOffLocation() {
		return droppOffLocation;
	}

	public void setDroppOffLocation(String droppOffLocation) {
		this.droppOffLocation = droppOffLocation;
	}

	public String getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public String getDropOffTime() {
		return dropOffTime;
	}

	public void setDropOffTime(String dropOffTime) {
		this.dropOffTime = dropOffTime;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	
	public Long getId() {
		return id;
	}
	

    
    
    

}
