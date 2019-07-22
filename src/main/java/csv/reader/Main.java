package csv.reader;

import csv.reader.calculator.PenaltiesCalculator;
import csv.reader.pojo.PenaltyCalculationData;
import csv.reader.pojo.PenaltyCalculationResult;
import csv.reader.pojo.PersonalAccountBean;
import csv.reader.reader.CsvReader;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {

            String csvFilePath;
            double refinancingRate;
            double rateReductionRatio;
            int penaltyDay;
            int daysFromDateOfAccrualOfInterest;
            YearMonth billingPeriod;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy", Locale.ENGLISH);

            try(Scanner scanner = new Scanner(System.in)){
                System.out.println("Введите путь к CSV файлу:");
                csvFilePath = scanner.next();

                System.out.println("Введите размер ставки рефинансирования:");
                refinancingRate = scanner.nextDouble();

                System.out.println("Введите день начисления пени");
                penaltyDay = scanner.nextInt();

                System.out.println("Введите количество дней отсрочки от дня начисления пени:");
                daysFromDateOfAccrualOfInterest = scanner.nextInt();

                System.out.println("Введите коэффициент уменьшения ставки:");
                rateReductionRatio = scanner.nextDouble();

                System.out.println("Введите период расчета:");
                String date = scanner.next();
                billingPeriod = YearMonth.parse(date, formatter);
            }

            CsvReader<PersonalAccountBean> csvReader = new CsvReader<>();
            List<PersonalAccountBean> personalAccountList = csvReader.readCsvToBean(csvFilePath, PersonalAccountBean.class);

            PenaltiesCalculator penaltiesCalculator =
                    new PenaltiesCalculator(
                            refinancingRate,
                            rateReductionRatio,
                            penaltyDay,
                            daysFromDateOfAccrualOfInterest,
                            billingPeriod
                    );

            PenaltyCalculationResult penaltyCalculationResult = penaltiesCalculator.calculatePenalties(personalAccountList);

            System.out.println();
            System.out.println("Расчет пени");
            System.out.println("День Сумма пени");
            for (PenaltyCalculationData data : penaltyCalculationResult.getPenaltyCalculationDataList()) {
                System.out.println(data);
            }
            System.out.println("Итого: " + penaltyCalculationResult.getSumOfPenalty());
        } catch (Exception exc) {
            System.out.println("Exception: " + exc);
        }
    }
}
