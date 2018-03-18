package idv.kuma.calcualtor;

import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.PunchData;

import java.util.List;

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
        // compare to punch data
        // rearrange application time
        // calculate hour
        // multiple
    }

    private void rearrangeApplicationTime () {
        for (ApplicationData applicationData : applicationDataList) {
            
        }
    }

}
