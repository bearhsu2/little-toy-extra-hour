package idv.kuma.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class PunchData {

    //員工編號,部門,姓名,日期,班別名稱,上班,上班地點,下班,下班地點,出勤狀況,遲到分鐘數,早退分鐘數,說明,外出登記,［*］代表簽核中

    private int employeeId;

    private String name;

    private LocalDate punchDate;

    private LocalTime checkinTime;

    private LocalTime checkoutTime;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(LocalDate punchDate) {
        this.punchDate = punchDate;
    }

    public LocalTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }
}
