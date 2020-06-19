package demo;

import compareIgnorecase.CompareIgnoreCase;
import containsany.ContainsAny;
import countmatches.CountMatches;
import lowercase.LowerCase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import prependIfmissing.PrependIfMissing;
import uppercase.UpperCase;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    Scanner scanner = new Scanner(System.in);
    public void showMenu(){
        System.out.println("Chọn 1 method");
        System.out.println("1.containsAny");
        System.out.println("2.containsIgnoreCase");
        System.out.println("3.countMatches");
        System.out.println("4.appendIfMissing");
        System.out.println("5.uppercase");
        System.out.println("6.lowercase");
    }
    @Override
    public void run(String... args) throws Exception {
        String text="";
        System.out.println("Text : ");
        text = scanner.nextLine();
        String input ="";
        System.out.println("input : ");
        input = scanner.nextLine();
        int choice=-1;
        do {
            showMenu();
            if(scanner.hasNext()){
                choice=scanner.nextInt();

            }
            switch (choice){
                case 1:
                    ContainsAny containsAny = new ContainsAny();
                    containsAny.containsAny(text,input);
                    break;
                case 2:
                    CompareIgnoreCase compareIgnoreCase = new CompareIgnoreCase();
                    compareIgnoreCase.compareignorecase(text,input);
                    break;
                case 3:
                    CountMatches countMatches = new CountMatches();
                    countMatches.countmatches(text,input);
                    break;
                case 4:
                    PrependIfMissing prependIfMissing = new PrependIfMissing();
                    prependIfMissing.prependIfmissing(text,input);
                    break;
                case 5:
                    UpperCase upperCase = new UpperCase();
                    upperCase.uppercase(input);
                    break;
                case 6:
                    LowerCase lowerCase = new LowerCase();
                    lowerCase.lowercase(input);
                    break;
                default:
                    break;
            }
        }while (choice>6);


    }

}
