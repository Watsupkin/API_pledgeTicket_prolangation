package my.pledge.ticket.prolongation.models;

import javax.persistence.*;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String goodName;
    private double goodPrice;
    private int pledgeDays;
    private long clientID;

    public Ticket(String goodName, double goodPrice, int pledgeDays, long clientID) {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.pledgeDays = pledgeDays;
        this.clientID = clientID;
    }

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getPledgeDays() {
        return pledgeDays;
    }

    public void setPledgeDays(int pledgeDays) {
        this.pledgeDays = pledgeDays;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }
}
