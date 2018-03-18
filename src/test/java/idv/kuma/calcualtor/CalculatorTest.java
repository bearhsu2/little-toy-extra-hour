package idv.kuma.calcualtor;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.PunchData;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculatorTest {

    @Test
    public void when_8to10_then_return2() {
        Calculator calculator = new Calculator(null, null);

        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(10, 0);

        Assert.assertEquals(2, calculator.computeAppliedHours(start, end), 0);

    }

    @Test
    public void when_19andHalfTo23_then_return3andHalf() {
        Calculator calculator = new Calculator(null, null);

        LocalTime start = LocalTime.of(19, 30);
        LocalTime end = LocalTime.of(23, 0);

        Assert.assertEquals(3.5, calculator.computeAppliedHours(start, end), 0);

    }

    @Test
    public void when_19and45To23and15min_then_return3() {
        Calculator calculator = new Calculator(null, null);

        LocalTime start = LocalTime.of(19, 45);
        LocalTime end = LocalTime.of(23, 15);

        Assert.assertEquals(3, calculator.computeAppliedHours(start, end), 0);

    }

    @Test
    public void when_19and15To23and45min_then_return4() {
        Calculator calculator = new Calculator(null, null);

        LocalTime start = LocalTime.of(19, 15);
        LocalTime end = LocalTime.of(23, 45);

        Assert.assertEquals(4, calculator.computeAppliedHours(start, end), 0);

    }

    @Test
    public void when_punchTimeNotEqualApplicationTime_then_chooseLessOne() {
        Calculator calculator = new Calculator(null, null);
        ApplicationData data = new ApplicationData();
        data.setStartTime(LocalDateTime.of(2018, 3, 19, 19, 0));
        data.setEndTime(LocalDateTime.of(2018, 3, 19, 22, 30));

        PunchData punchData = new PunchData();
        punchData.setCheckinTime(LocalTime.of(10, 0));
        punchData.setCheckoutTime(LocalTime.of(22, 10));

        double workHour = calculator.computeRealHours(data, punchData);
        Assert.assertEquals(3, workHour, 0);
    }

    @Test
    public void when_workMoreThen4Hour_then_rest1hour() {
        Calculator calculator = new Calculator(null, null);
        ApplicationData data = new ApplicationData();
        data.setStartTime(LocalDateTime.of(2018, 3, 17, 10, 0));
        data.setEndTime(LocalDateTime.of(2018, 3, 17, 19, 0));

        PunchData punchData = new PunchData();
        punchData.setCheckinTime(LocalTime.of(10, 0));
        punchData.setCheckoutTime(LocalTime.of(19, 10));

        double workHour = calculator.computeRealHours(data, punchData);
        Assert.assertEquals(7, workHour, 0);
    }

    @Test
    public void when_workMoreThen12Hour_then_stillRest2hour() {
        Calculator calculator = new Calculator(null, null);
        ApplicationData data = new ApplicationData();
        data.setStartTime(LocalDateTime.of(2018, 3, 17, 10, 0));
        data.setEndTime(LocalDateTime.of(2018, 3, 17, 23, 0));

        PunchData punchData = new PunchData();
        punchData.setCheckinTime(LocalTime.of(10, 0));
        punchData.setCheckoutTime(LocalTime.of(23, 10));

        double workHour = calculator.computeRealHours(data, punchData);
        Assert.assertEquals(11, workHour, 0);
    }
}
