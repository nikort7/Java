/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemain;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 1
 */
public class TreeMain {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        BinTree <Integer,Character> exemplar;
        exemplar = new BinTree<>(0,'o');
        int flag;
        int addkey;
        char addvalue;
        do {
            System.out.print("\nВыбирете действие:\n"
                        + "1. Добавить.\n"
                        + "2. Удалить. \n"
                        + "3. Вывести.\n"
                        + "4. Выход\n");
            Scanner scan = new Scanner (System.in);
            flag = scan.nextInt();
            switch (flag) {
                case 1:
                    System.out.print("\nВведите ключ и элемент:\n");
                    addkey = scan.nextInt();
                    addvalue = (char)  System.in.read();
                    exemplar.add(addkey,addvalue);
                    break;
                case 2:
                    System.out.print("\nВведите ключ элемента, который надо удалить:\n");
                    addkey = scan.nextInt();
                    Node tmp=exemplar.get(addkey);
                    addkey = (int) tmp.key;
                    exemplar.delete(addkey);
                    System.out.print(tmp.value);
                    break;
                case 3:
                    exemplar.print(exemplar.root);
                    break;
                case 4:
                    break;
                default:
                    System.out.print("\nError!!!:\n");
            }
        }while (flag != 4);
        
       /* exemplar.add(5,'r');
        exemplar.add(2,'e');
        exemplar.add(1,'i');
        exemplar.add(6,'v');
        exemplar.add(4,'q');
        exemplar.add(7,'s');
        Node tmp=exemplar.get(6);
        System.out.print(tmp.value);
        System.out.println();
        exemplar.print(exemplar.root);
        System.out.println();
        exemplar.remove(2);
        exemplar.print(exemplar.root);
        */
    }
    
}
