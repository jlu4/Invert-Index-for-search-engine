/*
 * 实现word-start-size
 */
package Invert_Index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class lexicon2 {
    public static ArrayList<String> lex=new ArrayList<String>();
    public static HashMap<String,Integer> startMap=new HashMap<String,Integer>();
    public static HashMap<String,Integer> sizeMap=new HashMap<String,Integer>();
    public static byte[] temp_byte=null;
    public static int start=0;
	public static int size=0;
    
    public static void main(String[] argv) throws IOException{
    	BufferedReader heapIn=null;
    	BufferedWriter indexOut=null;
    	BufferedWriter startOut=null;
    	BufferedWriter sizeOut=null;
    	String heapName="src/Invert_Index/minHeap/minHeap";
    	String indexName="src/Invert_Index/minHeap/Invert_Index";
    	String startName="src/Invert_Index/minHeap/start_index";
    	String sizeName="src/Invert_Index/minHeap/size_Index";
    	String[] strSplit;
    	String str1=null;
    	String str2=" ";
    	String lastStr=null;
    	String line=null;
    	String temp=" ";
    	
    	try{
    		heapIn=new BufferedReader(new FileReader(heapName));
    		indexOut=new BufferedWriter(new FileWriter(indexName));
    		startOut=new BufferedWriter(new FileWriter(startName));
    		sizeOut=new BufferedWriter(new FileWriter(sizeName));
    		
    		while((line=heapIn.readLine())!=null)
    		{
    			strSplit=new String[4];
    			strSplit=line.split(" ");
    			str1=strSplit[0];
    			str2=subString(line);
    			if(lastStr==null)
    			{
    				temp=str2;
    				startMap.put(str1,start);
    				startOut.write(str1+" "+start+'\n');
					startOut.flush();
    			}
    			else
    			{
    				if(str1.compareTo(lastStr)==0)
    				{
    					temp=temp.concat(str2);
    				}
    				else
    				{
    					temp_byte=temp.getBytes();
    					size=temp_byte.length;
    					sizeMap.put(lastStr,size);
    					indexOut.write(temp);
    					sizeOut.write(lastStr+" "+size+'\n');
    					
    					indexOut.flush();
    					sizeOut.flush();
    					
    					System.out.println(lastStr+"   "+start+"   "+size);
    					System.out.println(temp);
    					
    					temp=str2;
    					start=start+size;
    					startMap.put(str1,start);
    					startOut.write(str1+" "+start+'\n');
    					startOut.flush();
    				}
    			}
    			lastStr=str1;
    		}
    		//澶勭悊鏈�鍚庝竴涓崟璇嶇殑size
    		temp_byte=temp.getBytes();
			size=temp_byte.length;
			sizeMap.put(str1,size);
    		sizeOut.write(str1+" "+size+'\n');
    	}catch (Exception e) {
			e.printStackTrace();
		}finally{
			indexOut.flush();
			startOut.flush();
			sizeOut.flush();
			indexOut.close();
			startOut.close();
			sizeOut.close();
		}
    			
    }
    
    public static String subString(String s)
    {
    	int i=s.indexOf(" ");
    	String sub=s.substring(i);
    	return sub;
    }
}
