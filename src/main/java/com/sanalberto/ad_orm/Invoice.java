package com.sanalberto.ad_orm;
// Generated 11 ene. 2021 21:27:48 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name = "Invoice", catalog = "chinook")
public class Invoice implements java.io.Serializable {

	private Integer invoiceId;
	private Customer customer;
	private Date invoiceDate;
	private String billingAddress;
	private String billingCity;
	private String billingState;
	private String billingCountry;
	private String billingPostalCode;
	private BigDecimal total;
	private Set<InvoiceLine> invoiceLines = new HashSet<InvoiceLine>(0);

	public Invoice() {
	}

	public Invoice(Customer customer, Date invoiceDate, BigDecimal total) {
		this.customer = customer;
		this.invoiceDate = invoiceDate;
		this.total = total;
	}

	public Invoice(Customer customer, Date invoiceDate, String billingAddress, String billingCity, String billingState,
			String billingCountry, String billingPostalCode, BigDecimal total, Set<InvoiceLine> invoiceLines) {
		this.customer = customer;
		this.invoiceDate = invoiceDate;
		this.billingAddress = billingAddress;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingCountry = billingCountry;
		this.billingPostalCode = billingPostalCode;
		this.total = total;
		this.invoiceLines = invoiceLines;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "InvoiceId", unique = true, nullable = false)
	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerId", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InvoiceDate", nullable = false, length = 19)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name = "BillingAddress", length = 70)
	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Column(name = "BillingCity", length = 40)
	public String getBillingCity() {
		return this.billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	@Column(name = "BillingState", length = 40)
	public String getBillingState() {
		return this.billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	@Column(name = "BillingCountry", length = 40)
	public String getBillingCountry() {
		return this.billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	@Column(name = "BillingPostalCode", length = 10)
	public String getBillingPostalCode() {
		return this.billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	@Column(name = "Total", nullable = false, precision = 10)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public Set<InvoiceLine> getInvoiceLines() {
		return this.invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

}
