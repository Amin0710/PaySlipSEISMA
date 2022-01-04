package com.amin.PaySlipSeisma.PaySlip;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Month;
import java.util.Calendar;


@Entity
@Table
public class PaySlip
{
    @Id
    @SequenceGenerator(
            name= "paySlip_sequence",
            sequenceName = "paySlip_sequence",
            allocationSize =1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "paySlip_sequence"
    )
    private long id;
    @NotBlank(message = "firstName is mandatory")
    @Size(min = 2)
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    @Size(min = 2)
    private String lastName;
    @NotBlank(message = "annualSalary is mandatory")
    @Positive
    private Integer annualSalary;
    @NotBlank(message = "paymentMonth is mandatory")
    @Range(min = 1,max = 12)
    private Integer paymentMonth;
    @NotBlank(message = "superRate is mandatory")
    private Double superRate;
    @Transient
    private String fromDate;
    @Transient
    private String toDate;
    @Transient
    private Integer grossIncome;
    @Transient
    private Integer incomeTax;
    @Transient
    private Integer superannuation;
    @Transient
    private Integer netIncome;

    public PaySlip() {}

    public PaySlip(String firstName, String lastName, Integer annualSalary, Integer paymentMonth, Double superRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.paymentMonth = paymentMonth;
        this.superRate = superRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    public Integer getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(Integer paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public Double getSuperRate() {
        return superRate;
    }

    public void setSuperRate(Double superRate) {
        this.superRate = superRate;
    }

    public String getFromDate()
    {
        int x = this.getPaymentMonth();
        return ("01 " + Month.of(x));
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        int x = this.getPaymentMonth();
        int y=30;
        if (x==1||x==3||x==5||x==7||x==8||x==10||x==12)
        {
            y=31;
        }else if (x==2)
        {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            boolean leap = false;

            if (year % 4 == 0) // if the year is divided by 4
            {
                if (year % 100 == 0) // if the year is century
                {
                    if (year % 400 == 0) // then it is a leap year// if year is divided by 400
                        leap = true;
                    else
                        leap = false;
                }
                else
                    leap = true;// if the year is not century
            }
            else
                leap = false;

            if (leap){y=29;}else{y=28;}

        }
        return (y+" " + Month.of(x));
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Integer getGrossIncome()
    {
        int x = this.getAnnualSalary();
        return Math.round(x/12);
    }

    public void setGrossIncome(Integer grossIncome) {
        this.grossIncome = grossIncome;
    }

    public Integer getIncomeTax()
    {
        int x = this.getAnnualSalary();
        double tax=0;
        if(x<=18200)
            tax=0;
        else if(x<=37000)
            tax=0.19*(x-18200);
        else if(x<=87000)
            tax=3572+(0.325*(x-37000));
        else if(x<=180000)
            tax=19822+(0.37*(x-87000));
        else
            tax=54232+(0.45*(x-180000));

        return Math.toIntExact(Math.round(tax / 12));
    }

    public void setIncomeTax(Integer incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Integer getSuperannuation()
    {
        int x = this.getAnnualSalary();
        double y= this.getSuperRate();
        return Math.toIntExact((Math.round(x/12 * y)));
    }

    public void setSuperannuation(Integer superannuation) {
        this.superannuation = superannuation;
    }

    public Integer getNetIncome()
    {
        int x = this.getAnnualSalary();
        double tax=0;
        if(x<=18200)
            tax=0;
        else if(x<=37000)
            tax=0.19*(x-18200);
        else if(x<=87000)
            tax=3572+(0.325*(x-37000));
        else if(x<=180000)
            tax=19822+(0.37*(x-87000));
        else
            tax=54232+(0.45*(x-180000));
        return (Math.round(x/12)-Math.toIntExact(Math.round(tax / 12)));
    }

    public void setNetIncome(Integer netIncome) {
        this.netIncome = netIncome;
    }

    @Override
    public String toString() {
        return "PaySlip{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentMonth=" + paymentMonth +
                ", superRate=" + superRate +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", superannuation=" + superannuation +
                ", netIncome=" + netIncome +
                '}';
    }
}
