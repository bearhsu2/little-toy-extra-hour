package idv.kuma.calcualtor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SundayHourWeightTest {

    @Parameterized.Parameters
    public static Collection<double[]> data() {
        return Arrays.asList(
                new double[][]{
                        {1, 8},
                        {1.5, 8},
                        {2, 8},
                        {2.5, 8},
                        {3, 8},
                        {4, 8},
                        {8, 8},
                        {8.5, 8 + 1.33333 * 0.5},
                        {9, 9.33},
                        {10, 10.67}

                }
        );
    }

    private double workHour;
    private double weightedHour;
    private Calculator calculator;

    public SundayHourWeightTest(double[] param) {
        this.workHour = param[0];
        this.weightedHour = param[1];
    }

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator(null, null);
    }

    @Test
    public void checkSaturdayWeight() {
        double weight = calculator.computeWeightHourOnHoliday(workHour);
        Assert.assertEquals(Math.round(weightedHour * 100) * 0.01, weight, 0.01);
    }
}
