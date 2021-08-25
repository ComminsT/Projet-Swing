package entite;

import java.util.ArrayList;

import dao.AgentDAO;
import dao.LocataireDAO;
import dao.ProprietaireDAO;

public class Checker {

	// Vérification d'adresse mail

	public static boolean mailcheckerp(String mail) {
		Database.Connect();
		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		ArrayList<String> mails = proprietaireDAO.getAllMail();
		if (mail.matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			System.out.println("Mail ok");
			return true;
		} else {
			System.out.println("Mail FAIL");
			return false;
		}
	}

	public static boolean mailcheckerl(String mail) {
		Database.Connect();
		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<String> mails = locataireDAO.getAllMail();
		if (mail.matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			System.out.println("Mail ok");
			return true;
		} else {
			System.out.println("Mail FAIL");
			return false;
		}
	}

	public static boolean mailcheckera(String mail) {
		Database.Connect();
		AgentDAO agentDAO = new AgentDAO();
		ArrayList<String> mails = agentDAO.getAllMail();
		if (mail.matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			System.out.println("Mail ok");
			return true;
		} else {
			System.out.println("Mail FAIL");
			return false;
		}
	}
	// FIN Vérification d'adresse mail

	public static boolean datechecker(String date) {
		if (date.matches(
				"^(?:(?:19[0-9]{2}|200[0-9]|2010)([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:19(?:0[48]|[2648][048]|[13579][26])|2000|200[48])([-/.]?)0?2\\2(?:29))$")) {
			System.out.println("Date ok");
			return true;
		} else {
			System.out.println("Date fail");
			return false;
		}
	}

	public static String phonenumber(String tel) {
		if (tel.substring(3, 4).equals("+")) {
			System.out.println("ok");
			return tel.substring(3, tel.length());
		} else
			return tel.substring(4, tel.length());

	}

	// Verification numero de telephone
	public static boolean phonecheckerp(String tel) {
		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		ArrayList<String> phones = proprietaireDAO.getAllPhone();
		if (tel.matches(
				"^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
				&& !phones.contains(tel)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean phonecheckerl(String tel) {
		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<String> phones = locataireDAO.getAllPhone();
		if (tel.matches(
				"^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
				&& !phones.contains(tel)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean phonecheckera(String tel) {
		AgentDAO agentDAO = new AgentDAO();
		ArrayList<String> phones = agentDAO.getAllPhone();
		if (tel.matches(
				"^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
				&& !phones.contains(tel)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean mailmodifierl(String mail, Locataire locataire) {
		Database.Connect();
		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<String> mails = locataireDAO.getAllMail();
		if (mail.equals(locataire.getMail())) {
			return true;
		} else if (mail.matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean phonemodifierl(String tel, Locataire locataire) {
		Database.Connect();
		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<String> tels = locataireDAO.getAllPhone();
		if (tel.equals(locataire.getTel())) {
			return true;
		} else if (tel.matches(
				"^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
				&& !tels.contains(tel)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean phonemodifierp(String tel, Proprietaire proprietaire) {
		Database.Connect();
	ProprietaireDAO proprietaireDAO=new ProprietaireDAO();
		ArrayList<String> tels = proprietaireDAO.getAllPhone();
		if (tel.equals(proprietaire.getTel())) {
			return true;
		} else if (tel.matches(
				"^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
				&& !tels.contains(tel)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean mailmodifierp(String mail, Proprietaire proprietaire) {
		Database.Connect();
		ProprietaireDAO proprietaireDAO=new ProprietaireDAO();
		ArrayList<String> mails = proprietaireDAO.getAllMail();
		if (mail.equals(proprietaire.getMail())) {
			return true;
		} else if (mail.matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			return true;
		} else {
			return false;
		}
	}
	

}
