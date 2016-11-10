/*
 * startResource, sizeResource的坐标比txt文件里的小1
 * 可以提取出start， size
 * 可以实现单个word评分
 */
package Invert_Index;

import java.io.*;
import java.util.*;

public class input2 {
	
	public static ArrayList<String> startArray = new ArrayList<String>();
	public static ArrayList<String> sizeArray = new ArrayList<String>();
	public static ArrayList<mark> markArray = new ArrayList<mark>();
	public static HashMap<String, Integer> hashPos = new HashMap<String, Integer>();//记录不同开头字母的起始位置
	public static HashMap<String, Integer> hashWord = new HashMap<String, Integer>();
	public static HashMap<String,Integer>pageMap=new HashMap<String,Integer>();
	public static String[] strSplit;
	public static String[] strSplit2;
	public static byte[] byteArray;
	public static BufferedReader startReader = null;
	public static BufferedReader sizeReader = null;
	public static BufferedReader pageReader = null;
	public static BufferedWriter testWriter = null;
	public static String startName = "src/Invert_Index/minHeap/start_index";
	public static String sizeName = "src/Invert_Index/minHeap/size_Index";
	public static String indexName="src/Invert_Index/minHeap/Invert_Index";
	public static String pageName = "src/Invert_Index/minHeap/index_draw";
	public static String testName="src/Invert_Index/minHeap/test_Invert_Index";
	public static String lineStart = null;
	public static String lineSize = null;
	public static String linePage = null;
	public static int targetStart=0;
	public static int targetSize=0;
	public static int pos=0;
	public static String str=" ";
	public static int ft=0;
	public static final int N=52140;
	public static int fdt=0;
	public static int d=0;
	//public static double dav=drawIndex.dav;   //处在分母位置，计算K
	public static double dav=16622.0;
	public static final double k1=1.2;
	public static final double b=0.75;
	public static double K=0;
	public static double BM=0;
	
	public static void main(String[] argv) throws IOException {
		
		int n=0;	
		Integer m=0;
		try {
			startReader = new BufferedReader(new FileReader(startName));
			sizeReader = new BufferedReader(new FileReader(sizeName));
			pageReader = new BufferedReader(new FileReader(pageName));
			testWriter=new BufferedWriter(new FileWriter(testName));
			
			while ((lineSize = sizeReader.readLine()) != null) {
				sizeArray.add(lineSize);
				sub_string(lineSize);
				hashWord.put(strSplit[0],n);
				n++;
			}	
			
			while ((lineStart = startReader.readLine()) != null) {
				startArray.add(lineStart);
			}		
			
			while ((linePage = pageReader.readLine()) != null) {
				//startArray.add(linePage);
				strSplit=new String[2];
				strSplit=linePage.split(" ");
				pageMap.put(m.toString(),Integer.parseInt(strSplit[1] ));
				m++;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			startReader.close();
			sizeReader.close();
		}

		System.out.println("input words(not start with numbers): ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
	
		boolean flag;
		try{
//			while (target != "endend") 
//			{			
				if(hashWord.get(target)!=null)
				{
					pos=hashWord.get(target);				
					sub_string(startArray.get(pos));
					targetStart=Integer.parseInt(strSplit[1]);				
					sub_string(sizeArray.get(pos));
					targetSize=Integer.parseInt(strSplit[1]);				
					//System.out.println(targetStart+"   "+targetSize);			
				}
				else
				{
					System.out.println("not found!");
				}
				
				RandomAccessFile ra=new RandomAccessFile(indexName,"r");
				byteArray=new byte[targetSize];
				ra.seek(targetStart);
				ra.read(byteArray,0,targetSize);
				str=new String(byteArray);
				testWriter.write(str);
				//System.out.println(str);
				
				ft=str.split("]").length;
				System.out.println(ft);
		       strSplit=new String[ft];
		       strSplit=str.split("]");
				for(int i=0;i<ft;i++)
				{
					strSplit[i]=strSplit[i].substring(1);
					//System.out.println(strSplit[i]);
					int j=strSplit[i].split(" ").length;
					strSplit2=new String[j];
					strSplit2=strSplit[i].split(" ");
//					for(int k=0;k<j;k++)
//					    System.out.println(strSplit2[k]);
					fdt=Integer.parseInt(strSplit2[1]);
					//d=drawIndex.dataMap.get(Integer.parseInt(strSplit2[0]));
					d=pageMap.get(strSplit2[0]);
					K=k1*((1-b)+b*d/dav);
					BM= Math.log((N-ft+0.5)/(ft+0.5))*(k1+1)*fdt/(K+fdt);
					markArray.add(new mark(strSplit2[0],BM));
//					 System.out.println(strSplit2[0]+"   "+d+"   "+fdt+"   "+K+"   "+BM);
//					 System.out.println();
				}
				
				Collections.sort(markArray);
				
				for(int i=0;i<markArray.size();i++)
					System.out.println(markArray.get(i));
				//testWriter.flush();
//				sc.reset();
//				target = sc.next();			
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			testWriter.flush();
			testWriter.close();
		}
				
	}
	
	public static void sub_string(String str) {
		strSplit = new String[2];
		strSplit = str.split(" ");
	}
	
	public static void sub_string2(String str) {
		strSplit = new String[2];
		strSplit = str.split("|");
	}
}
