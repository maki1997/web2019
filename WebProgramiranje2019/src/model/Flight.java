package model;

import java.util.Date;

public class Flight {
	
	private int id;
	private String flightNumber;
	private Date startDate;
	private Date endDate;
	private Airport startAirport;
	private Airport endAirport;
	private int numberOfSeats;
	private double ticketPrice;
	private boolean deleted;
	
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Flight(int id, String flightNumber, Date startDate, Date endDate, Airport startAirport, Airport endAirport,
			int numberOfSeats, double ticketPrice, boolean deleted) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAirport = startAirport;
		this.endAirport = endAirport;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = ticketPrice;
		this.deleted = deleted;
	}
	
	public Flight(String flightNumber, Date startDate, Date endDate, Airport startAirport, Airport endAirport,
			int numberOfSeats, double ticketPrice, boolean deleted) {
		super();
		this.flightNumber = flightNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAirport = startAirport;
		this.endAirport = endAirport;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = ticketPrice;
		this.deleted = deleted;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Airport getStartAirport() {
		return startAirport;
	}


	public void setStartAirport(Airport startAirport) {
		this.startAirport = startAirport;
	}


	public Airport getEndAirport() {
		return endAirport;
	}


	public void setEndAirport(Airport endAirport) {
		this.endAirport = endAirport;
	}


	public int getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public double getTicketPrice() {
		return ticketPrice;
	}


	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	

}