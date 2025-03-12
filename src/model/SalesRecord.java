package model;

public class SalesRecord {

    private int year;
    private int month;
    private int sales;

    public SalesRecord(int year, int month, int sales) {
        this.year = year;
        this.month = month;
        this.sales = sales;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "year=" + year +
                ", month=" + month +
                ", sales=" + sales +
                '}';
    }
}
