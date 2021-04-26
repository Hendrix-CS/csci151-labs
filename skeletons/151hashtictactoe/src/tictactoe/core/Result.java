package tictactoe.core;

public class Result {
	private Status status;
	private Coord endpoint1, endpoint2;
	
	public Result(Status status) {
		this.status = status;
	}
	
	public Result(Status status, Coord endpoint1, Coord endpoint2) {
		this.status = status;
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
	}
	
	public Status getStatus() {return status;}
	
	public Coord getStart() {return endpoint1;}
	
	public Coord getEnd() {return endpoint2;}
}
