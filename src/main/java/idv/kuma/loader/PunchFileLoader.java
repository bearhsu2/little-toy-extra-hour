package idv.kuma.loader;


import com.opencsv.CSVReader;
import idv.kuma.parser.PunchDataLineParser;
import idv.kuma.vo.PunchData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearhsu2 on 2/28/2018.
 */
public class PunchFileLoader {

    String FILE_NAME = "punch.csv";

    List<PunchData> dataList;

    public PunchFileLoader() {
        this.dataList = new ArrayList<>();
    }

    public void setFileName(String fileName){
        this.FILE_NAME = fileName;
    }

    public void loadData() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getPath());
        if(!file.exists()){
            file = new File(FILE_NAME);
        }

        PunchDataLineParser parser = new PunchDataLineParser();

        CSVReader reader = null;
        reader = new CSVReader(new FileReader(file));
        String[] line = reader.readNext(); // read the header to pass it;
        while ((line = reader.readNext()) != null) {

            dataList.add(parser.parseFromArray(line));
            }
    }

    public List<PunchData> getDataList() {
        return this.dataList;
    }
}