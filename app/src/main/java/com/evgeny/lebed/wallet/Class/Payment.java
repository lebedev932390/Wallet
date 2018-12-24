package com.evgeny.lebed.wallet.Class;


public class Payment implements Comparable<Payment> {

    private int id;
    private String note;
    private Double amount;
    private Long date;

    public Payment (int id, Long date, Double amount, String note ) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public double getAmount() {
        return amount;
    }

    public Long getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Payment o) {
        return getDate().compareTo(o.getDate());
    }
}
