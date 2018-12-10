package com.oppo;

import com.oppo.batch.InsertHoliday;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkatingApplication {

    public static void main(String[] args) {
        //InsertHoliday.main(args);

        SpringApplication.run(SkatingApplication.class, args);
    }
}
