package entite;

import java.util.ArrayList;
import java.util.Date;

import dao.AgentDAO;
import dao.LocataireDAO;
import dao.ProprietaireDAO;

public class Checker {

	// Vérification d'adresse mail

	public static boolean mailcheckerp(String mail) {
		Database.Connect();
		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		ArrayList<String> mails = proprietaireDAO.getAllMail();
		if (mail.toLowerCase().matches(
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
		if (mail.toLowerCase().matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			System.out.println("Mail ok");
			return true;
		} else {
			System.out.println("Mail FAIL");
			return false;
		}
	}

	public static boolean mailcheckera(String mail,int id) {
		Database.Connect();
		AgentDAO agentDAO = new AgentDAO();
		Agent agent = agentDAO.getById(id);
		ArrayList<String> mails = agentDAO.getAllMail();
		if (mail.toLowerCase().matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)||mail.equals(agent.getMail())) {
			System.out.println("Mail ok");
			return true;
		} else {
			System.out.println("Mail FAIL");
			return false;
		}
	}
	public static boolean mailcheckeraCreation(String mail) {
		Database.Connect();
		AgentDAO agentDAO = new AgentDAO();
		ArrayList<String> mails = agentDAO.getAllMail();
		if (mail.toLowerCase().matches(
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
	public static boolean phonemodifiera(String tel,int id) {
		AgentDAO agentDAO = new AgentDAO();
		ArrayList<String> phones = agentDAO.getAllPhone();
		Agent agent=agentDAO.getById(id);
		if(agent.getTel().equals(tel)) {
			return true;
		}else if (tel.matches(
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
		} else if (mail.toLowerCase().matches(
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
		} else if (mail.toLowerCase().matches(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				&& !mails.contains(mail)) {
			return true;
		} else {
			return false;
		}
	}
	public static String getMonthName() {
		Date date = new Date();
		String mois="";
		int m=date.getMonth();
		if(m==0) {
			mois="Janvier";
		}else if(m==1) {
			mois="Février";
		}else if(m==2) {
			mois="Mars";
		}else if(m==3) {
			mois="Avril";
		}else if(m==4) {
			mois="Mai";
		}else if(m==5) {
			mois="Juin";
		}else if(m==6) {
			mois="Juillet";
		}else if(m==7) {
			mois="Août";
		}else if(m==8) {
			mois="Septembre";
		}else if(m==9) {
			mois="Octobre";
		}else if(m==10) {
			mois="Novembre";
		}else if(m==11) {
			mois="Décembre";
		}
		return mois;}
	
	public static String getDateTime() {
		Date date = new Date();
		String seconde=String.valueOf(date.getSeconds());
		String minute = String.valueOf(date.getMinutes());
		String heure=String.valueOf(date.getHours());
		String jour = "";
		if(date.getDate()<10) {
			jour="0"+date.getDate();
		}else {
			jour=date.getDate()+"";
		}
		String moisnbr="";
		if(date.getMonth()<10) {
			 moisnbr ="0"+(date.getMonth()+1);
		}else {
			moisnbr=date.getMonth()+1+"";
		}
		String mois=Checker.getMonthName();
		String annees=String.valueOf(date.getYear()+1900);
		String dateTimeSQL=annees+"-"+moisnbr+"-"+jour+" "+heure+":"+minute+":"+seconde;
		return dateTimeSQL;
	}
	

}
