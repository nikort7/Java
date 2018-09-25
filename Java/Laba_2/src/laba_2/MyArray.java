
package laba_2;

import java.util.Random;
import java.util.Scanner;

public class MyArray {
    
    int[] body;
    private int currsize;

    public MyArray() {
        this.body = new int[10];
    }
    
    public boolean isEmpty()
    {
        if (currsize == 0)
            return true;
        else return false;
    }
    
    public void add(int n)
    {
       if (currsize < body.length)
       {
           body[currsize] = n;
           currsize++;
       }
       else
       {
           int[] mas = new int [body.length*2];
           for (int i = 0; i < currsize; i++)
               mas[i] = body[i];
           mas[currsize] = n;
           body = mas;
       }
       
       for (int i = 0; i < currsize; i++)
               System.out.print(body[i]);
    }

    public int find (int index)
    {
        return body[index];
    }
    
    public void update(int index, int n)
    {
        if (index > body.length)
            System.out.print("Error");
        else
        {
            body[index] = n;
            for (int i = 0; i < currsize; i++)
                System.out.print(body[i]);
        }
    }
    
    public void delete(int index)
    {
        currsize--;
        int[] new_mas_del = new int [body.length - 1];
        for (int i = 0; i < index; i++)
            new_mas_del[i] = body[i];
        for (int i = index; i < currsize; i++)
            new_mas_del[i] = body[i+1];
                    
        body = new_mas_del;

        for (int i = 0; i < currsize; i++)
            System.out.print(body[i]);
    }
    
}