package ru.nikky.aviasales;

public class Product {
    private String airports, time, airport1, airport2, airport3, transfer1, transfer2, transfer3, transfers, price;

    public Product(String airports, String time, String airport1, String airport2, String airport3, String transfer1, String transfer2, String transfer3, String transfers, String price) {
        this.airports = airports;
        this.time = time;
        this.airport1 = airport1;
        this.airport2 = airport2;
        this.airport3 = airport3;
        this.transfer1 = transfer1;
        this.transfer2 = transfer2;
        this.transfer3 = transfer3;
        this.transfers = transfers;
        this.price = price;
    }

    public String getAirports() {
        return airports;
    }

    public String getTime() {
        return time;
    }

    public String getAirport1() {
        return airport1;
    }

    public String getAirport2() {
        return airport2;
    }

    public String getAirport3() {
        return airport3;
    }

    public String getTransfer1() {
        return transfer1;
    }

    public String getTransfer2() {
        return transfer2;
    }

    public String getTransfer3() {
        return transfer3;
    }

    public String getTransfers() {
        return transfers;
    }

    public String getPrice() {
        return price;
    }
}
