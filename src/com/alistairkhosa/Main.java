package com.alistairkhosa;

class CommissionEmployee{
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private double grossSales;
    private double commissionRate;

    public CommissionEmployee(String firstName, String lastName,
                              String socialSecurityNumber, double grossSales,
                              double commissionRate){
        if(grossSales < 0.0)
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        if(commissionRate <= 0.0)
            throw new IllegalArgumentException("Commission rate must be > 0");

        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        if(commissionRate <= 0.0)
            throw new IllegalArgumentException("Commission rate must be > 0");

        this.commissionRate = commissionRate;
    }

    public void setGrossSales(double grossSales) {
        if(grossSales < 0.0)
            throw new IllegalArgumentException("Gross sales must be >= 0.0");

        this.grossSales = grossSales;
    }

    public double earnings(){
        return this.getCommissionRate() * this.getGrossSales();
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s%n%s: %s%n%s: %.2f%n%s: %.2f",
                "commission employee", this.getFirstName(), this.getLastName(),
                "social security number",this.getSocialSecurityNumber(),
                "gross sales", this.getGrossSales(),
                "commission rate", this.getCommissionRate());
    }
}

class BasePlusCommissionEmployee extends CommissionEmployee{
    private double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName,
                                      String socialSecurityNumber, double grossSales,
                                      double commissionRate, double baseSalary){
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);

        if(baseSalary < 0.0)
            throw new IllegalArgumentException("Base Salary must be >= 0.0");
        this.baseSalary = baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if(baseSalary < 0.0)
            throw new IllegalArgumentException("Base must be >= 0.0");
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double earnings(){
        return this.getBaseSalary() + super.earnings();
    }

    @Override
    public String toString() {
        return String.format("%s %s%n%s: %.2f","base-salaried", super.toString(),
                "base salary", this.getBaseSalary());
    }
}

public class Main {

    public static void main(String[] args) {
        // assign superclass ref to superclass variable
        CommissionEmployee commissionEmployee = new CommissionEmployee(
                "Alistair", "Khosa","222-111-11",
                50000,.06);

        // assign subclass ref to a subclass variable
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(
                "Ntshuxeko","Malmela","333-222-10",
                42000,.04,300);

        // invoke toString on superclass
        System.out.printf("%s %s:%n%n%s%n%n", "Call CommissionEmployee's toString with superclass ref",
                "to superclass obj", commissionEmployee);

        // invoke toString on subclass
        System.out.printf("%s %s:%n%n%s%n%n", "Call BasePlusCommissionEmployee's toString with subclass ref",
                "to subclass obj", basePlusCommissionEmployee.toString());

        CommissionEmployee commissionEmployee1 = basePlusCommissionEmployee;

        // invoke toString on subclass object using superclass variable
        System.out.printf("%s %s:%n%n%s%n%n", "Call BasePlusCommissionEmployee's toString with superclass",
                "ref to subclass obj", commissionEmployee1.toString());
    }
}

