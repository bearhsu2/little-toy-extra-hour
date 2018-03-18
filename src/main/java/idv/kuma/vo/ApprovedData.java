package idv.kuma.vo;

import java.time.LocalDate;

public class ApprovedData {
    private String sn;
    private int employeeId;
    private LocalDate date;
    private String type;
    private double appliedHours;
    private double realHours;
    private double weightedHours;


    public ApprovedData(ApplicationData data) {
        this.sn = data.getSn();
        this.type = data.getType();
        this.employeeId = data.getEmployeeId();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getSn() {
        return sn;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public double getAppliedHours() {
        return appliedHours;
    }

    public void setAppliedHours(double appliedHours) {
        this.appliedHours = appliedHours;
    }

    public double getRealHours() {
        return realHours;
    }

    public void setRealHours(double realHours) {
        this.realHours = realHours;
    }

    public double getWeightedHours() {
        return weightedHours;
    }

    public void setWeightedHours(double weightedHours) {
        this.weightedHours = weightedHours;
    }
}
