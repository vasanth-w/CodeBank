/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstfollow;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Vasanth
 */
public class FirstFollow 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner s=new Scanner(System.in);
        BufferedReader br = new BufferedReader( new FileReader("input.txt"));
        String table[][]=new  String[5][3];
        String temp="";
        int count=0;
        while((temp=br.readLine())!=null)
        {
            table[count++]= temp.split(" ");
        }  
    }
    
}
