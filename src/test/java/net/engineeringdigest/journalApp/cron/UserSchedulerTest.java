package net.engineeringdigest.journalApp.cron;

import net.engineeringdigest.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    @Disabled
    public void fetchUserAndSendSentimentAnalysisMailTest(){
        userScheduler.fetchUserAndSendSentimentAnalysisMail();
    }

}
