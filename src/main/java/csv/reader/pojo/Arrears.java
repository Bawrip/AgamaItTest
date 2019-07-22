package csv.reader.pojo;

import java.time.LocalDate;
import java.time.YearMonth;

public class Arrears {
    private YearMonth accountingMonth;
    private double sum;
    private LocalDate debtDate;

    public YearMonth getAccountingMonth() {
        return accountingMonth;
    }

    public void setAccountingMonth(YearMonth accountingMonth) {
        this.accountingMonth = accountingMonth;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDate getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(LocalDate debtDate) {
        this.debtDate = debtDate;
    }

    @Override
    public String toString() {
        return "Arrears{" +
                "accountingMonth=" + accountingMonth +
                ", sum=" + sum +
                ", debtDate=" + debtDate +
                '}';
    }
}
