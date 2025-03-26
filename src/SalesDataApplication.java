import com.salesrecordsapp.model.SalesRecord;
import com.salesrecordsapp.service.FileService;
import com.salesrecordsapp.service.SalesRecordService;

import java.util.*;


public class SalesDataApplication {

    public static void main(String[] args) {

        FileService fileService = new FileService();
        SalesRecordService salesRecordService = new SalesRecordService(fileService);

        String[] vehicleModels = {"Model 3", "Model S", "Model X"};
        String[] fileNames = {"model3.csv", "modelS.csv", "modelX.csv"};

        for (int i = 0; i < vehicleModels.length; i++) {
            List<SalesRecord> salesRecords = salesRecordService.loadSalesRecords(fileNames[i]);
            salesRecordService.printReport(salesRecords, vehicleModels[i]);
        }
    }
}
