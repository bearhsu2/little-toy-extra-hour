package idv.kuma.loader;


import idv.kuma.Constants;
import idv.kuma.parser.ApplicationDataLineParser;
import idv.kuma.vo.ApplicationData;

import java.io.BufferedReader;
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
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));


        String line;

        line = bufferedReader.readLine();
        boolean firstLine = true;

        ApplicationDataLineParser parser = new ApplicationDataLineParser();

        try {
            while (line != null) {
                if (firstLine) {
                    firstLine = false;
                    line = bufferedReader.readLine();
                    continue;
                }

                // Parse line into ApplicationData
                dataList.add(parser.parse(line));

                // Update line
                line = bufferedReader.readLine();

            }
        } finally {
            bufferedReader.close();
        }

        System.out.println("" + Constants.gson.toJson( dataList.get(0) ));

    }

    public List<ApplicationData> getDataList() {
        return this.dataList;
    }
}