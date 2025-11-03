/**
 * ILP4MSA_YOUR_NAME.java
 * Author(s): YOUR_NAME
 * Sequence Bioinformatics, WS 25/26
 */
public class ILP4MSA_BOATENG_HOTH {
	public static void main(String[] args) {
		final String[] seq = {"TCAG", "TCCTA", "CTAG"};

		var countObjectiveTerms = printObjectiveFunction(seq);
		var countCycles2 = printSimpleMixedCycles2(seq);
		var countCycles3 = printSimpleMixedCycles3(seq);

		var countBinConstraints = printBin(seq);
		
		System.out.flush();

		System.err.println(ILP4MSA_BOATENG_HOTH.class.getName());
		System.err.println("Objective terms: " + countObjectiveTerms);
		System.err.println("2-cycles count:  " + countCycles2);
		System.err.println("3-cycles count:  " + countCycles3);
		System.err.println("Integer count:   " + countBinConstraints);
	}

    private static int printObjectiveFunction(String[] seq) {
        int count = 0;
        System.out.print("max: ");
        boolean first = true;

        for (int i = 0; i < seq.length; i++) {
            for (int j = 0; j < seq[i].length(); j++) {
                for (int p = i + 1; p < seq.length; p++) {
                    for (int q = 0; q < seq[p].length(); q++) {
                        int score = (seq[i].charAt(j) == seq[p].charAt(q)) ? 4 : 1;
                        if (!first) System.out.print(" + ");
                        System.out.print(score + " x" + (i + 1) + (j + 1) + "_" + (p + 1) + (q + 1));
                        first = false;
                        count++;
                    }
                }
            }
        }
        System.out.println(";");

		return count; // number of terms in objective function
	}

    private static int printSimpleMixedCycles2(String[] seq) {
        var count = 0;

        for (int i = 0; i < seq.length; i++) {
            for (int p = i + 1; p < seq.length; p++) {
                int n = seq[i].length();
                int m = seq[p].length();

                // Column uniqueness: each residue aligns to at most one
                for (int j = 0; j < n; j++) {
                    for (int q1 = 0; q1 < m; q1++) {
                        for (int q2 = q1 + 1; q2 < m; q2++) {
                            System.out.println("x" + (i + 1) + (j + 1) + "_" + (p + 1) + (q1 + 1)
                                    + " + x" + (i + 1) + (j + 1) + "_" + (p + 1) + (q2 + 1) + " <= 1;");
                            count++;
                        }
                    }
                }

                for (int q = 0; q < m; q++) {
                    for (int j1 = 0; j1 < n; j1++) {
                        for (int j2 = j1 + 1; j2 < n; j2++) {
                            System.out.println("x" + (i + 1) + (j1 + 1) + "_" + (p + 1) + (q + 1)
                                    + " + x" + (i + 1) + (j2 + 1) + "_" + (p + 1) + (q + 1) + " <= 1;");
                            count++;
                        }
                    }
                }

                // Anti-crossing: for i<j and k<l, xi_l + xjk <= 1
                for (int j = 0; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        for (int q = 0; q < m; q++) {
                            for (int r = q + 1; r < m; r++) {
                                System.out.println("x" + (i + 1) + (j + 1) + "_" + (p + 1) + (r + 1)
                                        + " + x" + (i + 1) + (k + 1) + "_" + (p + 1) + (q + 1) + " <= 1;");
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }


    private static int printSimpleMixedCycles3(String[] seq) {
        int count = 0;

        for (int i = 0; i < seq.length; i++) {
            for (int p = i + 1; p < seq.length; p++) {
                for (int r = p + 1; r < seq.length; r++) {
                    for (int j = 0; j < seq[i].length(); j++) {
                        for (int q = 0; q < seq[p].length(); q++) {
                            for (int k = 0; k < seq[r].length(); k++) {
                                System.out.println(
                                        "x" + (i + 1) + (j + 1) + "_" + (p + 1) + (q + 1) + " + " +
                                                "x" + (i + 1) + (j + 1) + "_" + (r + 1) + (k + 1) + " + " +
                                                "x" + (p + 1) + (q + 1) + "_" + (r + 1) + (k + 1) + " <= 2;");
                                count++;
                            }
                        }
                    }
                }
            }
        }

		return count; // number of constraints
	}

    private static int printBin(String[] seq) {
        int count = 0;
        System.out.print("bin ");
        boolean first = true;

        for (int i = 0; i < seq.length; i++) {
            for (int j = 0; j < seq[i].length(); j++) {
                for (int p = i + 1; p < seq.length; p++) {
                    for (int q = 0; q < seq[p].length(); q++) {
                        if (!first) System.out.print(", ");
                        System.out.print("x" + (i + 1) + (j + 1) + "_" + (p + 1) + (q + 1));
                        first = false;
                        count++;
                    }
                }
            }
        }
        System.out.println(";");
		return count; // number of variables in bin statement
	}
}

