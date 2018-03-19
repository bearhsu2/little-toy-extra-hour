package idv.kuma.loader;


import com.opencsv.CSVReader;
import idv.kuma.parser.ApplicationDataLineParser;
import idv.kuma.vo.ApplicationData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearhsu2 on 2/28/2018.
 */
public class ApplicationFileLoader {

    String FILE_NAME = "application.csv";

    List<ApplicationData> dataList;

    public ApplicationFileLoader() {
        this.dataList = new ArrayList<>();
    }

    public void loadData() throws IOException {


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());

        ApplicationDataLineParser parser = new ApplicationDataLineParser();
        CSVReader reader = null;
        reader = new CSVReader(new FileReader(file));
        String[] line = reader.readNext(); // read the header to pass it;
        while ((line = reader.readNext()) != null) {
            dataList.add(parser.parseFromArray(line));
            }

    }

    public List<ApplicationData> getDataList() {
        return this.dataList;
    }
}