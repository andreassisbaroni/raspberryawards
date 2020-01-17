package br.com.andrebaroni.raspberryawards.infra.importation;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public abstract class CsvReader<T> implements Serializable {

    public void readCsv() throws IOException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(this.getFile()), this.getSeparator())) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (this.recordIsValid(line).equals(Boolean.TRUE)) {
                    processItem(this.convertObject(line));
                }
            }
        }
    }

    protected abstract InputStream getFile() throws IOException;

    protected abstract char getSeparator();

    protected abstract T convertObject(String[] lineItem);

    protected abstract void processItem(T item);

    protected abstract Boolean recordIsValid(String[] lineItem);
}
