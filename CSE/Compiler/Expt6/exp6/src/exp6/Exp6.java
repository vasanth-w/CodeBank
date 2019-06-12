package exp6;
import java.io.*;
import java.util.*;
public class Exp6 
{
    public static void main(String[] args) throws Exception 
    {
        Scanner s=new Scanner(System.in);
        BufferedReader b= new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw=new BufferedWriter(new FileWriter("output.txt"));
        String line="";
        HashMap<String,String> funcdeftab = new HashMap<String,String>();
        HashMap<String,String> funcparatab = new HashMap<String,String>();
        HashMap<String,String> reptab = new HashMap<String,String>();
        boolean flag=false,keyflag=false;
        while((line=b.readLine())!=null)
        {
            if(flag)
            {
                Iterator im =funcparatab.entrySet().iterator();
                while(im.hasNext())
                {
                    keyflag=false;
                    Map.Entry me=(Map.Entry)im.next();
                    String k=me.getKey().toString();
                    if(line.contains(k))
                    {
                        String g="";
                        for(String key : funcdeftab.keySet())
                        {
                            if(key.startsWith(k))
                            {
                                g=key;
                                keyflag=true;
                                break;
                            }
                        }
                        if(keyflag)
                        {
                            String endstr="";
                            String[] sing=line.split(";");
                            String chstr="";
                            if(sing.length==1)
                            {
                                String[] equal=sing[0].split("=");
                                if(equal.length==1)
                                {
                                    chstr=equal[0];
                                }
                                else if(equal.length==2)
                                {
                                    endstr=endstr+equal[0]+" = ";
                                    chstr=equal[1];
                                }
                            }
                            else
                            {
                                System.out.println("Error");
                                System.exit(0);
                            }
                            int cnt=chstr.split(",").length;
                            if(cnt==Integer.parseInt(funcparatab.get(k)))
                            {
                                String orig=chstr.replaceAll("\\)","");
                                String[] orig1=orig.split("\\(");
                                String[] origval=orig1[1].split(",");
                                String rep=g.replaceAll("\\)","");
                                String[] rep1=rep.split("\\(");
                                String[] repval=rep1[1].split(","); 
                                String z=funcdeftab.get(g);
                                for(int i=0;i<origval.length;i++){
                                    z=z.replaceAll(repval[i],origval[i]);
                                }
                                endstr=endstr+z+";";
                                line=endstr;
                            }
                            else
                            {
                                System.out.println("Error"+line);
                                System.exit(0);
                            }
                        }
                        else
                        {
                            line=line.replace(k, funcparatab.get(k));
                        }
                    }
                }
                bw.write(line);
                bw.newLine(); 
            }
            else
            {
               if(line.toLowerCase().contains("main()"))
               {
                   bw.write(line);
                   bw.newLine();
                   flag=true;
               }
               else
               {
                   if(line.contains("#define"))
                   {
                       String[] s1=line.split(" ");
                       if(s1[1].contains("("))
                       {
                           int count=s1[1].split(",").length;
                           funcparatab.put(s1[1].split("\\(")[0],Integer.toString(count));
                           funcdeftab.put(s1[1], s1[2]);
                       }
                       else
                       {
                           funcparatab.put(s1[1], s1[2]);
                       }
                   }
                   else
                   {
                       bw.write(line);
                       bw.newLine(); 
                   }
               }
            }
        }
        //System.out.println("DefTab "+funcdeftab);
        //System.out.println("ParaTab "+funcparatab);
        System.out.println("Done processing");
        b.close();
        bw.close();
    }   
}