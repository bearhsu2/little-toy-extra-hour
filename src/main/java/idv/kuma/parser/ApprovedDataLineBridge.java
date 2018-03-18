package idv.kuma.parser;

import idv.kuma.vo.ApprovedData;

public class ApprovedDataLineBridge {

    public static String getCSVHeader(){
        return "Date,EmployeeId,Type,AppliedHours,RealHours,WeightedHours";
    }

    public String parseToString(ApprovedData data){
        StringBuilder builder = new StringBuilder();
        String split = ",";

        builder.append(data.getDate().toString()).append(split)
                .append(data.getEmployeeId()).append(split)
                .append(data.getType()).append(split)
                .append(data.getAppliedHours()).append(split)
                .append(data.getRealHours()).append(split)
                .append(data.getWeightedHours());

        return builder.toString();
    }
}
