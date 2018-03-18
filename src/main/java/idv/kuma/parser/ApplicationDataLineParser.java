package idv.kuma.parser;

import idv.kuma.Constants;
import idv.kuma.vo.ApplicationData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationDataLineParser {


    public ApplicationDataLineParser() {

    }

    public ApplicationData parse(String line){

        ApplicationData applicationData = new ApplicationData();

        String[] elements = line.split(",");

        applicationData.setSn(elements[0]);
        applicationData.setEmployeeId(Integer.valueOf(elements[1]));
        applicationData.setName(elements[2]);
        applicationData.setType(elements[3]);

        applicationData.setStartTime(LocalDateTime.parse(elements[4], Constants.formatter));
        applicationData.setEndTime(LocalDateTime.parse(elements[5], Constants.formatter));

        return applicationData;
    }
}
