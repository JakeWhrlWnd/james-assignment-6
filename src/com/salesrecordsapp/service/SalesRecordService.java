package com.salesrecordsapp.service;

import com.salesrecordsapp.model.SalesRecord;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesRecordService {

    private final FileService fileService = new FileService();

    public List<SalesRecord> loadSalesRecords(String fileName) {
        return fileService.read(fileName).stream()
                .map(this::parseSalesRecord)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<SalesRecord> parseSalesRecord(String line) {
        try {
            String[] data = line.split(",");
            if (data.length < 2) {
                return Optional.empty();
            }

            String date = data[0].trim();
            int sales = Integer.parseInt(data[1].trim());
            return Optional.of(new SalesRecord(date, sales));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.err.println("Oops, there was an issue parsing the sales record. Issue -> " + e.getMessage());
            return Optional.empty();
        }
    }

    public Map<Integer,Integer> getYearlySales(List<SalesRecord> salesRecords) {
        return salesRecords.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getDate().getYear(),
                        Collectors.summingInt(SalesRecord::getSales)
                ));
    }

    // filterSales()

    // printSales()
}
