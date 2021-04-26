package tictactoe.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	Game g = new Game();

	@Test
	public void test1() {
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 0);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(1, 1);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 2);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 2);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 0);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(1, 0);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 1);
		assertEquals(Status.X_WIN, g.getStatus());
	}

	@Test(expected=IllegalStateException.class)
	public void test1a() {
		test1();
		g.takeTurn(1, 2);
	}

	@Test(expected=IllegalStateException.class)
	public void test2() {
		g.takeTurn(0, 1);
		g.takeTurn(0, 1);
	}
	
	@Test
	public void test3() {
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 0);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(1, 1);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 2);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 1);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 2);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 1);
		assertEquals(Status.O_WIN, g.getStatus());
	}
	
	@Test(expected=IllegalStateException.class)
	public void test3a() {
		test3();
		g.takeTurn(1, 0);
	}
	
	@Test
	public void test4() {
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 0);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(1, 1);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 2);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 1);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 1);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(0, 2);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(2, 0);
		assertEquals(Symbol.O, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(1, 0);
		assertEquals(Symbol.X, g.currentPlayer());
		assertEquals(Status.CONTINUE, g.getStatus());
		g.takeTurn(1, 2);
		assertEquals(Status.DRAW, g.getStatus());
	}
	
	@Test(expected=IllegalStateException.class)
	public void test4a() {
		test4();
		g.takeTurn(0, 0);
	}
}
