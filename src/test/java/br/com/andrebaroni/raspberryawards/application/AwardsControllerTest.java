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
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        AwardQuery awardQuery = (AwardQuery) entity.getBody();
        assertNotNull(awardQuery);

        assertNotNull(awardQuery.getMax());
        assertEquals(1, awardQuery.getMax().size());

        AwardWinnerQuery maxWinner = awardQuery.getMax().stream().findFirst().orElse(null);
        assertNotNull(maxWinner);
        assertEquals("Matthew Vaughn", maxWinner.getProducer());
        assertEquals(49L, maxWinner.getInterval());
        assertEquals( 2015L, maxWinner.getPreviousWin());
        assertEquals(2064L, maxWinner.getFollowingWin());

        assertNotNull(awardQuery.getMin());
        assertEquals(1, awardQuery.getMin().size());


        AwardWinnerQuery minWinner = awardQuery.getMin().stream().findFirst().orElse(null);
        assertNotNull(minWinner);
        assertEquals("Joel Silver", minWinner.getProducer());
        assertEquals(1L, minWinner.getInterval());
        assertEquals( 1990L, minWinner.getPreviousWin());
        assertEquals(1991L, minWinner.getFollowingWin());
    }

    @Override
    protected String getResource() {
        return "awards";
    }

}