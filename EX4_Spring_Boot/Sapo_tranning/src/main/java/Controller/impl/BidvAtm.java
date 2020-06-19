package Controller.impl;

import Controller.Atm;
import Controller.Printer;
import Model.Customer;
import function.DePosit;
import function.WithDraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class BidvAtm implements Atm {
    @Autowired
    BigDecimal moneyAtm ;
    private Printer printer;
    private WithDraw withDraw;
    private DePosit deposit;

    public BidvAtm(Printer printer, WithDraw withDraw, DePosit deposit) {
        this.printer = printer;
        this.withDraw = withDraw;
        this.deposit = deposit;
    }

    @Override
    public void withDraw(Customer customer, BigDecimal amount) {
        moneyAtm=withDraw.withDraw(customer,amount,moneyAtm);
        printCurrentMoney();
    }

    @Override
    public void printCurrentMoney() {
        printer.printMessage("Current ATM money is " + moneyAtm.toString());
    }

    @Override
    public void deposit(Customer customer, BigDecimal amount) {
        moneyAtm = deposit.deposit(customer,amount,moneyAtm);
        printCurrentMoney();
    }

    @Override
    public void displayCustomerInfo(Customer customer) {
            printer.printCustoner(customer);
    }
}
