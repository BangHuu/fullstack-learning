package function;

import Controller.Printer;
import Controller.impl.PrinterConsole;
import Model.Customer;

import java.math.BigDecimal;

public class WithDraw {
    private Printer printer = new PrinterConsole();

    public BigDecimal withDraw(Customer customer, BigDecimal amount, BigDecimal moneyAtm) {
        //trường hợp số tiền muốn rút không hợp lệ
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0) {
            printer.printMessage("Số tiền không hợp lệ!");
        }
        //Trường hợp số tiền muốn rút vượt quá số tiền khách hàng có
        else if (customer.getBalance().compareTo(amount) < 0) {
            printer.printMessage("số tiền muốn rút vượt quá số tiền trong ATM có!");

        }
        //Trường hợp rút hết tiền
        else if (amount.compareTo(moneyAtm) >= 0) {
            printer.printMessage("ATM hết tiền");
        }else{
            //Trừ số tiền có trong ATM và tài khoản người dùng
            BigDecimal currentBalance = customer.getBalance();
            //add cộng, subtract trừ
            customer.setBalance(currentBalance.subtract(amount));
            moneyAtm = moneyAtm.subtract(amount);
            printer.printMessage("Rút tiền thành công!");
        }
        return moneyAtm;
    }
}
