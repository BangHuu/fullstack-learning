package Controller.impl;

import Controller.Printer;
import Model.Customer;


public class PrinterConsole implements Printer {
    @Override
    public void printCustoner(Customer customer) {
        System.out.println("ID của khách hàng : " + customer.getAcctNo() + ", Số tiền còn lại: " + customer.getBalance().toString());
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
