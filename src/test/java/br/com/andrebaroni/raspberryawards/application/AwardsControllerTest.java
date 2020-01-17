package br.com.andrebaroni.raspberryawards.application;

import br.com.andrebaroni.raspberryawards.ControllerTest;
import br.com.andrebaroni.raspberryawards.application.query.AwardQuery;
import br.com.andrebaroni.raspberryawards.application.query.AwardWinnerQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AwardsControllerTest extends ControllerTest {

    @Autowired
    public AwardsControllerTest(TestRestTemplate testRestTemplate) {
        super(testRestTemplate);
    }

    @Test
    public void findAwardsInterval() {
        ResponseEntity entity = super.getTestRestTemplate().getForEntity(this.getUrl(), AwardQuery.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);

        AwardQuery awardQuery = (AwardQuery) entity.getBody();
        assertNotNull(awardQuery);

        assertNotNull(awardQuery.getMax());
        assertEquals(awardQuery.getMax().size(), 1);

        AwardWinnerQuery maxWinner = awardQuery.getMax().stream().findFirst().orElse(null);
        assertNotNull(maxWinner);
        assertEquals(maxWinner.getProducer(), "Matthew Vaughn");
        assertEquals(maxWinner.getInterval(), 13L);
        assertEquals(maxWinner.getPreviousWin(), 2002L);
        assertEquals(maxWinner.getFollowingWin(), 2015L);

        assertNotNull(awardQuery.getMin());
        assertEquals(awardQuery.getMin().size(), 1);


        AwardWinnerQuery minWinner = awardQuery.getMin().stream().findFirst().orElse(null);
        assertNotNull(minWinner);
        assertEquals(minWinner.getProducer(), "Joel Silver");
        assertEquals(minWinner.getInterval(), 1L);
        assertEquals(minWinner.getPreviousWin(), 1990L);
        assertEquals(minWinner.getFollowingWin(), 1991L);
    }

    @Override
    protected String getResource() {
        return "awards";
    }

}