/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class A {private int id;
    A(int id) {this.id = id;}}

public static void main (String[] args) {
    List<A> list = new ArrayList<>();
    Set<A> set = new HashSet<>();
    A a1 = new A(1); A a2 = new A(1);  A a3 = a1;
    list.add(a1);list.add(a2);list.add(a3);
    set.add(a1);set.add(a2);set.add(a3);
    System.out.println(list.size() + set.size());
}