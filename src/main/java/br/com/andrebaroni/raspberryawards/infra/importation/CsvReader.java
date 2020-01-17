package br.com.andrebaroni.raspberryawards.infra.importation;

import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class CsvReader<T> implements Serializable {

    public void readCsv() throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(new ClassPathResource(this.getFilePath()).getFile()), this.getSeparator())) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (this.recordIsValid(line).equals(Boolean.TRUE)) {
                    processItem(this.convertObject(line));
                }
            }
        }
    }

    protected abstract String getFilePath();

    protected abstract char getSeparator();

    protected abstract T convertObject(String[] lineItem);

    protected abstract void processItem(T item);

    protected abstract Boolean recordIsValid(String[] lineItem);
}
