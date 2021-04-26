package tictactoe.learn;

public class VeryBad {
	private String s;
	
	public VeryBad(String s) {
		this.s = s;
	}
	
	public String toString() {
		return s;
	}
	
	public int hashCode() {return 15;}
	
	public boolean equals(Object other) {
		return this.toString().equals(other.toString());
	}
}
