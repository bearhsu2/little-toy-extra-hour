package idv.kuma.calcualtor;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.ApprovedData;
import idv.kuma.vo.PunchData;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bearhsu2 on 2/28/2018.
 */
public class Calculator {
    List<ApplicationData> applicationDataList;
    List<PunchData> punchDataList;
    private List<ApprovedData> approvedDataList;

    public Calculator(List<ApplicationData> applicationDataList, List<PunchData> punchDataList) {
        this.applicationDataList = applicationDataList;
        this.punchDataList = punchDataList;
        this.approvedDataList = new ArrayList<>();
    }

    public void calculate() {
//
        Stream<ApplicationData> applicationDataStream = applicationDataList.stream();

        approvedDataList = applicationDataStream.map((applicationData) -> mapToApproveData(applicationData,punchDataList)).collect(Collectors.toList());

    }

    public List<ApprovedData> getApprovedDataList() {
        return approvedDataList;
    }

    private ApprovedData mapToApproveData(ApplicationData applicationData, List<PunchData> punchDataList) {
        Stream<PunchData> punchDataStream = punchDataList.stream();
        List<PunchData> workDays = punchDataStream.filter(punchData ->
                punchData.getEmployeeId() == applicationData.getEmployeeId()
                        && punchData.getPunchDate().equals(applicationData.getEndTime().toLocalDate()))
                .collect(Collectors.toList());

        // PunchData should be only 0 or 1
        ApprovedData approvedData = new ApprovedData(applicationData);
        approvedData.setAppliedHours(computeAppliedHours(applicationData.getStartTime().toLocalTime(), applicationData.getEndTime().toLocalTime()));
        approvedData.setDate(applicationData.getStartTime().toLocalDate());
        if (workDays.isEmpty()) {
            approvedData.setRealHours(0);
            approvedData.setWeightedHours(0);
        } else {
            PunchData punchData = workDays.get(0);
            approvedData.setRealHours(computeRealHours(applicationData, punchData));
            approvedData.setWeightedHours(computerWeightHours(approvedData.getType(), approvedData.getRealHours()));
        }

        return approvedData;
    }

    double computeRealHours(ApplicationData applicationData, PunchData punchData) {

        LocalTime startTime = applicationData.getStartTime().toLocalTime();
        if (punchData.getCheckinTime().isAfter(startTime)) {
            startTime = punchData.getCheckinTime();
        }

        LocalTime endTime = applicationData.getEndTime().toLocalTime();
        if (punchData.getCheckoutTime().isBefore(endTime)) {
            endTime = punchData.getCheckoutTime();
        }

        double workHours = computeAppliedHours(startTime, endTime);
        double restTime = 0;
        if (workHours > 4)
            restTime += 0.5;
        if (workHours >= 5)
            restTime += 0.5;
        if (workHours > 8)
            restTime += 0.5;
        if (workHours >= 9)
            restTime += 0.5;

        return workHours - restTime;
    }


    double computerWeightHours(String overtimeType, double realHours) {
        double weightHour = 0;
        TypeParser type = TypeParser.parseString(overtimeType);
        switch (type) {
            case REST_DAT_OVERTIME:
                weightHour = computeWeightHourOnRestday(realHours);
                break;
            case HOLIDAY_OVERTIME:
                weightHour = computeWeightHourOnHoliday(realHours);
                break;
            case NORMAL_DAY_OVERTIME:
                weightHour = computeWeightHourOnNormalDay(realHours);
                break;
        }
        return weightHour;
    }

    double computeWeightHourOnNormalDay(double realHours) {
        double[] weightTable = {1.34, 1.34, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67};
        return multHourWith(realHours, weightTable);
    }

    double computeWeightHourOnHoliday(double realHours) {
        double[] weightTable = {8, 0, 0, 0, 0, 0, 0, 0, 1.34, 1.34, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67};
        return multHourWith(realHours, weightTable);
    }

    private double multHourWith(double hour, double[] weightTable) {
        double sum = 0;
        for (int i = 0; hour > 0; i++) {
            if (hour >= 1) {
                sum += weightTable[i];
            } else {
                sum += hour * weightTable[i];
            }
            hour--;
        }
        return sum;
    }

    double computeWeightHourOnRestday(double realWorkHours) {
        double[] weightTable = {1.34, 1.34, 1.67, 1.67, 1.67, 1.67, 1.67, 1.67
                , 2.67, 2.67, 2.67, 2.67, 2.67, 2.67, 2.67, 2.67};
        return multHourWith(realWorkHours, weightTable);
    }

    double computeAppliedHours(LocalTime start, LocalTime end) {
        double startHour = start.getHour();
        double endHour = end.getHour();

        if (start.getMinute() > 0) {
            startHour += 0.5;
        }
        if (start.getMinute() > 30) {
            startHour += 0.5;
        }

        if (end.getMinute() >= 30) {
            endHour += 0.5;
        }

        return endHour - startHour;
    }


    private Stream<ApprovedData> createApproveDatas(List<ApplicationData> datas) {
        return datas.stream().map(ApprovedData::new);
    }

    private void rearrangeApplicationTime() {
        for (ApplicationData applicationData : applicationDataList) {

        }
    }

}
