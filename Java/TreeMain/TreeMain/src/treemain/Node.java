/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemain;

/**
 *
 * @author 1
 * @param <T1>
 * @param <T2>
 */
public class Node<T1 extends Comparable,T2 extends Comparable>  {
        T1 key;
        T2 value;
        Node<T1, T2> left, right;
       
        Node(T1 key, T2 value) {
                this.key = key;
                this.value = value;
        }
    
}
