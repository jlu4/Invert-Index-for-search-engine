package Invert_Index;

public class Tuples implements Comparable{
    private String word;
    private int pageid;
    private int position;
    public static String[] strSplit;
    public int TupleId;
    
	public Tuples(String word, int pageid, int position) {
		super();
		this.word = word;
		this.pageid = pageid;
		this.position = position;
	}
	public Tuples(Tuples t) {
		super();
		this.word = t.getWord();
		this.pageid = t.getPageid();
		this.position = t.getPosition();
	}
	
	public Tuples(String str,int id)
	{
		super();
		strSplit = new String[3];
		strSplit = str.split(" ");		
		this.word = strSplit[0];
		this.pageid =Integer.parseInt(strSplit[1]);
		this.position = Integer.parseInt(strSplit[2]);
		TupleId=id;
	}
	
	public int getTupleId() {
		return TupleId;
	}
	public void setTupleId(int tupleId) {
		TupleId = tupleId;
	}
	public Tuples() {
		super();
	}
	@Override
	public String toString() {
		return word + " " + pageid + " "+ position;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getPageid() {
		return pageid;
	}
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}  
	public void swap(Tuples t)
	{
		this.word=t.getWord();
		this.pageid=t.getPageid();
		this.position=t.getPosition();
	}
	
	@Override
	public int compareTo(Object t) {
		// TODO Auto-generated method stub
		if( this.getWord().compareTo(((Tuples) t).getWord())!=0)
		    return this.getWord().compareTo(((Tuples) t).getWord());
		else if(this.getPageid()!=((Tuples) t).getPageid())
			return this.getPageid()-((Tuples) t).getPageid();
		else if(this.getPosition()!=((Tuples) t).getPosition())
			return this.getPosition()-((Tuples) t).getPosition();
		else
			return 0;
	}
	

}
