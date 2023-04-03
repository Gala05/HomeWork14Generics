package ru.netology.domain;

public class Ticket{
    private int id;
    private int amount;
    private String departureAirport;
    private String arrivalAirport;
    private int travelTime;

    public Ticket(int id, int amount, String departureAirport, String arrivalAirport, int travelTime) {
        this.id = id;
        this.amount = amount;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.travelTime = travelTime;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public int getTravelTime() {
        return travelTime;
    }
//
//    @Override
//    public int compareTo(Ticket o) {
//        if (this.amount < o.amount) {
//            return -1;
//        } else if (this.amount > o.amount) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }
}
