package assignment02;

import assignment01.FastA_YOUR_NAME;

import java.io.IOException;
import java.util.function.BiFunction;

/**
 * NW_YOUR_NAME.java
 * Author(s): YOUR_NAME
 * Sequence Bioinformatics, WS 25/26
 */
public class NW_YOUR_NAME {
	/**
	 * run the Needleman-Wunsch algorithm
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if(args.length!=5)
			throw new IOException("Usage: NW_YOUR_NAME infile mode match-score mismatch-score gap-penalty");
		var inputFile=args[0];
		var mode=args[1];
		if(!mode.equals("linear") && !mode.equals("quadratic") )
			throw new IOException("Illegal mode="+mode+", must be one of \"quadratic\" or \"linear\"");
		var matchScore= Integer.parseInt(args[2]);
		var mismatchScore= Integer.parseInt(args[3]);
		var gapPenalty= Integer.parseInt(args[4]);
		// todo: implement check that matchScore and gapPenalty are positive, while mismatchScore is negative, throw exception if not

		System.out.println(NW_YOUR_NAME.class.getName());
		System.out.println("File: "+inputFile);
		System.out.println("mode: "+mode);
		System.out.println("match:    "+matchScore);
		System.out.println("mismatch: "+mismatchScore);
		System.out.println("gap:      "+gapPenalty);

		var sequences= FastA_YOUR_NAME.read(inputFile);
		// todo: implement check that precisely two sequences given, throw exception if not

		var headerX=sequences.get(0).header();
		var headerY=sequences.get(1).header();
		var x=sequences.get(0).sequence();
		var y=sequences.get(1).sequence();

		// the scoring function:
		BiFunction<Character,Character,Integer> s=(a,b)-> (a==b?matchScore:mismatchScore);

		// compute alignment:
		Alignment alignment;
		if(mode.equals("quadratic"))
			alignment =runQuadraticNW(x,y,s,gapPenalty);
		else
			alignment = runLinearNW(x,y,s,gapPenalty);

		// report alignment:
		System.out.println("\nAlignment: " + alignment.score());
		var top=Math.min(alignment.xAligned().length(),alignment.yAligned().length());
		for(var a=0;a<top;a+=50) {
			var b=Math.min(top,a+50);
			System.out.printf("%-20s %s%n",headerX, alignment.xAligned().substring(a,b));
			System.out.printf("%-20s %s%n",headerY, alignment.yAligned().substring(a,b));
			System.out.println();
		}
	}

	/**
	 * Needleman-Wunsch using quadratic space
	 * @return score and aligned sequences
	 */
	public static Alignment runQuadraticNW(String x, String y, BiFunction<Character,Character,Integer> s, int d) {
		// todo: implement the Needleman-Wunsch algorithm using quadratic space

		return new Alignment(0,x,y);
	}

	/**
	 * Needleman-Wunsch using linear space
	 * @return score and aligned sequences
	 */
	public static Alignment runLinearNW(String x, String y, BiFunction<Character,Character,Integer> s, int d) {
		// todo: implement the Needleman-Wunsch algorithm using linear space (Hirschberg algorithm)

		return new Alignment(0,x,y);
	}

	/**
	 * an alignment
	 */
	public record Alignment(double score, String xAligned, String yAligned){}
}

