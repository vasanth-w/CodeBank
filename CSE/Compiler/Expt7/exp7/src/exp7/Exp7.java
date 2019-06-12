package exp7;
import java.util.*;
import java.io.*;
public class Exp7 
{
    public static void main(String[] args) throws Exception 
    {
        Scanner s= new Scanner(System.in);
        BufferedReader b= new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw=new BufferedWriter(new FileWriter("output.txt"));
        String line="";int rows=6,cols=7;
        String ss="E";
        String ar[][]=new String[rows][cols];
        //System.out.println("THE PARSING TABLE IS : ");
        
        int j=0;
        while((line=b.readLine())!=null)
        {
            String a[]=line.split(" ");
            for(int i=0;i<a.length;i++)
            {
               if(a[i].length()>7)
               {
                   ar[j][i]=a[i];
                   //System.out.print(a[i]+"\t");
               }
               else
               {
                   ar[j][i]=a[i];
                   //System.out.print(a[i]+"\t\t");
               } 
            }
            j++;
            //System.out.println("");
            
        }
        System.out.print("Enter start symbol : ");
        String sy=s.nextLine();
        System.out.print("Give a input string: ");
        String s1=s.nextLine()+" $";
        Stack<String> stack3=new Stack<>();
        Stack<String> stack4=new Stack<>();
        String terminal[]={"id","+","*","(",")","$"};
        String nonterminal[]={"E","E'","T","T'","F"};
        char c;boolean flag3=false;int strlen;int ind=0;
        for(int i=0;i<s1.length();i++)
        {
            flag3=false;
            c=s1.charAt(i);
            for(int k=0;k<terminal.length;k++)
            {
                if(terminal[k].startsWith(c+""))
                {
                    flag3=true;
                    ind=k;
                    break;
                }
            }
            if(flag3)
            {
                strlen=terminal[ind].length();
                int end=i+strlen;
                 stack3.push(s1.subSequence(i, end)+"");
                i=i+(strlen-1);
            }
        }
        Stack<String> stack1=new Stack<>();
        stack1.push("$");
        stack1.push(sy);
        Stack<String> stack2=new Stack<>();
        for(int i=stack3.size();i>0;i--)
        {
            stack2.push(stack3.pop());
        }
        String st1="",st2="",add1="",add2="";boolean flag=false,flag1=false,flag2=false;int i=0,k=0;
        int cnt=1;
        while(!stack1.isEmpty()|!stack2.isEmpty())
        {
           st1=stack1.pop();flag1=false;flag2=false;flag=false;
           if(cnt==1)
           {
               st2=stack2.pop();
               cnt++;
           }
           if(st1.equals(st2))
           {
               if(st1.equals("$") && st2.equals("$"))
               {
                   System.out.println("STRING ACCEPTED");
                   System.exit(0);
               }
               st2=stack2.pop();
               System.out.println(stack1+"\t\t\t"+st2+"\t\t\t----------");
               continue;
           }
           for(i=1;i<rows;i++)
           {
               if(ar[i][0].equals(st1))
               {
                   flag1=true;
                   break;
               }
           }
           for(k=1;k<cols;k++)
           {
               if(ar[0][k].equals(st2))
               {
                   flag2=true;
                   break;
               }
           }
           if(flag1 && flag2)
           {
               add1=ar[i][k];
                if(add1.equals("null"))
                {
                    System.out.println("Null production not allowed");
                    System.exit(0);
                }
                String[] temp=add1.split(">");
                add2=temp[1];
                if(add2.equals("LAM"))
                {
                    System.out.println(stack1+"\t\t\t\t"+st2+"\t\t\t"+add1);
                }
                else
                {
                    for(int m=0;m<terminal.length;m++)
                    {
                        if(terminal[m].equals(add2))
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(flag)
                    {
                        stack1.push(add2);
                    }
                    else
                    {
                        int q=0;
                        for(int m=0;m<add2.length();m++)
                        {
                            boolean flag5=false;
                            for(int y=0;y<nonterminal.length;y++)
                            { 
                                q=0;
                                if(nonterminal[y].startsWith(add2.charAt(m)+""))
                                {
                                    flag5=true;
                                    int len=nonterminal[y].length();
                                    int end=m+len;
                                    if(add2.charAt(m+1)=='\''){
                                        q=q+1;
                                       end=end+1;
                                    }
                                    stack4.push(add2.substring(m,end)+""); 
                                    m=m+(len-1);
                                    m=m+q;
                                    break;
                                }
                            }
                            if(!flag5)
                            {
                                for(int y=0;y<terminal.length;y++)
                                {
                                    if(terminal[y].startsWith(add2.charAt(m)+""))
                                    {
                                        int len=terminal[y].length();
                                        int end=m+len;
                                        stack4.push(add2.substring(m,end)+"");
                                        m=m+(len-1);
                                    }
                                }
                            }
                        }
                        for(int e=stack4.size();e>0;e--)
                        {
                            stack1.push(stack4.pop());
                        }             
                    }
                    System.out.println(stack1+"\t\t\t"+st2+"\t\t\t"+add1);
                }  
           }
           else if(flag1==false)
           {
               System.out.println("ERROR!!! No such stack symbol found");
               System.exit(0);
           }
           else if(flag2==false)
           {
               System.out.println("ERROR!!! No such string symbol found");
               System.exit(0);
           } 
        }
        System.out.println("STRING NOT ACCEPTED");
    }  
}