package model;

public class tester {
    public static void main(String[] args) {
        customer customer=new customer("firstName","lastName","j@domain.com");
        System.out.println(customer);
        customer customer1=new customer("firstName","lastName","email");
        System.out.println(customer1);
    }
}
