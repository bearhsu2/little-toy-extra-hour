package idv.kuma.parser;

import idv.kuma.Constants;
import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.PunchData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PunchDataLineParser {


    public PunchDataLineParser() {

    }

    public PunchData parse(String line) {
        //員工編號,部門,姓名,日期,班別名稱,上班,上班地點,下班,下班地點,
        // 出勤狀況,遲到分鐘數,早退分鐘數,說明,外出登記,［*］代表簽核中
        PunchData punchData = new PunchData();

        String[] elements = line.split(",");

        if (elements.length <7){
            // no data
            return null;
        }
        punchData.setEmployeeId(Integer.parseInt(elements[0]));
        punchData.setName(elements[2]);

        if (!elements[3].isEmpty()) {
            punchData.setPunchDate(LocalDate.parse(elements[3].substring(0, 10), Constants.date_formatter));
        }
        if (!elements[5].isEmpty()) {
            String parsedCheckInTime = elements[5].replace("* ", "").substring(0, 8);
            punchData.setCheckinTime(LocalTime.parse(parsedCheckInTime, Constants.timeFormatter));
        }
        if (!elements[7].isEmpty()) {
            String parsedCheckoutTime = elements[7].replace("* ", "").substring(0, 8);
            punchData.setCheckoutTime(LocalTime.parse(parsedCheckoutTime, Constants.timeFormatter));
        }
        return punchData;
    }
}
