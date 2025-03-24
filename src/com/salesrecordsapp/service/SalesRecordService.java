package com.salesrecordsapp.service;

import com.salesrecordsapp.model.SalesRecord;

import java.util.ArrayList;
import java.util.List;

public class SalesRecordService {

    public List<SalesRecord> salesRecords = new ArrayList<>();
    private final FileService fileService = new FileService();

    public List<SalesRecord> read(String fileName) {
        List<String> lines = fileService.read(fileName);
        for (String line : lines) {
            if (line != null && !line.isEmpty()) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    try {
                        String date = data[0].trim();
                        int sales = Integer.parseInt(data[1].trim());

                        salesRecords.add(new SalesRecord(date, sales));

                    } catch (Exception e) {
                        System.out.println("Oops, there was an error reading the sales record. Issue -> " + e.getMessage());
                    }
                } else {
                    System.out.println("Oops, the data is invalid. Issue -> " + line);
                }
            }
        }
        return salesRecords;
    }

    // filterSales()

    // printSales()
}
