/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package сплайны;

import static java.lang.Math.pow;
import java.util.Scanner;

/**
 *
 * @author Никита
 */

class Splain {
    private final
        double
            A,
            B;
    
    public
        int 
            k,
            n;
        double
                a,
                b;
        double[]
                x,
                y,
                f;
        double[]   //коэффициенты полинома
                BB,
                CC,
                DD;

    Splain() {
        this.A = 3;
        this.B = 3;
    }
    
    double f(double x)
    {
        return x*x*x;
    }
    
    double polinom (double point, double[] x, int i, double[] y, double[] b, double[] c, double[] d)
    {
        return y[i] + b[i]*(point - x[i]) + c[i]*pow((point - x[i]), 2) + d[i]*pow((point - x[i]), 3);
    }
        
    double[] solveMatrix (int n, double[] a, double[] c, double[] b, double[] f)
    {
        /*
            * n - число уравнений (строк матрицы)
            * b - диагональ, лежащая над главной (нумеруется: [0;n-2])
            * c - главная диагональ матрицы A (нумеруется: [0;n-1])
            * a - диагональ, лежащая под главной (нумеруется: [1;n-1])
            * f - правая часть (столбец)
            * x - решение, массив x будет содержать ответ
         */

        double[] x = new double[n];
        double m;

        for (int i = 1; i < n; i++)
        {
            m = a[i]/c[i-1];
            c[i] = c[i] - m*b[i-1];
            f[i] = f[i] - m*f[i-1];
        }

        x[n-1] = f[n-1]/c[n-1];
        //System.out.print("\nВектор x "+x[n-1]); 

        for (int i = n - 2; i >= 0; i--)
        {
            x[i]=(f[i]-b[i]*x[i+1])/c[i];
            //System.out.print(" "+x[i]);
        }

        return x;
    }

    double[] solution (int n, double[] y, double[] f, double h)
    {
        double[]
                a = new double[n],
                b = new double[n],
                c = new double[n];

        for (int i = 0; i < n-1; i++)
        {
            c[i] = 1;
            a[i+1] = 1;
            b[i+1] = 4;
        }
        b[0] = 2;
        b[n-1] = 7;//1;
        a[n-1] = 2;//2;
        
        System.out.print("\nc:");
        for (int i = 0; i < n; i++)
        System.out.print(c[i]+" ");

        System.out.print("\nb:");
        for (int i = 0; i < n; i++)
        System.out.print(b[i]+" ");
        
        System.out.print("\na:");
        for (int i = 0; i < n; i++)
        System.out.print(a[i]+" ");
        
        double [] sol = new double[n];

        sol = solveMatrix(n, a, b, c, f);

        return sol;
    }
    
    void main_kod ()
    {
        int key = 1;
        do
        {
            System.out.print("Введите границы отрезка интерполирования [a,b]: ");

            Scanner scan = new Scanner (System.in);
            a = scan.nextDouble();
            b = scan.nextDouble();

            System.out.print("Введите число разбиений k: ");
            k = scan.nextInt();
            n = k+1;

            x = new double[n];
            y = new double[n];

            for (int i = 0; i < n; i++)
            {
                x[i] = a + (b-a)*(i)/(n-1);
                y[i] = f(x[i]);
            }
            
            System.out.print("Узлы интерполяции:\n");
            for (int i = 0; i < n; i++)
                System.out.print("x["+i+"] = "+x[i]+"\n");
           
            f = new double[n-1]; // вектор правой части

            double h = x[1] - x[0];

            f[0] = 3 * ((y[1] - y[0])/h - A)/h;
            int i = 1;
            for ( ;i < n-2; i++)
                f[i] = 3 * (y[i-1] - 2*y[i] + y[i+1])/(h*h);
            f[i] = 3*( (3*y[i+1] - 5*y[i] + 2*y[i-1])/(h*h) - B/h);
          
            BB = new double[n-1];
            CC = new double[n-1];
            DD = new double[n-1];
            
            CC = solution(n-1, y, f, h);

            for (i = 0; i < n-2; i++)
            {
                DD[i] = (CC[i+1] - CC[i])/(3*h);
                BB[i] = (y[i+1]-y[i])/h - CC[i]*h - DD[i]*h*h;
            }
            BB[i] = BB[i-1] + 2*CC[i-1]*h + 3*DD[i-1]*h*h;
            DD[i] = (B - BB[i] - 2*CC[i]*h)/(3*h*h);

            double point;

            System.out.print("\n Программа готова к работе.");
            
            System.out.print("\n Введите число х, значение которого надо почитать: \n");
            point = scan.nextDouble();
            if (point < a || point >= b)
            {
                System.out.print("\n Ошибка: выход за заданный отрезок. \n");
                return;
            }
            else
            {
                System.out.print("\n Точное значение функции: "+f(point)+"\n");
                int index = 0;
                for (i = 0; i < n-1; i++)
                {
                    if (point > x[i] && point <= x[i+1])
                        index = i;
                }
                System.out.print("\n Приближенное значение функции: "+
                            polinom (point, x, index, y, BB, CC, DD)+"\n");

                System.out.print("\n Погрешность "+(f(point) - polinom (point, x, index, y, BB, CC, DD)));

                System.out.print("\n Хотите продолжить: да (1), нет (0): ");
                key = scan.nextInt();
            }
        }while(key == 1);
    }
}
