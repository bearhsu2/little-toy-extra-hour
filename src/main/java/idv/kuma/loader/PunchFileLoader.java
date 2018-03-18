package idv.kuma.loader;


import idv.kuma.Constants;
import idv.kuma.parser.ApplicationDataLineParser;
import idv.kuma.parser.PunchDataLineParser;
import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.PunchData;

import java.io.BufferedReader;
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

    public void loadData() throws IOException {


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));


        String line;

        line = bufferedReader.readLine();
        boolean firstLine = true;

        PunchDataLineParser parser = new PunchDataLineParser();

        try {
            while (line != null) {
                if (firstLine) {
                    firstLine = false;
                    line = bufferedReader.readLine();
                    continue;
                }

                // Parse line into ApplicationData
                PunchData punchData = parser.parse(line);
                if (punchData != null) {
                    dataList.add(punchData);
                }

                // Update line
                line = bufferedReader.readLine();

            }
        } finally {
            System.out.println(line);
            bufferedReader.close();
        }

        System.out.println("" + Constants.gson.toJson( dataList.get(0) ));

    }

    public List<PunchData> getDataList() {
        return this.dataList;
    }
}