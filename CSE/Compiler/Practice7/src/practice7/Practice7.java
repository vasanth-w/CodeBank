/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice7;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Vasanth
 */
public class Practice7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner s=new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        System.out.println("Enter the string to parse ");
        String in=s.nextLine();
        in=in+" $";
        String input[]=in.split(" ");
        Stack<String> inStack = new Stack<String>();
        for(int i=input.length-1;i>=0;i--)
        {
            inStack.push(input[i]);
        }
        //System.out.println(Arrays.toString(inStack.toArray()));
        String table[][]=new String[6][7];
        String temp="";
        int index=0;
        while((temp=br.readLine())!=null)
        {
            table[index++]= temp.split("\t");
        }
        System.out.println(Arrays.deepToString(table));
        Stack<String> stack =new Stack<String>();
        stack.push("$");
        stack.push(table[1][0]);
 start: while(stack.peek()!="$" && inStack.peek()!="$")
        {
            int flag=0;
            String r=stack.peek();
            String c=inStack.peek();
            for(int i=0;i<7;i++)
            {
                if(!(table[0][i].equals(c)))
                {
                    if(i==6)
                        flag=1;
                }
                else
                    break;
            }
            if(flag==1)
            {
                System.out.println("Not Accepted / ");
                System.exit(0);
            }
            if(r.equals(c))
            {
                stack.pop();
                inStack.pop();
                continue start;
            }
            if(r.equals("e"))
            {
                stack.pop();
            }
            int row=0,col=0;
            for(int i=0;i<6;i++)//to find row number
            {
                if(table[i][0].equals(r))
                    row=i;
            }
            for(int i=0;i<7;i++)//to get col number
            {
                if(table[0][i].equals(c))
                    col=i;
            }
            if(!(table[row][col].equals("-")))
            {
                String grammar=table[row][col];
                String grammarSplit[]=grammar.split(" ");
                stack.pop();
                for(int i=grammarSplit.length-1; i>=2; i--)
                {
                    stack.push(grammarSplit[i]);
                }
                System.out.println(Arrays.toString(inStack.toArray()));
                System.out.println(Arrays.toString(stack.toArray()));
            }
            else
            {
                System.out.println("Not accepted -");
                System.exit(0);
            }
        }
        System.out.println("The string is accepted");
    }
    
}
