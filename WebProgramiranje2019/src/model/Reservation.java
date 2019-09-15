package model;

import java.util.Date;

public class Reservation {

	private int id;
	private Flight startFlight;
	private Flight endFlight;
	private int startFlightSeat; // ili da seat bude klasa
	private int endFlightSeat;
	private Date reservationDate;
	private Date ticketSaleDate; // ??
	private User passenger;
	private String passengerFirstname;
	private String passengerLastname;
	private boolean deleted;

	public Reservation() {
		super();
	}
	

	public Reservation(Flight startFlight, Flight endFlight, int startFlightSeat, int endFlightSeat, User passenger,
			String passengerFirstname, String passengerLastname) {
		super();
		this.startFlight = startFlight;
		this.endFlight = endFlight;
		this.startFlightSeat = startFlightSeat;
		this.endFlightSeat = endFlightSeat;
		this.passenger = passenger;
		this.passengerFirstname = passengerFirstname;
		this.passengerLastname = passengerLastname;
		
		this.reservationDate = startFlight.getStartDate();
		this.ticketSaleDate = new Date();
		this.deleted = false;
	}


	public Reservation(int id, Flight startFlight, Flight endFlight, int startFlightSeat, int endFlightSeat,
			Date reservationDate, Date ticketSaleDate, User passenger, String passengerFirstname,
			String passengerLastname, boolean deleted) {
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
		this.deleted = deleted;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
