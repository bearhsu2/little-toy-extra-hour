package idv.kuma.calcualtor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SaturdayHourWeightTest {

    @Parameterized.Parameters
    public static Collection<double[]> data() {
        List<double[]> params = Arrays.asList(
                new double[][]{
                        {1, 1.34},
                        {1.5, 1.34 * 1.5},
                        {2, 2.68},
                        {2.5, 1.34 * 2 + 1.67 * 0.5},
                        {3, 4.35},
                        {4, 6.02},
                        {8, 12.7},
                        {8.5, 12.7 + 2.67 * 0.5},
                        {9, 15.37},
                        {10, 18.04}

                }
        );
        return params;
    }

    private double workHour;
    private double weightedHour;
    private Calculator calculator;

    public SaturdayHourWeightTest(double[] param) {
        this.workHour = param[0];
        this.weightedHour = param[1];
    }

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator(null, null);
    }

    @Test
    public void checkSaturdayWeight() {
        double weight = calculator.computeWeightHourOnRestday(workHour);
        Assert.assertEquals(Math.round(weightedHour * 100) * 0.01, weight, 0.01);
    }
}
