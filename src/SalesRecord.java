import java.time.YearMonth;

public class SalesRecord {

    private YearMonth date;
    private int sales;

    public SalesRecord(YearMonth date, int sales) {
        this.date = date;
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

    @Override
    public String toString() {
        return "SalesRecord{" +
                "date=" + date +
                ", sales=" + sales +
                '}';
    }
}
