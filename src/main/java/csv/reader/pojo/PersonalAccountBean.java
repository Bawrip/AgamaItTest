package csv.reader.pojo;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import csv.reader.converter.LocalDateConverter;
import csv.reader.converter.LocalDateTimeConverter;
import csv.reader.converter.YearMonthConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class PersonalAccountBean {
    @CsvCustomBindByPosition(position = 0, converter = YearMonthConverter.class)
    private YearMonth duringThePeriod;

    @CsvCustomBindByPosition(position = 1, converter = YearMonthConverter.class)
    private YearMonth accountingMonth;

    @CsvCustomBindByPosition(position = 2, converter = LocalDateConverter.class)
    private LocalDate documentDate;

    @CsvCustomBindByPosition(position = 3, converter = LocalDateTimeConverter.class)
    private LocalDateTime dateOfCreation;

    @CsvBindByPosition(position = 4)
    private String type;

    @CsvBindByPosition(position = 5)
    private String consumption;

    @CsvBindByPosition(position = 6)
    private Double amount;

    @CsvBindByPosition(position = 7)
    private String status;

    public YearMonth getDuringThePeriod() {
        return duringThePeriod;
    }

    public void setDuringThePeriod(YearMonth duringThePeriod) {
        this.duringThePeriod = duringThePeriod;
    }

    public YearMonth getAccountingMonth() {
        return accountingMonth;
    }

    public void setAccountingMonth(YearMonth accountingMonth) {
        this.accountingMonth = accountingMonth;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PersonalAccountBean{" +
                "duringThePeriod='" + duringThePeriod + '\'' +
                ", accountingMonth='" + accountingMonth + '\'' +
                ", documentDate=" + documentDate +
                ", dateOfCreation=" + dateOfCreation +
                ", type='" + type + '\'' +
                ", consumption='" + consumption + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
