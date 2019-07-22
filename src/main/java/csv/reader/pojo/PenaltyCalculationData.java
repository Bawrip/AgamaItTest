package csv.reader.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PenaltyCalculationData {
    private LocalDate date;
    private double sum;
    private double penalty;

    public PenaltyCalculationData(LocalDate date, double sum, double penalty) {
        this.date = date;
        this.sum = sum;
        this.penalty = penalty;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public double getPenalty() {
        return penalty;
    }

    @Override
    public String toString() {
        return  date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + sum + " " + penalty;
    }
}
