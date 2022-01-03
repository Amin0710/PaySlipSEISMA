package com.amin.PaySlipSeisma.PaySlip;

//import com.amin.PaySlipSeisma.ResultPaySlip.ResultPaySlip;
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
//            ResultPaySlip result_david_ruud = new ResultPaySlip(
//                    250,
//                    525,
//                    525,
//                    26
//            );
            PaySlip ryan_chen = new PaySlip(
                    "Ryan",
                    "Chen",
                    120000,
                    1,
                    0.1
            );
//            ResultPaySlip result_ryan_chen= new ResultPaySlip(
//                    250,
//                    525,
//                    525,
//                    26);
            repository.saveAll(
                    List.of(david_ruud, ryan_chen)
            );
        };
    }
}
