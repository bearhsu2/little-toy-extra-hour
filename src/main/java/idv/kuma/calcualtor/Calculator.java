package idv.kuma.calcualtor;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.ApprovedData;
import idv.kuma.vo.PunchData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bearhsu2 on 2/28/2018.
 */
public class Calculator {
    List<ApplicationData> applicationDataList;
    List<PunchData> punchDataList;

    public Calculator(List<ApplicationData> applicationDataList, List<PunchData> punchDataList) {
        this.applicationDataList = applicationDataList;
        this.punchDataList = punchDataList;
    }

    public void calculate() {
//        Stream<ApprovedData> dataStream = createApproveDatas(applicationDataList);
//
        Stream<ApplicationData> applicationDataStream = applicationDataList.stream();
        Stream<PunchData> punchDataStream = punchDataList.stream();
        List<ApprovedData> approvedDataList = new ArrayList<>();

        applicationDataStream.forEach(new Consumer<ApplicationData>() {
            @Override
            public void accept(ApplicationData applicationData) {


                ApprovedData approvedData = mapToApproveData(applicationData, punchDataStream);

            }
        });

        // compare to punch data
        // rearrange application time
        // calculate hour
        // multiple
    }

    private ApprovedData mapToApproveData(ApplicationData applicationData, Stream<PunchData> punchDataStream) {
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
            approvedData.setWeightedHours(computerWeightHours(approvedData.getDate(), approvedData.getRealHours()));
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

        // minus rest time
        double workHours = computeAppliedHours(startTime, endTime);
        if (workHours >= 5) {
            // must take rest 1 hour after working 4 hours
            int restTime = 1;
            if (workHours >= 9) {
                restTime = 2;
            }
            workHours -= restTime;
        }

        return workHours;
    }

    double computerWeightHours(LocalDate date, double realHours) {
        double weightHour = 0;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
            case SATURDAY:
                weightHour = computeWeightHourOnSaturday(realHours);
                break;
            case SUNDAY:
                weightHour = computeWeightHourOnSunday(realHours);
                break;
            default:
                weightHour = computeWeightHourOnNormalDay(realHours);
                break;
        }
        return weightHour;
    }

    double computeWeightHourOnNormalDay(double realHours) {
        return 0;
    }

    double computeWeightHourOnSunday(double realHours) {
        return 0;
    }

    double computeWeightHourOnSaturday(double realWorkHours) {
        double[] weightTable = {1.333333, 1.333333, 1.666666, 1.666666, 1.666666, 1.666666, 1.666666, 1.666666
                , 2.666666, 2.666666, 2.666666, 2.666666, 2.666666, 2.666666, 2.666666, 2.666666};

        double sum = 0;
        for (int i = 0; realWorkHours > 0; i++) {
            if (realWorkHours >= 1) {
                sum += weightTable[i];
            } else {
                sum += realWorkHours * weightTable[i];
            }
            realWorkHours--;
        }
        return Math.round(sum * 100) * 0.01;
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
