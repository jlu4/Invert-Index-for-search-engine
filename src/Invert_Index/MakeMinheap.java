/*
 * 进行minHeap处理
 * test
 * 上一个class：preProcess.class
 * 下一个class：lexicon.class
 */
package Invert_Index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeMinheap {
	public static ArrayList<Format> preSumArray = new ArrayList<Format>();

	public static void main(String[] argv) throws IOException {

		BufferedWriter minHeapOut = null;

		BufferedReader tupleIn0 = null;
		BufferedReader tupleIn1 = null;
		BufferedReader tupleIn2 = null;
		BufferedReader tupleIn3 = null;
		BufferedReader tupleIn4 = null;
		BufferedReader tupleIn5 = null;
		BufferedReader tupleIn6 = null;
		BufferedReader tupleIn7 = null;
		BufferedReader tupleIn8 = null;

		String filename0 = "src/Invert_Index/Tuples/Tuples0";
		String filename1 = "src/Invert_Index/Tuples/Tuples1";
		String filename2 = "src/Invert_Index/Tuples/Tuples2";
		String filename3 = "src/Invert_Index/Tuples/Tuples3";
		String filename4 = "src/Invert_Index/Tuples/Tuples4";
		String filename5 = "src/Invert_Index/Tuples/Tuples5";
		String filename6 = "src/Invert_Index/Tuples/Tuples6";
		String filename7 = "src/Invert_Index/Tuples/Tuples7";
		String filename8 = "src/Invert_Index/Tuples/Tuples8";
		String minHeapName = "src/Invert_Index/minHeap/minHeap";
		MakeTurples.createFile(minHeapName);
		minHeapOut = new BufferedWriter(new FileWriter(minHeapName));

		String line0 = " ";
		String line1 = " ";
		String line2 = " ";
		String line3 = " ";
		String line4 = " ";
		String line5 = " ";
		String line6 = " ";
		String line7 = " ";
		String line8 = " ";

		Tuples temp0 = null;
		Tuples temp1 = null;
		Tuples temp2 = null;
		Tuples temp3 = null;
		Tuples temp4 = null;
		Tuples temp5 = null;
		Tuples temp6 = null;
		Tuples temp7 = null;
		Tuples temp8 = null;
		Tuples temp = null;
		Tuples lasttemp = null;
		Format temp_pre = null;

		try {
			tupleIn0 = new BufferedReader(new FileReader(filename0));
			tupleIn1 = new BufferedReader(new FileReader(filename1));
			tupleIn2 = new BufferedReader(new FileReader(filename2));
			tupleIn3 = new BufferedReader(new FileReader(filename3));
			tupleIn4 = new BufferedReader(new FileReader(filename4));
			tupleIn5 = new BufferedReader(new FileReader(filename5));
			tupleIn6 = new BufferedReader(new FileReader(filename6));
			tupleIn7 = new BufferedReader(new FileReader(filename7));
			tupleIn8 = new BufferedReader(new FileReader(filename8));

			MinHeap4 tuplesHeap = new MinHeap4(15);

			boolean flag = true;// 还有数据
			boolean flag0 = true;
			boolean flag1 = true;
			boolean flag2 = true;
			boolean flag3 = true;
			boolean flag4 = true;
			boolean flag5 = true;
			boolean flag6 = true;
			boolean flag7 = true;
			boolean flag8 = true;

			int id = -1;

			if ((line0 = tupleIn0.readLine()) != null) {
				temp0 = new Tuples(line0, 0);
				tuplesHeap.insert(temp0);
				flag0 = true;
			} else
				flag0 = false;

			if ((line1 = tupleIn1.readLine()) != null) {
				temp1 = new Tuples(line1, 1);
				tuplesHeap.insert(temp1);
				flag1 = true;
			} else
				flag1 = false;

			if ((line2 = tupleIn2.readLine()) != null) {
				temp2 = new Tuples(line2, 2);
				tuplesHeap.insert(temp2);
				flag2 = true;
			} else
				flag2 = false;

			if ((line3 = tupleIn3.readLine()) != null) {
				temp3 = new Tuples(line3, 3);
				tuplesHeap.insert(temp3);
				flag3 = true;
			} else
				flag3 = false;

			if ((line4 = tupleIn4.readLine()) != null) {
				temp4 = new Tuples(line4, 4);
				tuplesHeap.insert(temp4);
				flag4 = true;
			} else
				flag4 = false;

			if ((line5 = tupleIn5.readLine()) != null) {
				temp5 = new Tuples(line5, 5);
				tuplesHeap.insert(temp5);
				flag5 = true;
			} else
				flag5 = false;

			if ((line6 = tupleIn6.readLine()) != null) {
				temp6 = new Tuples(line6, 6);
				tuplesHeap.insert(temp6);
				flag6 = true;
			} else
				flag6 = false;

			if ((line7 = tupleIn7.readLine()) != null) {
				temp7 = new Tuples(line7, 7);
				tuplesHeap.insert(temp7);
				flag7 = true;
			} else
				flag7 = false;

			if ((line8 = tupleIn8.readLine()) != null) {
				temp8 = new Tuples(line8, 8);
				tuplesHeap.insert(temp8);
				flag8 = true;
			} else
				flag8 = false;

			if (flag0 || flag1 || flag2 || flag3 || flag4 || flag5 || flag6
					|| flag7 || flag8)
				flag = true;
			else
				flag = false;

			tuplesHeap.sort();
			tuplesHeap.remove();
			while (flag) {
				temp = (Tuples) tuplesHeap.remove();
				id = temp.getTupleId();

				temp_pre = new Format(temp);
				if (lasttemp == null) {
					preSumArray.add(temp_pre);
//					minHeapOut.write(preSumArray.get(preSumArray.size() - 1)
//							.toString() + '\n');
			//		System.out.println(preSumArray.get(preSumArray.size() - 1)
			//				.toString());
				} else {
					if (temp.getWord().compareTo(lasttemp.getWord()) != 0) {
			//			System.out.println(preSumArray.get(
			//					preSumArray.size() - 1).toString());
						minHeapOut.write(preSumArray
								.get(preSumArray.size() - 1).toString() + '\n');
						minHeapOut.flush();
						preSumArray.clear();
						preSumArray.add(temp_pre);
					} else if (temp.getPageid() != lasttemp.getPageid()) {
			//			System.out.println(preSumArray.get(
			//					preSumArray.size() - 1).toString());
						minHeapOut.write(preSumArray
								.get(preSumArray.size() - 1).toString() + '\n');
						minHeapOut.flush();
						preSumArray.add(temp_pre);
					} else {
						preSumArray.get(preSumArray.size() - 1).addPos(
								temp.getPosition());
					}
				}
				lasttemp = temp;

				switch (id) {
				case 0: {
					if (flag0 == true && (line0 = tupleIn0.readLine()) != null) {
						temp0 = new Tuples(line0, 0);
				//		 System.out.println(id + "   " + temp0);
						tuplesHeap.insert(temp0);
						flag0 = true;
					} else
						flag0 = false;
					break;
				}
				case 1: {
					if (flag1 == true && (line1 = tupleIn1.readLine()) != null) {
						temp1 = new Tuples(line1, 1);
			//			 System.out.println(id + "   " + temp1);
						tuplesHeap.insert(temp1);
						flag1 = true;
					} else
						flag1 = false;
					break;
				}
				case 2: {
					if (flag2 == true && (line2 = tupleIn2.readLine()) != null) {
						temp2 = new Tuples(line2, 2);
			//			 System.out.println(id + "   " + temp2);
						tuplesHeap.insert(temp2);
						flag2 = true;
					} else
						flag2 = false;
					break;
				}
				case 3: {
					if (flag3 == true && (line3 = tupleIn3.readLine()) != null) {
						temp3 = new Tuples(line3, 3);
			//			 System.out.println(id + "   " + temp3);
						tuplesHeap.insert(temp3);
						flag3 = true;
					} else
						flag3 = false;
					break;
				}
				case 4: {
					if (flag4 == true && (line4 = tupleIn4.readLine()) != null) {
						temp4 = new Tuples(line4, 4);
			//			 System.out.println(id + "   " + temp4);
						tuplesHeap.insert(temp4);
						flag4 = true;
					} else
						flag4 = false;
					break;
				}
				case 5: {
					if (flag5 == true && (line5 = tupleIn5.readLine()) != null) {
						temp5 = new Tuples(line5, 5);
				//		 System.out.println(id + "   " + temp5);
						tuplesHeap.insert(temp5);
						flag5 = true;
					} else
						flag5 = false;
					break;
				}
				case 6: {
					if (flag6 == true && (line6 = tupleIn6.readLine()) != null) {
						temp6 = new Tuples(line6, 6);
				//		 System.out.println(id + "   " + temp6);
						tuplesHeap.insert(temp6);
						flag6 = true;
					} else
						flag6 = false;
					break;
				}
				case 7: {
					if (flag7 == true && (line7 = tupleIn7.readLine()) != null) {
						temp7 = new Tuples(line7, 7);
				//		 System.out.println(id + "   " + temp7);
						tuplesHeap.insert(temp7);
						flag7 = true;
					} else
						flag7 = false;
					break;
				}
				case 8: {
					if (flag8 == true && (line8 = tupleIn8.readLine()) != null) {
						temp8 = new Tuples(line8, 8);
			//			 System.out.println(id + "   " + temp8);
						tuplesHeap.insert(temp8);
						flag8 = true;
					} else
						flag8 = false;
					break;
				}
				default:
					continue;
				}
				tuplesHeap.sort();
				if (flag0 || flag1 || flag2 || flag3 || flag4 || flag5 || flag6
						|| flag7 || flag8)
					flag = true;
				else
					flag = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			minHeapOut.flush();
			minHeapOut.close();
		}
	}
	
	
	
}
