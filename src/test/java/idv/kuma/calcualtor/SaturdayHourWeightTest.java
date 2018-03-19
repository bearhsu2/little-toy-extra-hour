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
                        {1, 1.3333},
                        {1.5, 1.3333 * 1.5},
                        {2, 2.67},
                        {2.5, 1.33 * 2 + 1.666 * 0.5},
                        {3, 4.33},
                        {4, 6.0},
                        {8, 12.67},
                        {8.5, 12.6666 + 2.6666 * 0.5},
                        {9, 15.33},
                        {10, 18.00}

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
