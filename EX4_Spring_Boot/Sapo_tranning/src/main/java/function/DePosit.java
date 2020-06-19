package function;

import Controller.Printer;
import Controller.impl.PrinterConsole;
import Model.Customer;

import java.math.BigDecimal;

/**
 * Gửi tiền đi
 */
public class DePosit {
    private Printer printer = new PrinterConsole();
    public BigDecimal deposit(Customer customer, BigDecimal amount, BigDecimal moneyAtm) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) <= 0) {
            printer.printMessage("Lỗi dữ liệu khi chuyển ");
        }else{
            BigDecimal currentBalance = customer.getBalance();
            customer.setBalance(currentBalance.add(amount));
            moneyAtm = moneyAtm.add(amount);
            printer.printMessage("Chuyển tiền thành công!");
        }
        //Công số tiền vừa gửi vào tài khoản khách hàng
        return moneyAtm;
    }
}
