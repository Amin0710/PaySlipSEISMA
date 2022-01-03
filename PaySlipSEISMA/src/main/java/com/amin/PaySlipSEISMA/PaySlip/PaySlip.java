package com.amin.PaySlipSeisma.PaySlip;


import javax.persistence.*;


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
    private String firstName;
    private String lastName;
    private Integer annualSalary;
    private Integer paymentMonth;
    private Double superRate;
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
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentMonth=" + paymentMonth +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", superannuation=" + superannuation +
                ", netIncome=" + netIncome +
                '}';
    }
}
