package idv.kuma.loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ApplicationFileLoaderTest {

    private ApplicationFileLoader loader;

    @Before
    public void setUp() throws Exception {
        loader = new ApplicationFileLoader();

    }

    @Test
    public void when_loaded_then_haveItemFromResource() throws IOException {
        loader.loadData();

        Assert.assertEquals(1,loader.dataList.size());
        Assert.assertEquals("E1709140001",loader.dataList.get(0).getSn());

    }
}
