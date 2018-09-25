/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1
 */
public class ListTime {
    void test(List l){
        add(l, 0, 0, 0);
        set(l, 0, 0, 0);
        remove (l, 0, 0, 0);
        clear (l, 0, 0, 0);
    }
    
    void add(List l, long start, long finish, long timeConsumedMillis){
        start = System.nanoTime();
        
        for(int i=0;i<20000;i++){
            l.add(i);
        }
            
        finish = System.nanoTime();
        timeConsumedMillis = finish - start;
        System.out.println("Time of add");
        System.out.println(timeConsumedMillis);
    }
    
    void set(List l, long start, long finish, long timeConsumedMillis){
        start = System.nanoTime();
        
        for(int i=0;i<20000;i++){
            l.set(i,15);
        }
            
        finish = System.nanoTime();
        timeConsumedMillis = finish - start;
        System.out.println("Time of set");
        System.out.println(timeConsumedMillis);
    }
    
    void remove (List l, long start, long finish, long timeConsumedMillis){
        start = System.nanoTime();
        
        for(int i=0;i<20000;i++){
            l.remove(0);
        }
            
        finish = System.nanoTime();
        timeConsumedMillis = finish - start;
        System.out.println("Time of remove");
        System.out.println(timeConsumedMillis);
    }
    
    void clear(List l, long start, long finish, long timeConsumedMillis){
        start = System.nanoTime();
              
        l.clear();
                  
        finish = System.nanoTime();
        timeConsumedMillis = finish - start;
        System.out.println("Time of clear");
        System.out.println(timeConsumedMillis);
    }
}
