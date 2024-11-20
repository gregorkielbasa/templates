package org.example.aspect;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final MyClass myClass;

    public Runner(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run(String... args) throws Exception {

//        System.out.println("Methode one starts");
//        myClass.methodeOne();
//        System.out.println("Methode one ended");

        System.out.println("Methode two starts");
        var result = myClass.methodeTwo(List.of("test1", "fail1", "test2", "test3", "fail2"));
        System.out.println("Methode two ended");

        System.out.println(result);
    }
}
