package com.salesrecordsapp.service;

import com.salesrecordsapp.model.SalesRecord;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class SalesRecordService {

    private final FileService fileService;

    public SalesRecordService(FileService fileService) {
        this.fileService = fileService;
    }

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

    public YearMonth getBestMonth(List<SalesRecord> salesRecords) {
        return salesRecords.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(SalesRecord::getSales))
                .map(SalesRecord::getDate)
                .orElseThrow(() -> new IllegalStateException("No sales records available"));
    }

    public YearMonth getWorstMonth(List<SalesRecord> salesRecords) {
        return salesRecords.stream()
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(SalesRecord::getSales))
                .map(SalesRecord::getDate)
                .orElseThrow(() -> new IllegalStateException("No sales records available"));
    }

    public void printReport(List<SalesRecord> salesRecords, String vehicleModel) {
        System.out.println(vehicleModel + " Yearly Sales Report");
        System.out.println("---------------------------");

        Map<Integer,Integer> yearlySales = getYearlySales(salesRecords);

        yearlySales.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("%d -> $%,.2f%n", entry.getKey(), (double) entry.getValue()));

        System.out.println();

        YearMonth bestMonth = getBestMonth(salesRecords);
        YearMonth worstMonth = getWorstMonth(salesRecords);

        System.out.println("The best month for " + vehicleModel + " was: " + (bestMonth != null ? bestMonth : "N/A"));
        System.out.println("The worst month for " + vehicleModel + " was: " + (worstMonth != null ? worstMonth : "N/A"));

        System.out.println();
    }
}
