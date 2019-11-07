package no.hvl.dat100.jplab12.oppgave4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import no.hvl.dat100.jplab12.oppgave3.*;
import no.hvl.dat100.jplab12.common.TODO;

public class SkrivBlogg {

	private static String MAPPE = System.getProperty("user.dir") + "/src/no/hvl/dat100/jplab12/tests/";

	public static boolean skriv(Blogg samling, String filnavn) {

		File fil = new File(filnavn);
		
		try {
			FileWriter fw = new FileWriter(fil);
			fw.write(samling.toString());
			fw.close();
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
