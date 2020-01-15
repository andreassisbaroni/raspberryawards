package br.com.andrebaroni.raspberryawards.infra.importation;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class CsvReader<T> implements Serializable {

    protected static final int YEAR_COLUMN = 0;
    protected static final int TITLE_COLUMN = 1;
    protected static final int STUDIOS_COLUMN = 2;
    protected static final int PRODUCERS_COLUMN = 3;
    protected static final int WINNER_COLUMN = 4;

    public void readCsv() {
        try {
            CSVReader reader = new CSVReader(new FileReader(this.getFilePath()), this.getSeparator());
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (StringUtils.isNumeric(line[YEAR_COLUMN])) {
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
}
