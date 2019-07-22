package csv.reader.calculator;

import csv.reader.pojo.Arrears;
import csv.reader.pojo.PenaltyCalculationData;
import csv.reader.pojo.PenaltyCalculationResult;
import csv.reader.pojo.PersonalAccountBean;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PenaltiesCalculator {
    private final static int ONE_MONTH = 1;

    private double refinancingRate;
    private double rateReductionRatio;
    private int penaltyDay;
    private int daysFromDateOfAccrualOfInterest;
    private YearMonth billingPeriod;

    public PenaltiesCalculator(double refinancingRate, double rateReductionRatio, int penaltyDay, int daysFromDateOfAccrualOfInterest, YearMonth billingPeriod) {
        this.refinancingRate = refinancingRate;
        this.rateReductionRatio = rateReductionRatio;
        this.penaltyDay = penaltyDay;
        this.daysFromDateOfAccrualOfInterest = daysFromDateOfAccrualOfInterest;
        this.billingPeriod = billingPeriod;
    }

    public PenaltyCalculationResult calculatePenalties(List<PersonalAccountBean> personalAccountList) {
        personalAccountList.sort(Comparator.comparing(PersonalAccountBean::getAccountingMonth));

        List<Arrears> arrearsList = new ArrayList<>();
        for (PersonalAccountBean personalAccountRecord : personalAccountList) {
            arrearsList.add(calculateArrears(personalAccountRecord));
        }

        return calculatePenalties(arrearsList, billingPeriod.atDay(1));
    }

    private Arrears calculateArrears(PersonalAccountBean personalAccountRecord) {
        Arrears arrears = new Arrears();

        arrears.setAccountingMonth(personalAccountRecord.getAccountingMonth());
        arrears.setDebtDate(calculateDebtDate(arrears.getAccountingMonth(), penaltyDay, daysFromDateOfAccrualOfInterest));
        arrears.setSum(personalAccountRecord.getAmount());

        return arrears;
    }

    private LocalDate calculateDebtDate(YearMonth accountingMonth, int penaltyDay, int daysFromDateOfAccrualOfInterest) {
        LocalDate debtDate;

        accountingMonth = accountingMonth.plusMonths(ONE_MONTH);

        int maxDayOfMonth = accountingMonth.getMonth().maxLength();

        if (maxDayOfMonth < penaltyDay) {
            debtDate = accountingMonth.atDay(maxDayOfMonth);
        } else {
            debtDate = accountingMonth.atDay(penaltyDay);
        }
        debtDate = debtDate.plusDays(daysFromDateOfAccrualOfInterest);

        return debtDate;
    }

    private PenaltyCalculationResult calculatePenalties(List<Arrears> arrearsList, LocalDate billingPeriod) {
        double amountForPenaltyCalculation = 0;
        double sumOfPenalty = 0;
        int maxDayOfMonth = billingPeriod.getMonth().maxLength();
        int j;
        ArrayList<PenaltyCalculationData> penaltyCalculationDataList = new ArrayList<>();

        for (j = 0; arrearsList.get(j).getDebtDate().isBefore(billingPeriod); j++) {
            amountForPenaltyCalculation = amountForPenaltyCalculation + arrearsList.get(j).getSum();
        }

        for (int i = 0; i < maxDayOfMonth; i++) {
            LocalDate currentDateOfBillingPeriod = billingPeriod.plusDays(i);
            Arrears currentArrears = arrearsList.get(j);
            if (j < arrearsList.size() && !currentArrears.getDebtDate().isAfter(currentDateOfBillingPeriod)) {
                amountForPenaltyCalculation = amountForPenaltyCalculation + currentArrears.getSum();
                j++;
            }
            double penaltyOfDay = refinancingRate * amountForPenaltyCalculation / 100 / rateReductionRatio;
            penaltyOfDay = Math.round(penaltyOfDay * 100.0) / 100.0;

            penaltyCalculationDataList.add(new PenaltyCalculationData(currentDateOfBillingPeriod, amountForPenaltyCalculation, penaltyOfDay));

            sumOfPenalty = sumOfPenalty + penaltyOfDay;
        }
        sumOfPenalty =  Math.round(sumOfPenalty * 100.0) / 100.0;

        return new PenaltyCalculationResult(penaltyCalculationDataList, sumOfPenalty);
    }
}
