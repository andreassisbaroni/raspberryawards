package br.com.andrebaroni.raspberryawards.infra.inicialization;

import br.com.andrebaroni.raspberryawards.infra.importation.CsvReader;
import br.com.andrebaroni.raspberryawards.infra.importation.raspberryawards.RaspberryAwardsDataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final CsvReader csvReader;

    @Autowired
    public ApplicationStartup(RaspberryAwardsDataReader raspberryAwardsDataReader) {
        super();
        this.csvReader = raspberryAwardsDataReader;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            this.csvReader.readCsv();
        } catch (IOException e) {
            throw new LoadMovieDataException();
        }
    }
}
