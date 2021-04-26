package engine;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

import specs.Payoffs;
import specs.Strategy;

public class Simulator {
	private Map<String,Integer> scores;
	private Map<String,Map<String,Integer>> pairScores;
	private Payoffs payoffs;

	private static class ClassFilter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			return pathname.getName().endsWith(".class");
		}
	}

	private class ScoreName implements Comparable<ScoreName> {
		public String name;
		public int score;

		public ScoreName(int score, String name) {
			this.name = name;
			this.score = score;
		}

		@Override
		public int compareTo(ScoreName o) {
			return this.score - o.score;
		}

	}

	public static String[] getClasses() {
		File dir = new File("./bin/strategies");
		assert dir.isDirectory();

		ArrayList<String> names = new ArrayList<String>();
		for (File f: dir.listFiles(new ClassFilter())) {
			names.add(f.getName().substring(0, f.getName().length() - 6));
		}

		String[] result = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			result[i] = names.get(i);
		}
		return result;
	}

	public Simulator(Payoffs p) {
		this(p, getClasses());
	}

	public Simulator(Payoffs p, String... strategies) {
		payoffs = p;
		scores = new TreeMap<String,Integer>();
		pairScores = new HashMap<String,Map<String,Integer>>();

		for (String name: strategies) {
			if (instantiate(name) != null) {
				scores.put(name, 0);
				pairScores.put(name, new TreeMap<String,Integer>());
			}
		}
	}

	public Strategy instantiate(String name) {
		try {
			return (Strategy)(Class.forName("strategies." + name).newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("Failed to instantiate " + name);
			return null;
		}
	}

	public int scoreFor(String name) {
		return scores.get(name);
	}

	public int scoreAgainst(String name, String opponent) {
		return pairScores.get(name).get(opponent);
	}

	public void runTournament(int roundLength) {
		for (String one: scores.keySet()) {
			for (String two: scores.keySet()) {
				if (!pairScores.get(one).containsKey(two)) {
					runContest(one, two, roundLength);
				}
			}
		}
	}

	private void runContest(String one, String two, int roundLength) {
		Strategy s1 = instantiate(one);
		Strategy s2 = instantiate(two);
		if (s1 != null && s2 != null) {
			Contest c = new Contest(s1, s2, payoffs);
			c.rounds(roundLength);
			scores.put(one, scores.get(one) + c.oneScore());
			if (!one.equals(two)) {
				scores.put(two, scores.get(two) + c.twoScore());
			}
			pairScores.get(one).put(two, c.oneScore());
			if (!one.equals(two)) {
				pairScores.get(two).put(one, c.twoScore());
			}
		}
	}

	public int getLongestNameLength() {
		int max = 0;
		for (String s: scores.keySet()) {
			if (s.length() > max) {
				max = s.length();
			}
		}
		return max;
	}

	public void pad(String content, int colWidth, StringBuilder sb) {
		sb.append(content);
		for (int i = content.length(); i < colWidth; ++i) {
			sb.append(' ');
		}
	}

	public void numPad(int value, int colWidth, StringBuilder sb) {
		rightPad(Integer.toString(value), colWidth, sb);
	}

	public void rightPad(String content, int colWidth, StringBuilder sb) {
		for (int i = content.length(); i < colWidth; ++i) {
			sb.append(' ');
		}
		sb.append(content);
	}

	public String report() {
		StringBuilder sb = new StringBuilder();
		int colWidth = getLongestNameLength() + 2;
		createHeader(sb, colWidth);
		createRows(sb, colWidth);
		return sb.toString();
	}

	private void createRows(StringBuilder sb, int colWidth) {
		for (ScoreName rowName: getRowNames()) {
			createRow(sb, colWidth, rowName.name);
		}
	}

	private void createRow(StringBuilder sb, int colWidth, String label) {
		pad(label, colWidth, sb);
		numPad(scores.get(label), colWidth, sb);
		for (String col: pairScores.get(label).keySet()) {
			numPad(pairScores.get(label).get(col), colWidth, sb);
		}
		sb.append('\n');
	}

	private void createHeader(StringBuilder sb, int colWidth) {
		pad("Name", colWidth, sb);
		rightPad("Total", colWidth, sb);
		for (String top: scores.keySet()) {
			rightPad(top, colWidth, sb);
		}
		sb.append('\n');
	}

	private ArrayList<ScoreName> getRowNames() {
		ArrayList<ScoreName> scorenames = new ArrayList<ScoreName>();
		for (String name: scores.keySet()) {
			scorenames.add(new ScoreName(scores.get(name), name));
		}
		Collections.sort(scorenames);
		Collections.reverse(scorenames);
		return scorenames;
	}

	public static void main(String[] args) {
		int numRounds = args.length == 0 ? 100 : Integer.parseInt(args[0]);
		Simulator rr = new Simulator(new BasicPayoffs());
		rr.runTournament(numRounds);
		System.out.println(rr.report());
	}
}
