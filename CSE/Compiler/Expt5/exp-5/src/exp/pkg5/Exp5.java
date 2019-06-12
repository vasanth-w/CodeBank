package exp.pkg5;
import java.lang.*;
import java.util.*;
import java.io.*;
public class Exp5 {  
    public static void main(String[] args) throws Exception{     
        Scanner s= new Scanner(System.in);
        BufferedReader b= new BufferedReader(new FileReader("Input.txt"));
        BufferedWriter bw=new BufferedWriter(new FileWriter("middle.txt"));
        HashMap<String,Integer> optab=new HashMap<String,Integer>(){{put("STA",23);put("LDA",00);}};
        HashMap<String,Integer> symtab=new HashMap<String,Integer>();
        String line="";int startaddress=0;
        int lc=0;int cnt=1;
        while((line=b.readLine())!=null){
            String[] s1=line.split(" ");
            if(cnt==1){
                if(line.contains("start")|line.contains("START")){   
                    if(!s1[2].equals("-")){
                        lc=Integer.parseInt(s1[2], 16);
                    }
                    bw.write(line);
                    bw.newLine();
                }
                startaddress=lc;
            }
            else{
                bw.write(Integer.toHexString(lc)+" "+line);
                bw.newLine();
                if(!s1[0].equals("-")){
                    if(!symtab.containsKey(s1[0])){
                        symtab.put(s1[0], lc);
                    }
                    else{
                        System.out.println("ERROR!!!,DUPLICATE SYMBOL");
                        System.exit(0);
                    }
                }
                if(optab.containsKey(s1[1]) | s1[1].equals("WORD")){
                    lc=lc+3;
                }
                else if(s1[1].equals("BYTE")){
                    lc=lc+s1[2].length();
                }
                else if(s1[1].equals("RESW")){
                    lc=lc+(Integer.parseInt(s1[2],16)*3);
                }
                else if(s1[1].equals("RESB")){
                    lc=lc+(Integer.parseInt(s1[2],16));
                }
                else if(s1[1].equals("END")){
                    
                }
                else{
                    System.out.println("ERROR!!!!!");
                    System.exit(0);
                }
            }
            cnt++;
        }
        //System.out.println("program length = "+(lc-startaddress)+" ("+Integer.toHexString(lc-startaddress)+" in hexadecimal)");
        //System.out.println("PASS 1 COMPLEATED");
        b.close();
        bw.close();
        BufferedReader br=new BufferedReader(new FileReader("middle.txt"));
        BufferedWriter bw1=new BufferedWriter(new FileWriter("Result.txt"));
        int hc,tc,ec=0;
        String sd="";
        String trec="";int treccnt=0;String size="xx";
        boolean flag=false;
        while((line=br.readLine())!=null)
        {
            String rec="";
            String[] s1=line.split(" ");
            if(line.contains("start")|line.contains("START")){
                rec="H^"+s1[0]+"^"+tolen(Integer.toHexString(startaddress))+"^"+tolen(Integer.toHexString(lc-startaddress)); 
                bw1.write(rec);
                bw1.newLine();//\n
            }
            else if(line.contains("END")|line.contains("end")){
                if(flag==false){
                    String treclen=totwo(Integer.toString(treccnt*3));
                    trec=trec.replace("xx", treclen);
                    bw1.write(trec);
                    bw1.newLine();
                }
                rec="E^"+tolen(Integer.toHexString(startaddress));
                bw1.write(rec);
                bw1.newLine();
            }
            else{
                String objectcode="";
                if(treccnt==10){
                    String treclen=totwo(Integer.toString(treccnt*3));
                    trec=trec.replace("xx", treclen);
                    bw1.write(trec);
                    bw1.newLine();
                    flag=true;
                    treccnt=0;
                }
                if(treccnt==0){
                    flag=false;
                    trec="T^"+tolen(s1[0])+"^"+size+"^";
                }
                if(optab.containsKey(s1[2])){
                    objectcode=objectcode+totwo(Integer.toString(optab.get(s1[2])));
                    if(symtab.containsKey(s1[3])){
                       objectcode=objectcode+tofour(Integer.toHexString(symtab.get(s1[3])));
                    }
                    else{
                        System.out.println("ERROR!!!");
                        System.exit(0);
                    }
                    trec=trec+"^"+objectcode;
                    treccnt=treccnt+1;
                }
                if(line.contains("WORD")|line.contains("word")){
                    trec=trec+"^"+tolen(s1[3]);
                    treccnt=treccnt+1;
                }
                if(line.contains("BYTE")|line.contains("byte")){
                    trec=trec+"^"+tolen(s1[3]);
                    treccnt=treccnt+1;
                }
            }
        }
        System.out.println("Done");
        br.close();
        bw1.close();
    }
    public static String tofour(String n){
        String res=n;
        for(int i=n.length();i<4;i++){
            res="0"+res;
        }
        return res;
    }
    public static String tolen(String n){
        String res=n;
        for(int i=n.length();i<6;i++){
            res="0"+res;
        }
        return res;
    }
    public static String totwo(String n){
        String res=n;
        for(int i=n.length();i<2;i++){
            res="0"+res;
        }
        return res;
    } 
}