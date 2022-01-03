package com.amin.PaySlipSeisma.PaySlip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PaySlipController
{
    private final PaySlipService paySlipService;

    @Autowired
    public PaySlipController(PaySlipService paySlipService) {
        this.paySlipService = paySlipService;
    }

    @GetMapping
    public List<PaySlip> getPaySlip()
    {
        return paySlipService.getPaySlip();
    }
}
