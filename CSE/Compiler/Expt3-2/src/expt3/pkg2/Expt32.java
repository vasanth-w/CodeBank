/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expt3.pkg2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Vasanth
 */
public class Expt32 
{
    public static void replace(String a,String b,String dfa[][]) 
    {
        for(int i=1;i<5;i++)
        {
            for(int j=1; j<3; j++)
            {
                if(dfa[i][j].equals(a))
                {
                    dfa[i][j]=b;
                }
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner s=new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        System.out.println("Enter the Initial State: ");
        String ini=s.next();
        //s.next();
        System.out.println("Enter Final State: ");
        String  fin=s.next();
        //String finale[]=fin.split(" ");
        String temp="";
        String dfa[][]=new String[5][3];
        int count=0;
        while((temp=br.readLine())!=null)
        {
            dfa[count++]= temp.split("\t");
        }
        int c=0;
        System.out.println(Arrays.deepToString(dfa));
  Start:
  while(true)
  {
      c++;
      for(int i=1; i<5; i++)
        {
            for(int j=i+1;  j<5;  j++)
            {
                if((dfa[i][1].equals(dfa[j][1])) && (dfa[i][2].equals(dfa[j][2])))// && ( !(dfa[j][0].equals("-")) ))
                {
                    if(((fin.contains(dfa[i][0])) && (fin.contains(dfa[j][0]))) || (!(fin.contains(dfa[i][0])) && !(fin.contains(dfa[j][0])))) //cheacking if both are final state 
                    {
                        String stateToRep= dfa[j][0];
                        //System.out.println("A: " + stateToRep);
                        String stateToRepWith = dfa[i][0];
                        //System.out.println("B: " + stateToRepWith);
                        dfa[j][1] = dfa[j][2] = dfa[j][0] = "-";
                        System.out.println(Arrays.deepToString(dfa));
                        //write replace function
                        replace(stateToRep,stateToRepWith,dfa);
                        continue Start;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dfa));
    }
   }
}
