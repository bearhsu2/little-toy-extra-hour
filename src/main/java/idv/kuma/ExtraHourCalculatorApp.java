package idv.kuma;

import idv.kuma.calcualtor.Calculator;
import idv.kuma.loader.ApplicationFileLoader;
import idv.kuma.loader.PunchFileLoader;
import idv.kuma.vo.ApplicationData;
import idv.kuma.vo.ApprovedData;
import idv.kuma.vo.PunchData;
import idv.kuma.writer.HourFileWriter;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class ExtraHourCalculatorApp {

    List<ApplicationData> applicationDataList;
    List<PunchData> punchDataList;
    List<ApprovedData> approvedDataList;

    public ExtraHourCalculatorApp() {
    }

    public void execute() throws IOException {
        // http://jsefa.sourceforge.net/quick-tutorial.html
        loadData();

        // Calculate legal extra hour
        calculateHours();

        // Output
        output();
    }

    private void loadData() throws IOException {
        // Load extra hour application record
        ApplicationFileLoader applicationFileLoader = new ApplicationFileLoader();
        applicationFileLoader.setFileName("application7.csv");
        applicationFileLoader.loadData();
        applicationDataList = applicationFileLoader.getDataList();

        PunchFileLoader punchFileLoader = new PunchFileLoader();
        punchFileLoader.setFileName("punch77.csv");
        punchFileLoader.loadData();
        punchDataList = punchFileLoader.getDataList();

    }

    private void calculateHours() {
        Calculator calculator = new Calculator(applicationDataList, punchDataList);
        calculator.calculate();
        approvedDataList = calculator.getApprovedDataList();
    }

    private void output() throws IOException {
        HourFileWriter writer = new HourFileWriter(approvedDataList);
        writer.setFileName("hour77.csv");
        writer.writeData();
    }

    public static void main(String[] args) {
        System.out.println("Hello!");

        ExtraHourCalculatorApp app = new ExtraHourCalculatorApp();

        try {
            app.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }


}
