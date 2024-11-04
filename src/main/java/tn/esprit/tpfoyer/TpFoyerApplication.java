package tn.esprit.tpfoyer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class TpFoyerApplication {


 @GetMapping("/")
    public String getmessage()
    {
        return "good job nermine your java app is deployed with docker successfully ";
    }

    public static void main(String[] args) {
        SpringApplication.run(TpFoyerApplication.class, args);
    }

}
