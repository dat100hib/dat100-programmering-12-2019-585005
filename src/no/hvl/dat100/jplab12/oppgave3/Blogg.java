package no.hvl.dat100.jplab12.oppgave3;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;
import no.hvl.dat100.jplab12.oppgave2.Bilde;
import no.hvl.dat100.jplab12.oppgave2.Tekst;

public class Blogg {

	private Innlegg[] innleggTabell;
	private int nesteLedig;

	public Blogg() {
		innleggTabell = new Innlegg[20];
		nesteLedig = 0;
	}

	public Blogg(int lengde) {
		innleggTabell = new Innlegg[lengde];
		nesteLedig = 0;

	}

	public int getAntall() {
		return nesteLedig;
	}

	public Innlegg[] getSamling() {
		return innleggTabell;
	}

	public int finnInnlegg(Innlegg innlegg) {

		for (int i = 0; i < innleggTabell.length; i++) {
			if (innlegg.erLik(innleggTabell[i])) {
				return i;
			}
		}
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
		int i = 0;
		while (innleggTabell[i] != null) {
			if (innleggTabell[i].erLik(innlegg)) {
				return true;
			}
			i++;
		}
		return false;
	}

	public boolean ledigPlass() {

		for (int i = 0; i < innleggTabell.length; i++) {
			if (innleggTabell[i] == null) {
				return true;
			}
		}
		return false;
	}

	public boolean leggTil(Innlegg innlegg) {
		if (!finnes(innlegg) && ledigPlass()) {
			innleggTabell[nesteLedig] = innlegg;
			nesteLedig++;
			return true;
		}
		return false;
	}

	public String toString() {
		String utskrift = nesteLedig + "\n";
		for (Innlegg i : innleggTabell) {
			if (i instanceof Bilde) {
				Bilde b = (Bilde) i;
				utskrift = utskrift + b.toString();
			} else if (i instanceof Tekst) {
				Tekst t = (Tekst) i;
				utskrift = utskrift + t.toString();
			}
		}
		return utskrift;
	}

	public void utvid() {
		Innlegg[] nyInnleggTabell = new Innlegg[innleggTabell.length * 2];
		for (int i = 0; i < innleggTabell.length; i++) {
			nyInnleggTabell[i] = innleggTabell[i];
		}
		innleggTabell = nyInnleggTabell;
	}

	/**
	 * @param innlegget som skal legges til hvis det er ledig plass
	 * @if om innlegget allerede finnes, return false
	 * @if hvis det er plass i tabellen legg til, hvis det ikke er plass, utvid
	 * @return true om den ble lagt til, false hvis ikke
	 */
	public boolean leggTilUtvid(Innlegg innlegg) {
		for (int i = 0; i < innleggTabell.length; i++) {
			if (finnes(innlegg)) {
				return false;
			}
		}
		if (innleggTabell[nesteLedig - 1] != null) {
			utvid();
			leggTil(innlegg);
			return true;
		} else if (innleggTabell[nesteLedig - 1] == null) {
			leggTil(innlegg);
			return true;
		}
		return false;
	}
//		for (int i = 0; i < innleggTabell.length; i++) {
//			if (finnes(innlegg)) {
//				return false;
//			}
//		}
//		if (ledigPlass()) {
//			leggTil(innlegg);
//			return true;
//		} else if (!ledigPlass()) {
//			utvid();
//			leggTil(innlegg);
//			return true;
//		}
//		return false;
//	}

	/**
	 * @param innlegget som skal slettes hvis det finnes fra før
	 * @return {@summary slette noe == null?}
	 */
	public boolean slett(Innlegg innlegg) {
		int pos = finnInnlegg(innlegg);
		
			if (pos == innlegg.getId()) {
			innleggTabell[pos] = null;
			nesteLedig--;
				return true;
			}
		
		return false;
	}

	/**
	 * @param hva er/gjør keyword her? {@summary går igjennom tabellen og bruke
	 *            getId til å hente ut Id}
	 * @return
	 */
	public int[] search(String keyword) {
		int[] idTab = new int[getAntall()];
		for (int i = 0; i < innleggTabell.length; i++) {
			idTab[i] = innleggTabell[i].getId();
		}
		return idTab;
	}
}