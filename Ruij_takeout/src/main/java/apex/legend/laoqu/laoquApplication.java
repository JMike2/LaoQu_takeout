package apex.legend.laoqu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
public class laoquApplication {
    public static void main(String[] args) {
        SpringApplication.run(laoquApplication.class,args);
        log.info("start success");
    }
}
