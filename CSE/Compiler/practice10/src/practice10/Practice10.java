/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice10;
import java.util.*;
/**
 *
 * @author Vasanth
 */
public class Practice10 
{
    public static void main(String[] args) 
    {
        Scanner s=new Scanner(System.in);
        int eqPresent=0;
        String input=s.nextLine();
        int start=0;
        String LHS=new String(),RHS=new String();
        if(input.contains("="))
        {
            eqPresent=1;
            String temp[]=input.split("=");
            LHS=temp[0];
            RHS=temp[1];
        }
        int count=0;
        for(int i=0; i<RHS.length() ; i++)
        {
            char c=RHS.charAt(i);
            //System.out.println(c);
            if(start==0)
            {    
                if(c=='+' || c=='-' || c=='*' || c=='/')
                {
                    System.out.println("temp" + count + " = " + RHS.charAt(i-1) + " " + RHS.charAt(i) + " " + RHS.charAt(i+1));
                    count++;
                    start++;
                    i++;
                }
            }
            else
            {
                if(c=='+' || c=='-' || c=='*' || c=='/')
                {
                    System.out.println("temp" + count + " = " + "temp" + (count-1) + " " + RHS.charAt(i) + " " + RHS.charAt(i+1));
                    count++;
                    i++;
                }
            } 
        }
        if(eqPresent==1)
        {
            System.out.println("temp" + (count-1) + " = " + LHS);
        }
    }
    
}
