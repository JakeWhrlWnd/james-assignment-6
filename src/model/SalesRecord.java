package model;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class SalesRecord {

    private YearMonth date;
    private int sales;

    public SalesRecord(YearMonth date, int sales) {
        this.date = date;
        this.sales = sales;
    }

    public SalesRecord(String date, int sales) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
        this.date = YearMonth.parse(date, formatter);
        this.sales = sales;
    }

    public YearMonth getDate() {
        return date;
    }

    public void setDate(YearMonth date) {
        this.date = date;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getFormattedDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "date=" + date.format(DateTimeFormatter.ofPattern("yyyy-MM")) +
                ", sales=" + sales +
                '}';
    }
}
