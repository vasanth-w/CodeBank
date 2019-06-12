/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expt3;
import java.util.*;
/**
 *
 * @author ur16cs089
 */
public class Expt3 
{
    public static void main(String[] args) 
    {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter number of states");
        int state=s.nextInt();
        System.out.println("Enter number of input symbols");
        int sym=s.nextInt();
        char dfa[][] =new char[state][sym];
        for(int i=0; i<state; i++)
        {
            for(int j=0; j<sym; j++)
            {
                dfa[i][j]=s.next().charAt(0);
            }
        }
        String str=s.next();
        //str=str.concat("#");
        char c[]= str.toCharArray();
        int row=0,col=0;
        for(int i=0; i<c.length ; i++)
        {
                col=c[i]-97;
                //read the value present at row and col and move to that place in array
                char temp=dfa[row][col];
                //now go to the index which temp denotes
                row=temp-65;
        }
        if(row==state-1)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
    
}
