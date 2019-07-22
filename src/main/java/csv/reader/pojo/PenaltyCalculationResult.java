package csv.reader.pojo;

import java.util.ArrayList;

public class PenaltyCalculationResult {
    private ArrayList<PenaltyCalculationData> penaltyCalculationDataList;
    private double sumOfPenalty;

    public PenaltyCalculationResult(ArrayList<PenaltyCalculationData> penaltyCalculationDataList, double sumOfPenalty) {
        this.penaltyCalculationDataList = penaltyCalculationDataList;
        this.sumOfPenalty = sumOfPenalty;
    }

    public ArrayList<PenaltyCalculationData> getPenaltyCalculationDataList() {
        return penaltyCalculationDataList;
    }

    public double getSumOfPenalty() {
        return sumOfPenalty;
    }
}
