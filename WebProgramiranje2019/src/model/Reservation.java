package model;

import java.util.Date;

public class Reservation {
	
	private int id;
	private Flight startFlight;
	private Flight endFlight;
	private int startFlightSeat; //ili da seat bude klasa
	private int endFlightSeat; 
	private Date reservationDate;
	private Date ticketSaleDate; // ??
	private User passenger;
	private String passengerFirstname;
	private String passengerLastname;
	
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reservation(int id, Flight startFlight, Flight endFlight, int startFlightSeat, int endFlightSeat,
			Date reservationDate, Date ticketSaleDate, User passenger, String passengerFirstname,
			String passengerLastname) {
		super();
		this.id = id;
		this.startFlight = startFlight;
		this.endFlight = endFlight;
		this.startFlightSeat = startFlightSeat;
		this.endFlightSeat = endFlightSeat;
		this.reservationDate = reservationDate;
		this.ticketSaleDate = ticketSaleDate;
		this.passenger = passenger;
		this.passengerFirstname = passengerFirstname;
		this.passengerLastname = passengerLastname;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Flight getStartFlight() {
		return startFlight;
	}


	public void setStartFlight(Flight startFlight) {
		this.startFlight = startFlight;
	}


	public Flight getEndFlight() {
		return endFlight;
	}


	public void setEndFlight(Flight endFlight) {
		this.endFlight = endFlight;
	}


	public int getStartFlightSeat() {
		return startFlightSeat;
	}


	public void setStartFlightSeat(int startFlightSeat) {
		this.startFlightSeat = startFlightSeat;
	}


	public int getEndFlightSeat() {
		return endFlightSeat;
	}


	public void setEndFlightSeat(int endFlightSeat) {
		this.endFlightSeat = endFlightSeat;
	}


	public Date getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}


	public Date getTicketSaleDate() {
		return ticketSaleDate;
	}


	public void setTicketSaleDate(Date ticketSaleDate) {
		this.ticketSaleDate = ticketSaleDate;
	}


	public User getPassenger() {
		return passenger;
	}


	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}


	public String getPassengerFirstname() {
		return passengerFirstname;
	}


	public void setPassengerFirstname(String passengerFirstname) {
		this.passengerFirstname = passengerFirstname;
	}


	public String getPassengerLastname() {
		return passengerLastname;
	}


	public void setPassengerLastname(String passengerLastname) {
		this.passengerLastname = passengerLastname;
	}
	
	
	
	

}
