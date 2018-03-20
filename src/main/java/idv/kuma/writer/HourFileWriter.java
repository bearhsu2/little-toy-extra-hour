package idv.kuma.writer;

import idv.kuma.parser.ApprovedDataLineBridge;
import idv.kuma.vo.ApprovedData;

import java.io.*;
import java.util.List;

public class HourFileWriter {

    private String FILE_NAME = "hour.csv";

    List<ApprovedData> dataList;

    public HourFileWriter(List<ApprovedData> dataList) {
        this.dataList = dataList;
    }

    public void setFileName(String fileName){
        this.FILE_NAME = fileName;
    }


    public void writeData() throws IOException {
        boolean firstWrite = false;
        File file = new File(FILE_NAME);
        if (!file.exists()){
            firstWrite = true;
        }

        ApprovedDataLineBridge bridge = new ApprovedDataLineBridge();
        FileWriter writer = new FileWriter(file,true);
        if (firstWrite){
            writer.write(ApprovedDataLineBridge.getCSVHeader());
            writer.write(System.lineSeparator());
        }

        for (ApprovedData data :
                dataList) {
            writer.write(bridge.parseToString(data));
            writer.write(System.lineSeparator());
        }

        writer.close();
    }
}
