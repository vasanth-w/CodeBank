package exp8;
import java.io.*;
import java.util.*;
public class Exp8 
{
    public static void main(String[] args) throws Exception 
    {
        Scanner s= new Scanner(System.in);
        BufferedReader b= new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw=new BufferedWriter(new FileWriter("output.txt"));
        String line="";
        
        int lc=0;
        while((line=b.readLine())!=null)
        {
            String ar[]=line.split("\\^");
            if(line.startsWith("H"))
            { 
//                for(int i=0;i<ar.length;i++){
//                    System.out.println(ar[i]);
//                }
            }
            if(line.startsWith("T"))
            {
                lc=Integer.parseInt(ar[1], 16);
                int k=3;
                while(k<(ar.length))
                {
                    for(int i=0;i<ar[k].length();i=i+2)
                    {
                        System.out.println(tosix(Integer.toHexString(lc))+"\t"+ar[k].substring(i, i+2));
                        lc=lc+1;
                    }
                    k++;
                }
            }
        }
        b.close();
        bw.close();
    }
    
    public static String tosix(String n)
    {
        String res=n;
        for(int i=n.length();i<6;i++)
        {
            res="0"+res;
        }
        return res;
    }
    
}
