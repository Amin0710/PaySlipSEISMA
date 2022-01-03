package com.amin.PaySlipSeisma.PaySlip;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class PaySlipConfig
{
    @Bean
    CommandLineRunner commandLineRunner(PaySlipRepository repository) {
        return args ->
        {
            PaySlip david_ruud = new PaySlip(
                    "David",
                    "Rudd",
                    60050,
                    1,
                    0.09
            );
            PaySlip ryan_chen = new PaySlip(
                    "Ryan",
                    "Chen",
                    120000,
                    1,
                    0.1
            );
            repository.saveAll(
                    List.of(david_ruud, ryan_chen)
            );
        };
    }
}
