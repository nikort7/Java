package Laba_1;

public class Laba_1 
{
   
    
    public static void main(String[] args)
    {
       String s = "(((7*(4-1))*(3+2))-5);";
       
       System.out.print("Решение уравнения "+s+" = ");
       double a = Parser.parser(s);
       
       System.out.print(a);
    }
}
