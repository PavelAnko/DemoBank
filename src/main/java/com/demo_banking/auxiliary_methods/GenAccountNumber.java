package com.demo_banking.auxiliary_methods;
import java.util.Random;

public class GenAccountNumber {
    public static int generateAccountNumber(){
        int accountNumber;
        Random random = new Random();
        int bound = 1000;
        accountNumber = bound * (random.nextInt(9000)+bound);
        return accountNumber;
    }
}
