package Invert_Index;

import java.util.ArrayList;

public class preSum {
    private String word;
    private int pageid;
    private int fre;
    private ArrayList<Integer> pos=new ArrayList<Integer>();
	public preSum(String word, int pageid, int fre, int pos) {
		super();
		this.word = word;
		this.pageid = pageid;
		this.fre = fre;
		this.pos.add(pos);
	}
	public preSum(Tuples temp) {
		super();
		this.word = temp.getWord();
		this.pageid =temp. getPageid();
		this.fre = 1;
		this.pos.add(temp.getPosition());
	}
	public void addPos(int pos) {
		this.pos.add(pos);
		fre++;
	}
	
	
	@Override
	public String toString() {
		StringBuilder strbu=new StringBuilder();
//		strbu.append(word);
//		strbu.append(" ");
//		strbu.append(pageid);
//		strbu.append(" ");
//		strbu.append(fre);
//		strbu.append(" ");
		strbu.append(word+" "+pageid+" "+fre+" ");
		for(int i=0;i<pos.size();i++)
		{
			strbu.append(pos.get(i)+" ");
			//strbu.append(" ");
		}
		strbu.append("]");//分割每个pageid
		//return word + " "+ pageid + " " + fre+ " " + pos;
		return strbu.toString();
	}
	
    
}
