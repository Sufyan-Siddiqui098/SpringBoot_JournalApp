package net.engineeringdigest.journalApp.serviceTest;

import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test

    public void sendMailTest(){
        Assertions.assertTrue( emailService.sendEmail("abc098@gmail.com", "Testing Java Send mail", "Hi, kaise hain ap?"));

    }
}
