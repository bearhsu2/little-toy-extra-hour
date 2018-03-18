package idv.kuma.loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PunchFileLoaderTest {

    private PunchFileLoader loader;

    @Before
    public void setUp() throws Exception {
        loader = new PunchFileLoader();

    }

    @Test
    public void when_loaded_then_hasItemFromResource() throws IOException {
        loader.loadData();

        Assert.assertEquals(3,loader.dataList.size());
        Assert.assertEquals(201608001,loader.dataList.get(0).getEmployeeId());
    }
}
