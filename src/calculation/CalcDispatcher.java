package calculation;

/**
 * Created by Kuba on 12/26/2015.
 */
public class CalcDispatcher {

    public static CalcResult calculate(double debt, double rate, int noPeriods,
        CalcRepaymentType repaymentType, CalcPeriodInputType periodInput, CalcInterestAccrualRate accrualRate)
    {

        Calculation calc = null;

        if (periodInput == CalcPeriodInputType.YEAR && accrualRate == CalcInterestAccrualRate.MONTHLY){
            noPeriods *= 12;
            rate /= 12;
        } else if (periodInput == CalcPeriodInputType.MONTH && accrualRate == CalcInterestAccrualRate.MONTHLY){
            rate /= 12;
        } else if (periodInput == CalcPeriodInputType.MONTH && accrualRate == CalcInterestAccrualRate.YEARLY)
            throw new RuntimeException("Input in months and yearly interest accrual not allowed.");
        //w przeciwnym wypadku mamy input in YEARs i YEARLY interest accrual -> nie modyfikujemy rate ani noPeriods

        switch (repaymentType){
            case CONSTANT_CAPITAL_PART: calc = new ConstCapitalPartCalc(debt, rate, noPeriods); break;
            case CONSTANT_INSTALLMENT:  calc = new ConstantInstallmentCalc(debt, rate, noPeriods); break;
        }

        return  calc.calculate();
    }
}