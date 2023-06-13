package duo.cmr.dysha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DyshaApplication {
    // TODO: 03.02.22 Fetch all data in an .excel oder .csv file while server ist running

    public static void main(String[] args) {
        SpringApplication.run(DyshaApplication.class, args);
    }

    /*@Autowired
   private EmailService emailService;
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        emailService.send("silatsamdylan@gmail.com", "test", "Yo bro que ca a marché! cest nice");
    }*/
}
