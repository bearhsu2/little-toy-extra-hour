package idv.kuma.calcualtor;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.ApprovedData;
import idv.kuma.vo.PunchData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CalculatorTest2 {

    List<ApplicationData> applicationDataList = new ArrayList<>();
    List<PunchData> punchDataList = new ArrayList<>();

    Calculator calculator;

    @Before
    public void setUp() throws Exception {
        applicationDataList.clear();
        punchDataList.clear();

        calculator = new Calculator(applicationDataList, punchDataList);


        int year = 2018, month = 3, day = 17;
        ApplicationData data = new ApplicationData();
        data.setEmployeeId(5487);
        data.setStartTime(LocalDateTime.of(year, month, day, 10, 0));
        data.setEndTime(LocalDateTime.of(year, month, day, 12, 0));
        data.setType("平日加班換加班費");
        applicationDataList.add(data);

        PunchData punchData = new PunchData();
        punchData.setEmployeeId(5487);
        punchData.setCheckinTime(LocalTime.of(9, 45));
        punchData.setCheckoutTime(LocalTime.of(12, 15));
        punchData.setPunchDate(LocalDate.of(year, month, day));
        punchDataList.add(punchData);
    }


    @Test
    public void when_inputData_then_mapApprovedList() {
        calculator.calculate();
        Assert.assertEquals(applicationDataList.size(), calculator.getApprovedDataList().size());
    }

    @Test
    public void theAppliedHourOfFirst_is2() {
        calculator.calculate();
        ApprovedData data = calculator.getApprovedDataList().get(0);
        Assert.assertEquals(2, data.getAppliedHours(), 0);

    }

    @Test
    public void theRealHourOfFirst_is2() {
        calculator.calculate();
        ApprovedData data = calculator.getApprovedDataList().get(0);
        Assert.assertEquals(2, data.getRealHours(), 0);
    }

    @Test
    public void theWeightHourOfFirst_is267() {
        calculator.calculate();
        ApprovedData data = calculator.getApprovedDataList().get(0);
        Assert.assertEquals(2.68, data.getWeightedHours(), 0.01);
    }

    @Test(expected = NullPointerException.class)
    public void when_punchNotCorrect_then_throwException() throws Exception {
        punchDataList.get(0).setCheckinTime(null);

        calculator.computeRealHours(applicationDataList.get(0),punchDataList.get(0));

    }
}
