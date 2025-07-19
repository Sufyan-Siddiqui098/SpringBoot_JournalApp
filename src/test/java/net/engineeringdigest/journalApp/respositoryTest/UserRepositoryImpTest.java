package net.engineeringdigest.journalApp.respositoryTest;

import net.engineeringdigest.journalApp.repository.UserRepositoryImp;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImpTest {

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Test
    @Disabled
    public void getUserForSentimentAnalysisTest(){
        userRepositoryImp.getUserForSentimentAnalysis();
    }
}
