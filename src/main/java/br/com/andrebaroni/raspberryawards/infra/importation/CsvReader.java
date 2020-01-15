package br.com.andrebaroni.raspberryawards.infra.importation;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class CsvReader<T> implements Serializable {

    public void readCsv() {
        try {
            CSVReader reader = new CSVReader(new FileReader(this.getFilePath()), this.getSeparator());
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (this.recordIsValid(line).equals(Boolean.TRUE)) {
                    processItem(this.convertObject(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getFilePath();

    protected abstract char getSeparator();

    protected abstract T convertObject(String[] lineItem);

    protected abstract void processItem(T item);

    protected abstract Boolean recordIsValid(String[] lineItem);
}
