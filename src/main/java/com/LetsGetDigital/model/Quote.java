package com.LetsGetDigital.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Quote")
@NamedQueries({
    @NamedQuery(
            name = "com.LetsGetDigital.model.Quote.findAll",
            query = "FROM Quote q"
    ),
    @NamedQuery(
            name = "com.LetsGetDigital.model.Quote.findQuoteByReference",
            query = "FROM Quote quote "
            		+ " where quote.reference = :reference"
    ),
    @NamedQuery(
            name = "com.LetsGetDigital.model.Quote.findBySearchCriteriaId",
            query = "FROM Quote q"
            		+ " left join fetch q.searchCriteria searchCriteria"
            		+  " where searchCriteria.id = :searchCriteriaId"
    ),
    @NamedQuery(
            name = "com.LetsGetDigital.model.Quote.findBySearchCriteria",
            query = "FROM Quote q"
            		+ " left join fetch q.searchCriteria searchCriteria"
            		+  " where "
            		+ " searchCriteria.fromDate = :fromDate"
            		+ " and searchCriteria.toDate = :toDate"
            		+ " and searchCriteria.pickupLocation = :pickupLocation"
            		+ " and searchCriteria.droppOffLocation = :droppOffLocation"
            		+ " and searchCriteria.pickUpTime = :pickUpTime"
            		+ " and searchCriteria.dropOffTime = :dropOffTime"
            		+ " and searchCriteria.countryOfResidence = :countryOfResidence"

    )
})

public class Quote {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Long getId() {
		return id;
	}

	@ManyToOne
    @JoinColumn(name="searchCriteriaId")
    private SearchCriteria searchCriteria;

	private String reference;
	
    @Enumerated(EnumType.STRING)
    private PaymentModel paymentModel;
    
    private BigDecimal total;
    private String baseCurrency;
    private String paymentCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal browseTotal;
    private CarType cartype;
    
    public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public PaymentModel getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(PaymentModel paymentModel) {
		this.paymentModel = paymentModel;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getBrowseTotal() {
		return browseTotal;
	}

	public void setBrowseTotal(BigDecimal browseTotal) {
		this.browseTotal = browseTotal;
	}




	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public CarType getCartype() {
		return cartype;
	}

	public void setCartype(CarType cartype) {
		this.cartype = cartype;
	}
    
    

}
