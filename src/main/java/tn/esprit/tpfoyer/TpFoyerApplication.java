package tn.esprit.tpfoyer;

//comment added
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//bleda
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@RestController
public class TpFoyerApplication {
    @GetMapping("/")
    public String getmessage()
    {
        return "good job mohmed malek turki your java app is deployed with docker successfully ";
    }
    public static void main(String[] args) {
 
        SpringApplication.run(TpFoyerApplication.class, args);
    }

}
