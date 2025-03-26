import com.salesrecordsapp.model.SalesRecord;
import com.salesrecordsapp.service.FileService;
import com.salesrecordsapp.service.SalesRecordService;

import java.util.List;


public class SalesDataApplication {

    public static void main(String[] args) {

        FileService fileService = new FileService();
        SalesRecordService salesRecordService = new SalesRecordService(fileService);

        List<SalesRecord> model3Records = salesRecordService.loadSalesRecords("model3.csv");
        salesRecordService.printReport(model3Records, "Model 3");

        List<SalesRecord> modelSRecords = salesRecordService.loadSalesRecords("modelS.csv");
        salesRecordService.printReport(modelSRecords, "Model S");

        List<SalesRecord> modelXRecords = salesRecordService.loadSalesRecords("modelX.csv");
        salesRecordService.printReport(modelXRecords, "Model X");
    }
}
