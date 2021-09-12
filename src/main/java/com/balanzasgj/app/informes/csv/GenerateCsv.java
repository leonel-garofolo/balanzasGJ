package com.balanzasgj.app.informes.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class GenerateCsv {
    private boolean showHeader;
    protected String path;
    protected String nameFile;
    protected List<List<String>> rowsData;
    private String[] headers;
    private CSVPrinter csvPrinter;

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    protected void setRowsData(List<List<String>> rowsData) {
        this.rowsData = rowsData;
    }

    protected void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public GenerateCsv build() {
        if(this.path == null){
            throw new NullPointerException("Please add path of directory to export csv");
        }
        if(this.nameFile == null){
            throw new NullPointerException("Please add name of file to export csv");
        }

        BufferedWriter writer = null;
        try {
            CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);
            writer = Files.newBufferedWriter(Paths.get(this.path + "\\" + this.nameFile));
            this.csvPrinter = new CSVPrinter(writer, format);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void export(){
        try{
            for (List<String> row: rowsData) {
                csvPrinter.printRecord(row.toArray());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
