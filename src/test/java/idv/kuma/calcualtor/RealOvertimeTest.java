package idv.kuma.calcualtor;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.PunchData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class RealOvertimeTest {

    @Parameterized.Parameters
    public static Collection<double[]> data() {
        return Arrays.asList(
                new double[][]{
                        {10, 0, 19, 0, 10, 0, 19, 10, 7},
                        {10, 0, 23, 0, 10, 0, 23, 10, 11},
                        {19, 0, 22, 30, 10, 0, 22, 10, 3},
                        {10, 0, 14, 30, 10, 0, 14, 30, 4},
                        {10, 0, 15, 0, 10, 0, 15, 0, 4},
                        {10, 0, 18, 0, 10, 0, 18, 0, 7},
                        {10, 0, 18, 30, 10, 0, 18, 30, 7},

                }
        );
    }

    private Calculator calculator;
    private ApplicationData applicationData;
    private PunchData punchData;
    private double expectResult;

    public RealOvertimeTest(double[] params) {
        calculator = new Calculator(null, null);

        Random random = new Random();
        int year = random.nextInt(3) + 2014;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        applicationData = new ApplicationData();
        applicationData.setStartTime(LocalDateTime.of(year, month, day, (int) (params[0]), (int) (params[1])));
        applicationData.setEndTime(LocalDateTime.of(year, month, day, (int) (params[2]), (int) (params[3])));

        punchData = new PunchData();
        punchData.setCheckinTime(LocalTime.of((int) params[4], (int) params[5]));
        punchData.setCheckoutTime(LocalTime.of((int) params[6], (int) params[7]));

        expectResult = params[8];
    }

    @Test
    public void restOvertimeTest() {
        double workHour = calculator.computeRealHours(applicationData, punchData);
        Assert.assertEquals(expectResult, workHour, 0.1);
    }
}
