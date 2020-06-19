package Main;

import Controller.Atm;
import Controller.Printer;
import Controller.impl.PrinterConsole;
import Model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    Scanner sc = new Scanner(System.in);
    @Value("${money.money")
    BigDecimal moneyAtm;

    private final Atm atm;

    public DemoApplication(Atm atm) {
        this.atm = atm;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
    public Customer Text(){
        System.out.println("Nhập thông tin của khách hàng");
        System.out.println("Nhập id khách hàng");
        String id = sc.nextLine();
        System.out.println("Nhập mã pin ");
        String pin = sc.nextLine();
        System.out.println("Nhập số dư tài khoản");
        BigDecimal balance = sc.nextBigDecimal();
        Customer customer = new Customer(id,pin,balance);
        return customer;
    }
    public  void ShowMenu(){
        System.out.println("Nhập lựa chọn của bạn :");
        System.out.println("Xem thông tin");
        System.out.println("Gửi tiền");
        System.out.println("Rút tiền");
    }
    public void menu(Customer customer,Atm atm){
        Printer printer = new PrinterConsole();
        while (true) {
            try {
                 ShowMenu();
                int chon = sc.nextInt();
                if(0>=chon || 3<chon)
                {
                    System.err.println("Nhập từ 1-3");
                    continue;
                }
                sc.nextLine();
                switch (chon) {
                    case 1:
                        atm.displayCustomerInfo(customer);
                        break;
                    case 2:
                        printer.printMessage("Nhập vào số tiền bạn muốn rút: ");
                        BigDecimal tienrut = sc.nextBigDecimal();
                        atm.withDraw(customer, tienrut);
                        break;
                    case 3:
                        System.out.println("Nhập số tiền bạn muốn gửi: ");
                        BigDecimal tiengui = sc.nextBigDecimal();
                        atm.deposit(customer, tiengui);
                        break;
                }

            } catch (InputMismatchException ex) {
                System.err.println("Vui lòng nhập dữ liệu hợp lệ!");
            }
            sc.nextLine();
            System.out.println("Tiếp tục bạn nhập Y?");
            String kt=sc.nextLine();
            if("Y".equalsIgnoreCase(kt)){
                continue;
            }
            else{
                System.out.println("Cảm ơn bạn đã sử dụng dịch vụ!");
                break;
            }

        }
    }
    @Override
    public void run(String... args) throws Exception {
        Printer printer = new PrinterConsole();

    }
}
