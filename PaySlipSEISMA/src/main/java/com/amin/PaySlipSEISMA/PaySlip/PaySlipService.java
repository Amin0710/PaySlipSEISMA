package com.amin.PaySlipSeisma.PaySlip;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaySlipService
{
    private final PaySlipRepository paySlipRepository;

    public PaySlipService(PaySlipRepository paySlipRepository) {
        this.paySlipRepository = paySlipRepository;
    }
    public List<PaySlip> getPaySlip()
    {
        return paySlipRepository.findAll();
    }

}
