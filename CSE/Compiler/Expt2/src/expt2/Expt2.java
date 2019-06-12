/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expt2;
import java.util.*;
/**
 *
 * @author UR16CS089
 */
public class Expt2 
{
    static int check(String x, String table[][])
    {
        int flag=0;
        for(int i=0; i< 10; i++)
            {
                for(int k=0; k< 6; k++)
                {
                    if(x.equals(table[i][k])){
                        flag=1;
                    }
                }
            }
        if(flag==0)
        {
            return 0; // not present
        }
        else
            return 1;   //present
    }
    public static void main(String[] args)
    {
        Scanner s= new Scanner(System.in);
        String table[][] =new String[10][6];
        int j=0;//to manipulate rows
        while(s.hasNext())
        {
            String x=s.nextLine();
            String exp[] = x.split(" ");
            int flag=0;
            for(int i=0; i< exp.length; i++)//to go across the exp
            {
                String temp=exp[0];
                if(flag==0 && check(exp[i+1], table)==0)
                {
                    table[j][1]=temp;
                    table[j][0]=exp[1];
                    if(exp[i+2].equals("("))
                    {
                        table[j][2]=temp;
                        table[j][1]="null";
                        table[j][5]=exp[i+3];
                        table[j][4]="2";
                    }
                    flag=1;
                    if(exp[i+2].equals("="))
                    {
                        table[j][3]=exp[i+3];
                    }
                    if(exp[i+2].equals(";"));
                    {
                       // j++;
                    }
                }
                if(flag==0 && check(exp[i+1], table)==1)
                    System.out.println("Variable already declared");
                
                if(exp[i].equals(",") && check(exp[i+1], table)==0)
                {
                    j++;
                    if(exp[i+2].equals("("))
                    {
                        table[j][2]=temp;
                        table[j][1]="null";
                        table[j][5]=exp[i+3];
                        table[j][4]="2";
                    }
                    table[j][0]=exp[i+1];
                    table[j][1]=temp;
                    if(exp[i+2].equals("="))
                    {
                        table[j][3]=exp[i+3];
                    }
                    if(exp[i+2].equals(";"));
                    {
                    }
                }
                //if(exp[i].equals(",") && check(exp[i+1], table)==1)
                //    System.out.println("Variable already declared");
            }
            for(int i=0; i< j+1; i++)
            {
                for(int k=0; k< 6; k++)
                {
                    System.out.print(table[i][k] + " ");
                }
                System.out.println();
            }
            
        }
    }
    
}
