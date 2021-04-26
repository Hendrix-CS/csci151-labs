package specs;

public interface Strategy {
	public void opponentMove(boolean isDefecting);
	public boolean isDefecting();
}
