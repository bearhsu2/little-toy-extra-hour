package idv.kuma.parser;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.ApprovedData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ApprovedDataLineBridgeTest {

    private ApprovedDataLineBridge bridge;

    @Before
    public void setUp() throws Exception {
        bridge = new ApprovedDataLineBridge();
    }

    @Test
    public void when_hourHasHalf_then_itShouldPrintExactly() {
        String expect = "2017-07-06,201706002,[加班]$平日加班換加班費(時數換算比照勞基法規定)(預設),2.5,2.5,3.515";

        ApplicationData applicationData = new ApplicationData();
        applicationData.setEmployeeId(201706002);

        applicationData.setType("[加班]$平日加班換加班費(時數換算比照勞基法規定)(預設)");
        ApprovedData data = new ApprovedData(applicationData);
        data.setDate(LocalDate.of(2017,7,6));
        data.setRealHours(2.5);
        data.setAppliedHours(2.5);
        data.setWeightedHours(3.515);

        Assert.assertEquals(expect,bridge.parseToString(data));
    }

    @Test
    public void when_hourHasInt_then_itShouldPrintExactly() {
        String expect = "2017-07-27,201608013,[加班]$平日加班換加班費(時數換算比照勞基法規定)(預設),2.0,1.5,2.01";

        ApplicationData applicationData = new ApplicationData();
        applicationData.setEmployeeId(201608013);

        applicationData.setType("[加班]$平日加班換加班費(時數換算比照勞基法規定)(預設)");
        ApprovedData data = new ApprovedData(applicationData);
        data.setDate(LocalDate.of(2017,7,27));
        data.setRealHours(1.5);
        data.setAppliedHours(2.0);
        data.setWeightedHours(1 * 1.34 + 0.5 * 1.34);

        Assert.assertEquals(expect,bridge.parseToString(data));
    }

    @Test
    public void when_hasRemark_then_itShouldPrintExactly() {
        String expect = "2017-07-27,201608013,[加班]$平日加班換加班費(時數換算比照勞基法規定)(預設),2.0,1.5,2.01,test";

        ApplicationData applicationData = new ApplicationData();
        applicationData.setEmployeeId(201608013);

        applicationData.setType("[加班]$平日加班換加班費(時數換算比照勞基法規定)(預設)");
        ApprovedData data = new ApprovedData(applicationData);
        data.setDate(LocalDate.of(2017,7,27));
        data.setRealHours(1.5);
        data.setAppliedHours(2.0);
        data.setWeightedHours(1 * 1.34 + 0.5 * 1.34);
        data.setRemark("test");

        Assert.assertEquals(expect,bridge.parseToString(data));
    }
}
