package br.com.andrebaroni.raspberryawards.application;

import br.com.andrebaroni.raspberryawards.domain.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api/awards")
public class AwardsController implements Serializable {

    private AwardService awardService;

    @Autowired
    public AwardsController(AwardService awardService) {
        super();
        this.awardService = awardService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AwardQuery> findAwardsInterval() {
        return new ResponseEntity<>(this.awardService.findWinnerInterval(), HttpStatus.OK);
    }
}
