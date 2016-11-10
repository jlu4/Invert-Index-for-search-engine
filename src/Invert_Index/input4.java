/*
 * startResource, sizeResource的坐标比txt文件里的小1
 * 可以提取出start， size
 * 可以实现单个word评分
 */
package Invert_Index;

import java.io.*;
import java.util.*;

public class input4 {

	public static ArrayList<String> startArray = new ArrayList<String>();
	public static ArrayList<String> sizeArray = new ArrayList<String>();
	public static ArrayList<String> pageidArray1 = new ArrayList<String>();
	public static ArrayList<String> pageidArray2 = new ArrayList<String>();
	public static ArrayList<String> samepageArray = new ArrayList<String>();
	public static ArrayList<mark> markArray = new ArrayList<mark>();
	public static HashMap<String, Integer> hashPos = new HashMap<String, Integer>();// 记录不同开头字母的起始位置
	public static HashMap<String, Integer> hashWord = new HashMap<String, Integer>();
	public static HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> freMap1 = new HashMap<String, Integer>();
	public static HashMap<String, Integer> freMap2 = new HashMap<String, Integer>();
	public static String[] strSplit;
	public static String[] strSplit_div1;
	public static String[] strSplit_div12;
	public static byte[] byteArray1;
	public static String[] strSplit_div2;
	public static String[] strSplit_div22;
	public static byte[] byteArray2;
	public static BufferedReader startReader = null;
	public static BufferedReader sizeReader = null;
	public static BufferedReader pageReader = null;
	public static String startName = "src/Invert_Index/minHeap/start_index";
	public static String sizeName = "src/Invert_Index/minHeap/size_Index";
	public static String indexName = "src/Invert_Index/minHeap/Invert_Index";
	public static String pageName = "src/Invert_Index/minHeap/index_draw";
	public static String lineStart = null;
	public static String lineSize = null;
	public static String linePage = null;

	public static int targetStart1 = 0;
	public static int targetSize1 = 0;
	public static int pos1 = 0;
	public static String str1 = " ";
	public static int ft1 = 0;
	public static int fdt1 = 0;
	public static int d1 = 0;
	public static String pageid1 = null;

	public static int targetStart2 = 0;
	public static int targetSize2 = 0;
	public static int pos2 = 0;
	public static String str2 = " ";
	public static int ft2 = 0;
	public static int fdt2 = 0;
	public static int d2 = 0;
	public static String pageid2 = null;

	public static final int N = 52140;
	public static int ft = 0;
	public static int fdt = 0;
	public static int d = 0;
	public static double dav = 16622.0;
	public static final double k1 = 1.2;
	public static final double b = 0.75;
	public static double K = 0;
	public static double BM = 0;

