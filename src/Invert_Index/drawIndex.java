package Invert_Index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class drawIndex {
	public static byte[] fileNameBytes;
	public static ArrayList<Integer> dataArray = new ArrayList<Integer>();
	public static HashMap<Integer,Integer>dataMap=new HashMap<Integer,Integer>();
	public static ArrayList<String> urlArray = new ArrayList<String>();
	public static HashMap<Integer,String>urlMap=new HashMap<Integer,String>();
	public static BufferedReader bufferforindex;
	public static GZIPInputStream dataStream;
	public static String filename = "src/Invert_Index/Invert_Index.txt";
	public static File file;
	public static File tupleFile;
	public static Scanner sc;
	public static String[] strSplit;
	public static int index = 0;                                       //个数
	public static int index2 = 0;
	public static double dav=0;
	public static int dsum=0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Integer indexFile_index = 0;
		String indexFileName = "src/Invert_Index/nz2_merged/0_index";
		String indexDrawFileName = "src/Invert_Index/minHeap/index_draw";
		String urlDrawFileName = "src/Invert_Index/minHeap/url_draw";
		String line=" ";
		String data_index=" ";
		String url=null;
		int datalength=0;
		BufferedWriter indexDrawOut = null;
		BufferedWriter urlDrawOut = null;
		
		try {
			createFile(indexDrawFileName);
			indexDrawOut = new BufferedWriter(new FileWriter(indexDrawFileName));
			createFile(urlDrawFileName);
			urlDrawOut = new BufferedWriter(new FileWriter(urlDrawFileName));
			
			for (indexFile_index = 0;  indexFile_index < 83; indexFile_index++) {                   
					indexFileName = "src/Invert_Index/nz2_merged/"+ indexFile_index.toString() + "_index";
					bufferforindex = new BufferedReader(new InputStreamReader(
							new GZIPInputStream(new FileInputStream(
									indexFileName))));

					while ((line = bufferforindex.readLine()) != null) {
						sub_string(line);
						data_index = strSplit[3];
						url=strSplit[0];
						datalength = Integer.parseInt(data_index);
						dsum+=datalength;
						dataArray.add(datalength);// 添加每个html页面长度
						dataMap.put(index,datalength);
						urlArray.add(url);// 添加每个html页面长度
						urlMap.put(index,url);
						indexDrawOut.write(index+" "+datalength+'\n');
						urlDrawOut.write(index+" "+url+'\n');
						index++;
					}	
					System.out.println(index);
					indexDrawOut.flush();
					urlDrawOut.flush();
			}
			dav=dsum/index;
			System.out.println(dav);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			indexDrawOut.flush();
			urlDrawOut.flush();
			indexDrawOut.close();
			urlDrawOut.close();
		}
	}
	
	public static void readBytesFromGzipInputStream(GZIPInputStream ins,
			int sumLeng) throws IOException {
		fileNameBytes = new byte[sumLeng];
		int fileNameReadLength = 0;
		int hasReadLength = 0;// 已经读取的字节数
		while ((fileNameReadLength = ins.read(fileNameBytes, hasReadLength,
				sumLeng - hasReadLength)) > 0) {
			hasReadLength = hasReadLength + fileNameReadLength;
		}
	}

	public static void sub_string(String str) {
		strSplit = new String[7];
		strSplit = str.split(" ");
	}

	public static boolean createFile(String tupleFileNmae) {
		tupleFile = new File(tupleFileNmae);
		if (tupleFile.exists()) {
			System.out.println("创建单个文件" + tupleFileNmae + "失败，目标文件已存在！");
			return false;
		}
		if (tupleFileNmae.endsWith(File.separator)) {
			System.out.println("创建单个文件" + tupleFileNmae + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!tupleFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!tupleFile.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (tupleFile.createNewFile()) {
				System.out.println("创建单个文件" + tupleFileNmae + "成功！");
				return true;
			} else {
				System.out.println("创建单个文件" + tupleFileNmae + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建单个文件" + tupleFileNmae + "失败！"
					+ e.getMessage());
			return false;
		}
	}

}
