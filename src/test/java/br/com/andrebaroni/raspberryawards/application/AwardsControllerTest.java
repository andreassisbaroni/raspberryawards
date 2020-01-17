package br.com.andrebaroni.raspberryawards.application;

import br.com.andrebaroni.raspberryawards.ControllerTest;
import br.com.andrebaroni.raspberryawards.application.query.AwardQuery;
import br.com.andrebaroni.raspberryawards.application.query.AwardWinnerQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AwardsControllerTest extends ControllerTest {

    @Autowired
    public AwardsControllerTest(TestRestTemplate testRestTemplate) {
        super(testRestTemplate);
    }

    @Test
    void findAwardsInterval() {
        ResponseEntity entity = super.getTestRestTemplate().getForEntity(this.getUrl(), AwardQuery.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);

        AwardQuery awardQuery = (AwardQuery) entity.getBody();
        Assertions.assertNotNull(awardQuery);

        Assertions.assertNotNull(awardQuery.getMax());
        Assertions.assertEquals(awardQuery.getMax().size(), 1);

        AwardWinnerQuery maxWinner = awardQuery.getMax().stream().findFirst().orElse(null);
        Assertions.assertNotNull(maxWinner);
        Assertions.assertEquals(maxWinner.getProducer(), "Matthew Vaughn");
        Assertions.assertEquals(maxWinner.getInterval(), 13L);
        Assertions.assertEquals(maxWinner.getPreviousWin(), 2002L);
        Assertions.assertEquals(maxWinner.getFollowingWin(), 2015L);

        Assertions.assertNotNull(awardQuery.getMin());
        Assertions.assertEquals(awardQuery.getMin().size(), 1);


        AwardWinnerQuery minWinner = awardQuery.getMin().stream().findFirst().orElse(null);
        Assertions.assertNotNull(minWinner);
        Assertions.assertEquals(minWinner.getProducer(), "Joel Silver");
        Assertions.assertEquals(minWinner.getInterval(), 1L);
        Assertions.assertEquals(minWinner.getPreviousWin(), 1990L);
        Assertions.assertEquals(minWinner.getFollowingWin(), 1991L);
    }

    @Override
    protected String getResource() {
        return "awards";
    }

}