	public static void main(String[] argv) throws IOException {

		int n = 0;
		Integer m = 0;
		RandomAccessFile ra = new RandomAccessFile(indexName, "r");
		;
		try {
			startReader = new BufferedReader(new FileReader(startName));
			sizeReader = new BufferedReader(new FileReader(sizeName));
			pageReader = new BufferedReader(new FileReader(pageName));

			while ((lineSize = sizeReader.readLine()) != null) {
				sizeArray.add(lineSize);
				sub_string(lineSize);
				hashWord.put(strSplit[0], n);
				n++;
			}

			while ((lineStart = startReader.readLine()) != null) {
				startArray.add(lineStart);
			}

			while ((linePage = pageReader.readLine()) != null) {
				strSplit = new String[2];
				strSplit = linePage.split(" ");
				pageMap.put(m.toString(), Integer.parseInt(strSplit[1]));
				m++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			startReader.close();
			sizeReader.close();
		}

		System.out.println("input words(not start with numbers): ");
		Scanner sc = new Scanner(System.in);
		String target = sc.nextLine();
		sub_string(target);
		String[] keyWord = new String[2];
		keyWord[0] = strSplit[0];
		keyWord[1] = strSplit[1];

		boolean flag;
		try {
			/*
			 * 第一个单词
			 */
			if (hashWord.get(keyWord[0]) != null) {
				pos1 = hashWord.get(keyWord[0]);

				sub_string(startArray.get(pos1));

				targetStart1 = Integer.parseInt(strSplit[1]);
				sub_string(sizeArray.get(pos1));
				targetSize1 = Integer.parseInt(strSplit[1]);
			} else
				System.out.println("not found!");

			ra = new RandomAccessFile(indexName, "r");
			byteArray1 = new byte[targetSize1];
			ra.seek(targetStart1);
			ra.read(byteArray1, 0, targetSize1);
			str1 = new String(byteArray1);
			ft1 = str1.split("]").length;
			strSplit_div1 = new String[ft1];
			strSplit_div1 = str1.split("]");

			for (int i = 0; i < ft1; i++) {
				strSplit_div1[i] = strSplit_div1[i].substring(1);
				int j = strSplit_div1[i].split(" ").length;
				strSplit_div12 = new String[j];
				strSplit_div12 = strSplit_div1[i].split(" ");
				fdt1 = Integer.parseInt(strSplit_div12[1]);
				pageid1 = strSplit_div12[0];
				pageidArray1.add(pageid1);
				freMap1.put(pageid1, fdt1);
			}

			/*
			 * 第二个单词
			 */
			if (hashWord.get(keyWord[1]) != null) {
				pos2 = hashWord.get(keyWord[1]);

				sub_string(startArray.get(pos2));

				targetStart2 = Integer.parseInt(strSplit[1]);
				sub_string(sizeArray.get(pos2));
				targetSize2 = Integer.parseInt(strSplit[1]);
			} else
				System.out.println("not found!");

			byteArray2 = new byte[targetSize2];
			ra.seek(targetStart2);
			ra.read(byteArray2, 0, targetSize2);
			str2 = new String(byteArray2);
			ft2 = str2.split("]").length;
			strSplit_div2 = new String[ft2];
			strSplit_div2 = str2.split("]");

			for (int i = 0; i < ft2; i++) {
				strSplit_div2[i] = strSplit_div2[i].substring(1);
				int j = strSplit_div2[i].split(" ").length;
				strSplit_div22 = new String[j];
				strSplit_div22 = strSplit_div2[i].split(" ");
				fdt2 = Integer.parseInt(strSplit_div22[1]);
				pageid2 = strSplit_div22[0];
				pageidArray2.add(pageid2);
				freMap2.put(pageid2, fdt2);
			}
			/*
			 * 找共同的pageid
			 */
			for (int i = 0; i < pageidArray1.size(); i++) {
				for (int j = 0; j < pageidArray2.size(); j++) {
					if (pageidArray1.get(i).equals(pageidArray2.get(j)))
						samepageArray.add(pageidArray1.get(i));
				}
			}

			ft = samepageArray.size();
			double bm1, bm2;
			for (int i = 0; i < ft; i++) {
				BM = 0;
				bm1 = 0;
				bm2 = 0;
				d = pageMap.get(samepageArray.get(i));

				fdt1 = freMap1.get(samepageArray.get(i));
				fdt2 = freMap2.get(samepageArray.get(i));
				K = k1 * ((1 - b) + b * d / dav);
				// bm1= Math.log((N-ft1+0.5)/(ft1+0.5))*(k1+1)*fdt1/(K+fdt1);
				// bm2=Math.log((N-ft2+0.5)/(ft2+0.5))*(k1+1)*fdt2/(K+fdt2);
				bm1 = Math.log((N - ft1 + 0.5) / (ft + 0.5)) * (k1 + 1) * fdt1
						/ (K + fdt1);
				bm2 = Math.log((N - ft2 + 0.5) / (ft + 0.5)) * (k1 + 1) * fdt2
						/ (K + fdt2);
				BM = bm1 + bm2;
				markArray.add(new mark(samepageArray.get(i), BM));
			}

			Collections.sort(markArray);

			for (int i = 0; i < markArray.size(); i++)
				System.out.println(markArray.get(i));

		} catch (Exception e) {
			e.printStackTrace();
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
