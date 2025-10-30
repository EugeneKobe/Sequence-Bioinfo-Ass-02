/**
 * ILP4MSA_YOUR_NAME.java
 * Author(s): YOUR_NAME
 * Sequence Bioinformatics, WS 25/26
 */
public class ILP4MSA_YOUR_NAME {
	public static void main(String[] args) {
		final String[] seq = {"TCAG", "TCCTA", "CTAG"};

		var countObjectiveTerms = printObjectiveFunction(seq);
		var countCycles2 = printSimpleMixedCycles2(seq);
		var countCycles3 = printSimpleMixedCycles3(seq);

		var countBinConstraints = printBin(seq);
		
		System.out.flush();

		System.err.println(ILP4MSA_YOUR_NAME.class.getName());
		System.err.println("Objective terms: " + countObjectiveTerms);
		System.err.println("2-cycles count:  " + countCycles2);
		System.err.println("3-cycles count:  " + countCycles3);
		System.err.println("Integer count:   " + countBinConstraints);
	}

	private static int printObjectiveFunction(String[] seq) {
		var count = 0;

		// todo: implement output of objective function
		// use 4 for match, 1 for mismatch
		//
		// print using System.out
		System.out.print("max: ");

		return count; // number of terms in objective function
	}

	private static int printSimpleMixedCycles2(String[] seq) {
		var count = 0;

		// todo: output all avoid-mixed-cycle-involving-only-2-sequences constraints
		//
		// print using System.out

		return count; // number of constraints
	}

	private static int printSimpleMixedCycles3(String[] seq) {
		var count = 0;

		// todo: output all avoid-mixed-cycle-involving-using-3-sequences constraints
		//
		// print using System.out


		return count; // number of constraints
	}

	private static int printBin(String[] seq) {
		var count = 0;

		// todo: output bin variables statement
		//
		// print using System.out

		return count; // number of variables in bin statement
	}
}

