package model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Cruise extends Entity {
    private static final long serialVersionUID = 1L;
    private Ship ship;
    private int duration;
    private double price;
    private Date startDate;
    private City startPort;
    private City endPort;
    private boolean paid;

    public Cruise() {
    }

    public Cruise(Ship ship) {
        this.ship = ship;
    }

    public Cruise(Ship ship, int duration, Date startDate, boolean paid) {
        this.ship = ship;
        this.duration = duration;
        this.startDate = startDate;
        this.paid = paid;
    }

    public Cruise(Ship ship, int duration, double price, Date startDate, City startPort, City endPort, boolean paid) {
        this.ship = ship;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        ;
        this.startPort = startPort;
        this.endPort = endPort;
        this.paid = paid;
    }

    public Cruise(long id, Ship ship, int duration, double price, Date startDate, City startPort, City endPort, boolean paid) {
        super(id);
        this.ship = ship;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        ;
        this.startPort = startPort;
        this.endPort = endPort;
        this.paid = paid;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public City getStartPort() {
        return startPort;
    }

    public void setStartPort(City startPort) {
        this.startPort = startPort;
    }

    public City getEndPort() {
        return endPort;
    }

    public void setEndPort(City endPort) {
        this.endPort = endPort;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship shipId) {
        this.ship = shipId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
