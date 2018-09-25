/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laba_1;

import java.util.Stack;

/**
 *
 * @author Никита
 */
public class Parser {

    static double parser(String s) {
        Stack ops = new Stack();
        Stack vals = new Stack();

        char[] ch;
        int i = 0;
        ch = s.toCharArray();
        while (ch[i] != ';') {
            switch (ch[i]) {
                case '(':
                    break;
                case '+':
                    ops.push(ch[i]);
                    break;
                case '-':
                    ops.push(ch[i]);
                    break;
                case '*':
                    ops.push(ch[i]);
                    break;
                case '/':
                    ops.push(ch[i]);
                    break;
                //else vals.push(Double.parseDouble(s));
                case ')':
                    char op = (char) ops.pop();
                    double v = Double.parseDouble(vals.pop().toString());
                    switch (op) {
                        case '+':
                            v = Double.parseDouble(vals.pop().toString()) + v;
                            break;
                        case '-':
                            v = Double.parseDouble(vals.pop().toString()) - v;
                            break;
                        case '*':
                            v = Double.parseDouble(vals.pop().toString()) * v;
                            break;
                        case '/':
                            v = Double.parseDouble(vals.pop().toString()) / v;
                            break;
                        default:
                            break;
                    }
                    vals.push(v);
                    break;

                default:
                    vals.push(ch[i]);
                    break;
            }
            i++;
        }
        return Double.parseDouble(vals.pop().toString());
    }

}
