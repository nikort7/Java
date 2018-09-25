/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 1
 */
public class Time {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListTime exemplar=new ListTime();
        System.out.println("\nFor Array:\n");
        exemplar.test(new ArrayList());
        System.out.println("\nFor List:\n");
        exemplar.test(new LinkedList());
    }
}
    

    
