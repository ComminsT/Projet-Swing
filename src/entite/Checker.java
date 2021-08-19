package entite;

import java.util.ArrayList;

import dao.ProprietaireDAO;
import entite.Database;

public class Checker {
	
	public static boolean mailcheckerp(String mail) {
		Database.Connect();
		ProprietaireDAO proprietaireDAO=new ProprietaireDAO();
		ArrayList<String>mails=proprietaireDAO.getAllMail();
		if(mail.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")&&!mails.contains(mail)) {
			System.out.println("Mail ok");
			return true;
		}else {
			System.out.println("Mail FAIL");
			return false;
		}
	}
public static boolean datechecker(String date) {
	if(date.matches("^(?:(?:19[0-9]{2}|200[0-9]|2010)([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:19(?:0[48]|[2648][048]|[13579][26])|2000|200[48])([-/.]?)0?2\\2(?:29))$")) {
		System.out.println("Date ok");
		return true;
	}else {
		System.out.println("Date fail");
		return false;
	}
}

public static String monthtonumber(String month) {
	if(month.equals("Janvier")) {
		return "1";
	}else if(month.equals("Février")) {
		return "2";
	}else if(month.equals("Mars")) {
		return "3";
	}else if(month.equals("Avril")) {
		return "4";
	}else if(month.equals("Mai")) {
		return "5";
	}else if(month.equals("Juin")) {
		return "6";
	}else if(month.equals("Juillet")) {
		return "7";
	}else if(month.equals("Août")) {
		return "8";
	}else if(month.equals("Septembre")) {
		return "9";
	}else if(month.equals("Octobre")) {
		return "10";
	}else if(month.equals("Novembre")) {
		return "11";
	}else if(month.equals("Décembre")) {
		return "12";
	}else return null;
}

public static String phonenumber(String tel) {
	if(tel.substring(3,4).equals("+")) {
		System.out.println("ok");
		return tel.substring(3,tel.length());
	}else return tel.substring(4,tel.length());
		
}

}
