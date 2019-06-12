/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expt1;
import java.util.*;
import java.util.regex.Pattern;
/**
 *
 * @author UR16CS089
 */
public class Expt1 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String keyword[] = {"int", "for", "while", "if", "else", "class", "do", "double", "float", "String", "return"};
        String ops[] = {"==", "=", "+", "-", "/", "*", "<", ">", "<=", ">="};
        String punc[] = {"{", "}", "(", ")", ",",".",";"};
        //Pattern num = Pattern.compile("^[1-9][0-9]{0,2}$");
        
        Scanner s=new Scanner(System.in);
        while(s.hasNext())
        {
            String exp=s.nextLine();
            String strArr[] = exp.split(" ");
           // Arrays.sort(strArr);
           int flag=0;
            for(int i=0;i<strArr.length; i++)
            {
                for(int j=0; j<keyword.length; j++)
                {
                    if(keyword[j].equals(strArr[i]) && flag==0)
                    {
                        System.out.println("<keyword " + keyword[j] + "> ");
                        flag=1;
                        //break;
                    }
                }
                for(int j=0; j<ops.length; j++)
                {
                    if(ops[j].equals(strArr[i]) && flag==0)
                    {
                        System.out.println("<operator " + ops[j] + "> ");
                        flag=1;
                        //break;
                    }
                }
                for(int j=0; j<punc.length; j++)
                {
                    if(punc[j].equals(strArr[i]) && flag==0)
                    {
                        System.out.println("<Punctuation " + punc[j] + "> ");
                        flag=1;
                        //break;
                    }
                }
                if(Pattern.matches("^[1-9][0-9]*", strArr[i]) && flag==0)
                {
                    System.out.println("<Numerical " + strArr[i] + "> ");
                    flag=1;
                   // break;
                }
                if(Pattern.matches("[_a-zA-Z][_a-zA-Z0-9]*", strArr[i]) && flag==0)
                {
                    System.out.println("<Identifier " + strArr[i] + "> ");
                    flag=1;
                    //break;
                }
                if(flag==0)
                {
                    System.out.println("<Error " + strArr[i] + "> ");
                }
                flag=0;
            }
        }
    }
}
