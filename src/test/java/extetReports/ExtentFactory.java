package extetReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInstance(String framework, String version) {
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo(framework, version);
        extent.setSystemInfo("SO", "Windows 11");
        return extent;
    }
}
