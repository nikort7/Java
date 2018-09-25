/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba_2;

import java.util.Random;
import java.util.Scanner;


public class Laba_2
{

    private static int Update(int index, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int Find(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private final MyArray ma;

    public Laba_2() {
        this.ma = new MyArray();
    }
    
    public static void main(String[] args)
    {
        MyArray ma = new MyArray();
                
        int n;
        Random r = new Random(); 
        int key;
        do
        {
            System.out.print("\nВыбирете действие:\n"
                    + "1. Добавить число в массив.\n"
                    + "2. Поиск по индексу. \n"
                    + "3. Изменение.\n"
                    + "4. Удаление.\n"
                    + "5. Выход\n");
            Scanner scan = new Scanner (System.in);
            int index;
            key = scan.nextInt();

            switch (key){
                case 1:
                    n = r.nextInt(10);
                    ma.add(n);
                    break;
                case 2:
                    if (ma.isEmpty())
                        System.out.print("Массив пустой.\n");
                    else
                    {
                        System.out.print("Введите индекс необходимого элемента \n");
                        index = scan.nextInt();
                        System.out.print("Найденный элемент: "+ ma.find(index) +".\n");
                    }
                    break;
                case (3):
                    if (ma.isEmpty())
                        System.out.print("Массив пустой.\n");
                    else
                    {
                        System.out.print("Введите индекс элемента, который надо изменить.\n");
                        index = scan.nextInt();
                        n = r.nextInt(10);
                        ma.update(index, n);
                    }
                    break;
                case (4):
                    if (ma.isEmpty())
                        System.out.print("Массив пустой.\n");
                    else
                    {
                        System.out.print("Введите индекс элемента, который надо удалить.\n");
                        index = scan.nextInt();
                        ma.delete(index);
                    }
                    break;
                case (5):
                    break;
                default:
                     System.out.print("Введена неверная команда.");
                     break;
             }
        }while (key != 5);
                 
    }

    private static void Scanner(int key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
