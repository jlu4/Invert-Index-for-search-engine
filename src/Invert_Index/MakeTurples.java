/*
 * Ԥ����
 * ���Է��ļ�����,���ź���
 * ��һ��class��posingformat.class
 * 
 */
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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class MakeTurples {

	public static byte[] fileNameBytes;
	public static String[] strSplit;
	public static ArrayList<String> strArray = new ArrayList<String>();
	public static ArrayList<Tuples> tuplesArray = new ArrayList<Tuples>();
	public static ArrayList<Integer> dataArray = new ArrayList<Integer>();
	public static HashMap<Integer,Integer>dataMap=new HashMap<Integer,Integer>();
	public static BufferedReader bufferforindex;
	public static GZIPInputStream dataStream;
	public static BufferedReader bufferfordata;
//	public static String filename = "src/Invert_Index/Invert_Index.txt";
	public static File file;
	public static File tupleFile;
	public static Scanner sc;
	
	public static parse parse;
	public static int t_index = 0;
	public static int index = 0;
	public static int index2 = 0;
	public static int exTupleSize = 0;
    public static int countInTotal=0;
    public static int count10=0;
    
	public static void main(String[] args) throws IOException {
		String line, line2, data_index, url;
		Integer tuple_index = 0;
		Integer data = 0;
		Integer indexFile_index = 0;	
		Integer dataFile_index = 0;
		String indexFileName = "src/Invert_Index/nz2_merged/0_index";
		String dataFileName = "src/Invert_Index/nz2_merged/0_data";
		String dataLengthFileName="src/Invert_Index/minHeap/pageLength";
		String tupleFileName;
		String minHeapName;

		int datalength = 0;
		BufferedWriter tupleOut = null;
		BufferedWriter minHeapOut = null;
		BufferedWriter pageLengthOut = new BufferedWriter(new FileWriter(dataLengthFileName));
		String target_index = " ";
		String str;
		int lab = 0;
		int temp_p=0;
		
		
		try {
			for (int txt_index = 1; txt_index <10 ; txt_index++) 
			{
				 index=0;//index����count10
	             index2=0;
				count10=0;
				for (indexFile_index = (txt_index - 1) * 10, dataFile_index = (txt_index - 1) * 10;  indexFile_index < 83&&dataFile_index<83&&indexFile_index < txt_index * 10
						&& dataFile_index < txt_index * 10; indexFile_index++, dataFile_index++) 
				{                   
					indexFileName = "src/Invert_Index/nz2_merged/"
							+ indexFile_index.toString() + "_index";
					dataFileName = "src/Invert_Index/nz2_merged/"
							+ dataFile_index.toString() + "_data";
					bufferforindex = new BufferedReader(new InputStreamReader(
							new GZIPInputStream(new FileInputStream(
									indexFileName))));             //index�ļ���BufferedReader��ʽ
					dataStream = new GZIPInputStream(new FileInputStream(
							dataFileName));                         //data�ļ���GZIP��ʽ
					bufferfordata = new BufferedReader(new InputStreamReader(
							dataStream));                             //data�ļ���BufferedReader��ʽ

					while ((line = bufferforindex.readLine()) != null) 
					{
						sub_string(line);
						data_index = strSplit[3];
						datalength = Integer.parseInt(data_index);
						dataArray.add(datalength);                        // ���ÿ��htmlҳ�泤��					
						index++;                                                     //ÿ��data�ļ����page����
						//countInTotal++;                                         //����data�ļ����page������
						count10++;                                                  //10��data�ļ����page������
					}
					for (; index2 < index; index2++) {
						readBytesFromGzipInputStream(dataStream,  //ÿ��data�ļ���GZIP��ʽ
								dataArray.get(index2));
						str = new String(fileNameBytes, "UTF-8");
						strArray.add(str);// ���htmlҳ��, 10��data�ļ�
						//System.out.println(str);
					}
				}
				/*
				 * �Ѿ���ȡ��ǰ10��data�ļ����е�page��
				 */
				tuple_index = txt_index - 1;
				tupleFileName = "src/Invert_Index/Tuples/Tuples"
						+ tuple_index.toString();// 9��txt�ļ�
				
				createFile(tupleFileName);
				tupleOut = new BufferedWriter(new FileWriter(tupleFileName));

				for (t_index=0; t_index <count10 ; t_index++) 
				{
					parse.parsePage(" ",strArray.get(t_index), tuplesArray,  t_index+countInTotal);//strarray�����page������
				}
				//docid =  t_index+countInTotal
				//turple   (name, docid, count)
				Collections.sort(tuplesArray);
				
				for (int i = 0; i < tuplesArray.size(); i++) {
				//	System.out.println(tuplesArray.get(i).toString());
					tupleOut.write(tuplesArray.get(i).toString() + '\n');
					
				}
				countInTotal+=count10;
				tupleOut.flush();
				//pageLengthOut.flush();
                //System.out.println(exIndex);
                strArray.clear();
                dataArray.clear();
               tuplesArray.clear();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			tupleOut.close();
			//pageLengthOut.close();
		}
	}

	public static void readBytesFromGzipInputStream(GZIPInputStream ins,
			int sumLeng) throws IOException {
		fileNameBytes = new byte[sumLeng];
		int fileNameReadLength = 0;
		int hasReadLength = 0;// �Ѿ���ȡ���ֽ���
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
			System.out.println("���������ļ�" + tupleFileNmae + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");
			return false;
		}
		if (tupleFileNmae.endsWith(File.separator)) {
			System.out.println("���������ļ�" + tupleFileNmae + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");
			return false;
		}
		// �ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����
		if (!tupleFile.getParentFile().exists()) {
			// ���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼
			System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");
			if (!tupleFile.getParentFile().mkdirs()) {
				System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");
				return false;
			}
		}
		// ����Ŀ���ļ�
		try {
			if (tupleFile.createNewFile()) {
				System.out.println("���������ļ�" + tupleFileNmae + "�ɹ���");
				return true;
			} else {
				System.out.println("���������ļ�" + tupleFileNmae + "ʧ�ܣ�");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("���������ļ�" + tupleFileNmae + "ʧ�ܣ�"
					+ e.getMessage());
			return false;
		}
	}
}
