package Invert_Index;

public class mark  implements Comparable{
    public String pageid;
    public Double score;
	public mark(String pageid, double score) {
		super();
		this.pageid = pageid;
		this.score = score;
	}
	@Override
	public String toString() {
		return "mark [pageid=" + pageid + ", score=" + score + "]";
	}
	
	@Override
	public int compareTo(Object arg0) {
		return ((mark) arg0).score.compareTo(this.score);
	}
    
}
