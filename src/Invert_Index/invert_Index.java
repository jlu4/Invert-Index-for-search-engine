package Invert_Index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class invert_Index {

	public static Queue queue = new LinkedList();
	public static byte[] fileNameBytes;
	public static ArrayList<String> strArray = new ArrayList<String>();
	public static String[] strSplit;
	public static ArrayList<Integer> dataArray = new ArrayList<Integer>();
	public static ArrayList<String> urlArray = new ArrayList<String>();
	public static ArrayList<String> datafilename=new ArrayList<String>();
	public static ArrayList<String> indexfilename=new ArrayList<String>();
	
	
	public static void main(String[] args) throws IOException {
		FileWriter of=new FileWriter("Result_1.txt",true);//
		String temp;//
		int count=1;//
		FileWriter tempforwrite=null;//
		for(int z=0;z<83;z++) {
			indexfilename.add("src/nz2_merged/"+z+"_index");//
			datafilename.add("src/nz2_merged/"+z+"_data");//
		}
		for(int fi=0;fi<indexfilename.size();fi++) {//
			if(fi%10==0) {
				tempforwrite=new FileWriter("Result_"+count+".txt",true);//
				count++;//
			}
			outputdata(datafilename.get(fi),indexfilename.get(fi),tempforwrite);
		}
	}

	public static void readBytesFromGzipInputStream(GZIPInputStream ins,
			int sumLeng) throws IOException {
		fileNameBytes = new byte[sumLeng];
		int fileNameReadLength = 0;
		int hasReadLength = 0;
		while ((fileNameReadLength = ins.read(fileNameBytes, hasReadLength,
				sumLeng - hasReadLength)) > 0) {
			hasReadLength = hasReadLength + fileNameReadLength;
		}
		
	}
	public static void sub_string(String str) {
		strSplit=new String[7];
		strSplit=str.split(" ");
		queue.add(strSplit[0]);
	}
	public static void outputdata(String data,String index,FileWriter of) throws IOException {
		ArrayList<Tuples> array=new ArrayList<Tuples>();//
		String line=null;//
		String data_index;//
		int datalength;//
		int ind=0;//
		try {
			BufferedReader bufferforindex = new BufferedReader(//
					new InputStreamReader(new GZIPInputStream(//
							new FileInputStream(index))));//
			GZIPInputStream dataStream = new GZIPInputStream(//
					new FileInputStream(data));//
			BufferedReader bufferfordata = new BufferedReader(//
					new InputStreamReader(dataStream));//
			while ((line = bufferforindex.readLine()) != null)
			{
			//	System.out.println(index+"   "+line);
				String[] result=line.split(" ");//
				data_index = result[3];//lengh?

				datalength = Integer.parseInt(data_index);//可以从字符串中获取整数使用
	
				readBytesFromGzipInputStream(dataStream,datalength);//
				String str = new String(fileNameBytes, "UTF-8");//html页面内容
			//	System.out.println(str);
				parse.parsePage("",str,array,ind);
				//System.out.println(data_index);
				ind++;
			}
			of.write(data+"\n");//
			for(int i=0;i<array.size();i++) {//
				of.write(array.get(i).toString());//
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}