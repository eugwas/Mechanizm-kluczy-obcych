package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	static String uzytkownik = "ewasile1"; // tu wpisz nazwę użytkownika
	static String haslo = "ewasile1"; // tu wpisz hasło
	static String url = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf"; // tu wpisz adres serwera

	public static void main(String[] args) {
		
		try {
			
			Connection polaczenie = DriverManager.getConnection(url, uzytkownik, haslo);

			verifyOracleDriver();

			connectToDatabase(polaczenie);

			createTables(polaczenie);

			insertInto4Tables(polaczenie);

			insertIntoOcenianie(polaczenie);

			polaczenie.close();

		} catch (

		Exception e) {
			System.out.println("Błąd programu!");
			e.printStackTrace();
			return;
		}
		System.out.println("Koniec.");
	}
	
	private static void verifyOracleDriver() {
		String nazwaSterownika = "oracle.jdbc.driver.OracleDriver";
		try {
			Class<?> c = Class.forName(nazwaSterownika);
			System.out.println("Pakiet: " + c.getPackage());
			System.out.println("Nazwa klasy: " + c.getName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void connectToDatabase(Connection polaczenie) {
		try {
			System.out.println("AutoCommit: " + polaczenie.getAutoCommit());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createTables(Connection polaczenie) throws SQLException {
		String sqlNauczycielCreate = "CREATE TABLE nauczyciel (idn integer not null, nazwisko_nauczyciela char(30) not null, imie_nauczyciela char(20) not null)";
		String sqlUczenCreate = "CREATE TABLE uczen (idu integer not null, nazwisko_ucznia char(30) not null, imie_ucznia char(20) not null)";
		String sqlPrzedmiotCreate = "CREATE TABLE przedmiot (idp integer not null, nazwa_przedmiotu char(20) not null)";
		String sqlOcenaCreate = "CREATE TABLE ocena (ido integer not null, wartosc_opisowa char(20) not null, wartosc_numeryczna float not null)";
		String sqlOcenianieCreate = "CREATE TABLE ocenianie (idn integer not null, idu integer not null, idp integer not null, ido integer not null, rodzaj_oceny char(1) not null)";

		Statement polecenieCreate = polaczenie.createStatement();

		try {
			polecenieCreate.executeUpdate(sqlNauczycielCreate);
			polecenieCreate.executeUpdate(sqlUczenCreate);
			polecenieCreate.executeUpdate(sqlPrzedmiotCreate);
			polecenieCreate.executeUpdate(sqlOcenaCreate);
			polecenieCreate.executeUpdate(sqlOcenianieCreate);
			polecenieCreate.close();
		} catch (SQLException e) {
			e.toString();
		}
	}
	
	private static void insertInto4Tables(Connection polaczenie) throws SQLException {
		String sqlNauczyciel1 = "INSERT INTO nauczyciel (idn, nazwisko_nauczyciela, imie_nauczyciela) SELECT 1, 'Marianowicz', 'Marian' from dual "
				+ "WHERE NOT EXISTS (SELECT * from nauczyciel WHERE idn = 1 AND nazwisko_nauczyciela = 'Marianowicz' AND imie_nauczyciela = 'Marian')";
		String sqlNauczyciel2 = "INSERT INTO nauczyciel (idn, nazwisko_nauczyciela, imie_nauczyciela) SELECT 2, 'Kamilowicz', 'Kamil' from dual "
				+ "WHERE NOT EXISTS (SELECT * from nauczyciel WHERE idn = 2 AND nazwisko_nauczyciela = 'Kamilowicz' AND imie_nauczyciela = 'Kamil')";
		String sqlNauczyciel3 = "INSERT INTO nauczyciel (idn, nazwisko_nauczyciela, imie_nauczyciela) SELECT 3, 'Dawidowicz', 'Dawid' from dual "
				+ "WHERE NOT EXISTS (SELECT * from nauczyciel WHERE idn = 3 AND nazwisko_nauczyciela = 'Dawidowicz' AND imie_nauczyciela = 'Dawid')";
		String sqlNauczyciel4 = "INSERT INTO nauczyciel (idn, nazwisko_nauczyciela, imie_nauczyciela) SELECT 4, 'Andrzejowicz', 'Andrzej' from dual "
				+ "WHERE NOT EXISTS (SELECT * from nauczyciel WHERE idn = 4 AND nazwisko_nauczyciela = 'Andrzejowicz' AND imie_nauczyciela = 'Andrzej')";
		String sqlNauczyciel5 = "INSERT INTO nauczyciel (idn, nazwisko_nauczyciela, imie_nauczyciela) SELECT 5, 'Tomaszewicz', 'Tomasz' from dual "
				+ "WHERE NOT EXISTS (SELECT * from nauczyciel WHERE idn = 5 AND nazwisko_nauczyciela = 'Tomaszewicz' AND imie_nauczyciela = 'Tomasz')";
		String sqlNauczyciel6 = "INSERT INTO nauczyciel (idn, nazwisko_nauczyciela, imie_nauczyciela) SELECT 6, 'Krzysztofowicz', 'Krzysztof' from dual "
				+ "WHERE NOT EXISTS (SELECT * from nauczyciel WHERE idn = 6 AND nazwisko_nauczyciela = 'Krzysztofowicz' AND imie_nauczyciela = 'Krzysztof')";

		String sqlUczen1 = "INSERT INTO uczen (idu, nazwisko_ucznia, imie_ucznia) SELECT 1,'Fedorek', 'Fedor' from dual "
				+ "WHERE NOT EXISTS (SELECT * from uczen WHERE idu = 1 AND nazwisko_ucznia = 'Fedorek' AND imie_ucznia = 'Fedor')";
		String sqlUczen2 = "INSERT INTO uczen (idu, nazwisko_ucznia, imie_ucznia) SELECT 2,'Igorek', 'Igor' from dual "
				+ "WHERE NOT EXISTS (SELECT * from uczen WHERE idu = 2 AND nazwisko_ucznia = 'Igorek' AND imie_ucznia = 'Igor')";
		String sqlUczen3 = "INSERT INTO uczen (idu, nazwisko_ucznia, imie_ucznia) SELECT 3,'Maciejek', 'Maciej' from dual "
				+ "WHERE NOT EXISTS (SELECT * from uczen WHERE idu = 3 AND nazwisko_ucznia = 'Maciejek' AND imie_ucznia = 'Maciej')";
		String sqlUczen4 = "INSERT INTO uczen (idu, nazwisko_ucznia, imie_ucznia) SELECT 4, 'Adrianek', 'Adrian' from dual "
				+ "WHERE NOT EXISTS (SELECT * from uczen WHERE idu = 4 AND nazwisko_ucznia = 'Adrianek' AND imie_ucznia = 'Adrian')";
		String sqlUczen5 = "INSERT INTO uczen (idu, nazwisko_ucznia, imie_ucznia) SELECT 5, 'Michalek', 'Michal' from dual "
				+ "WHERE NOT EXISTS (SELECT * from uczen WHERE idu = 5 AND nazwisko_ucznia = 'Michalek' AND imie_ucznia = 'Michal')";

		String sqlPrzedmiot1 = "INSERT INTO przedmiot (idp, nazwa_przedmiotu) SELECT 1, 'Java' from dual "
				+ "WHERE NOT EXISTS (SELECT * from przedmiot WHERE idp = 1 AND nazwa_przedmiotu = 'Java')";
		String sqlPrzedmiot2 = "INSERT INTO przedmiot (idp, nazwa_przedmiotu) SELECT 2, 'Python' from dual "
				+ "WHERE NOT EXISTS (SELECT * from przedmiot WHERE idp = 2 AND nazwa_przedmiotu = 'Python')";
		String sqlPrzedmiot3 = "INSERT INTO przedmiot (idp, nazwa_przedmiotu) SELECT 3, 'Javascript' from dual "
				+ "WHERE NOT EXISTS (SELECT * from przedmiot WHERE idp = 3 AND nazwa_przedmiotu = 'Javascript')";
		String sqlPrzedmiot4 = "INSERT INTO przedmiot (idp, nazwa_przedmiotu) SELECT 4, 'C++' from dual "
				+ "WHERE NOT EXISTS (SELECT * from przedmiot WHERE idp = 4 AND nazwa_przedmiotu = 'C++')";
		String sqlPrzedmiot5 = "INSERT INTO przedmiot (idp, nazwa_przedmiotu) SELECT 5, 'HTML/CSS' from dual "
				+ "WHERE NOT EXISTS (SELECT * from przedmiot WHERE idp = 5 AND nazwa_przedmiotu = 'HTML/CSS')";

		String sqlOcena1 = "INSERT INTO ocena (ido, wartosc_opisowa, wartosc_numeryczna) SELECT 1, 'ndst', 2 from dual "
				+ "WHERE NOT EXISTS (SELECT * from ocena WHERE ido = 1 AND wartosc_opisowa = 'ndst' AND wartosc_numeryczna = 2)";
		String sqlOcena2 = "INSERT INTO ocena (ido, wartosc_opisowa, wartosc_numeryczna) SELECT 2, 'dst', 3 from dual "
				+ "WHERE NOT EXISTS (SELECT * from ocena WHERE ido = 2 AND wartosc_opisowa = 'dst' AND wartosc_numeryczna = 3)";
		String sqlOcena3 = "INSERT INTO ocena (ido, wartosc_opisowa, wartosc_numeryczna) SELECT 3, 'dst+', 3.5 from dual "
				+ "WHERE NOT EXISTS (SELECT * from ocena WHERE ido = 3 AND wartosc_opisowa = 'dst+' AND wartosc_numeryczna = 3.5)";
		String sqlOcena4 = "INSERT INTO ocena (ido, wartosc_opisowa, wartosc_numeryczna) SELECT 4, 'db', 4 from dual "
				+ "WHERE NOT EXISTS (SELECT * from ocena WHERE ido = 4 AND wartosc_opisowa = 'db' AND wartosc_numeryczna = 4)";
		String sqlOcena5 = "INSERT INTO ocena (ido, wartosc_opisowa, wartosc_numeryczna) SELECT 5, 'db+', 4.5 from dual "
				+ "WHERE NOT EXISTS (SELECT * from ocena WHERE ido = 5 AND wartosc_opisowa = 'db+' AND wartosc_numeryczna = 4.5)";
		String sqlOcena6 = "INSERT INTO ocena (ido, wartosc_opisowa, wartosc_numeryczna) SELECT 6, 'bdb', 5 from dual "
				+ "WHERE NOT EXISTS (SELECT * from ocena WHERE ido = 6 AND wartosc_opisowa = 'bdb' AND wartosc_numeryczna = 5)";

		Statement polecenieInsert = polaczenie.createStatement();

		try {
			polecenieInsert.executeUpdate(sqlNauczyciel1);
			polecenieInsert.executeUpdate(sqlNauczyciel2);
			polecenieInsert.executeUpdate(sqlNauczyciel3);
			polecenieInsert.executeUpdate(sqlNauczyciel4);
			polecenieInsert.executeUpdate(sqlNauczyciel5);
			polecenieInsert.executeUpdate(sqlNauczyciel6);
			polecenieInsert.executeUpdate(sqlUczen1);
			polecenieInsert.executeUpdate(sqlUczen2);
			polecenieInsert.executeUpdate(sqlUczen3);
			polecenieInsert.executeUpdate(sqlUczen4);
			polecenieInsert.executeUpdate(sqlUczen5);
			polecenieInsert.executeUpdate(sqlPrzedmiot1);
			polecenieInsert.executeUpdate(sqlPrzedmiot2);
			polecenieInsert.executeUpdate(sqlPrzedmiot3);
			polecenieInsert.executeUpdate(sqlPrzedmiot4);
			polecenieInsert.executeUpdate(sqlPrzedmiot5);
			polecenieInsert.executeUpdate(sqlOcena1);
			polecenieInsert.executeUpdate(sqlOcena2);
			polecenieInsert.executeUpdate(sqlOcena3);
			polecenieInsert.executeUpdate(sqlOcena4);
			polecenieInsert.executeUpdate(sqlOcena5);
			polecenieInsert.executeUpdate(sqlOcena6);
			polecenieInsert.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return;
		}
	}

	private static void insertIntoOcenianie(Connection polaczenie) throws SQLException, IOException {
		
		String sql = "INSERT INTO ocenianie (idn, idu, idp, ido, rodzaj_oceny) VALUES (?, ?, ?, ?, ?)";

		String sqlNauczyciel = "SELECT * FROM nauczyciel WHERE idn = ?";
		String sqlUczen = "SELECT * FROM uczen WHERE idu = ?";
		String sqlPrzedmiot = "SELECT * FROM przedmiot WHERE idp = ?";
		String sqlOcena = "SELECT * FROM ocena WHERE ido = ?";

		PreparedStatement polecenie1 = polaczenie.prepareStatement(sqlNauczyciel);
		PreparedStatement polecenie2 = polaczenie.prepareStatement(sqlUczen);
		PreparedStatement polecenie3 = polaczenie.prepareStatement(sqlPrzedmiot);
		PreparedStatement polecenie4 = polaczenie.prepareStatement(sqlOcena);
		PreparedStatement polecenie = polaczenie.prepareStatement(sql);

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.println("Wprowadz dane do wiersza tabeli OCENIANIE");

			System.out.print("idn: ");
			int konsola1 = 0;
			boolean czyPoprawne1 = false;
			while (!czyPoprawne1) {
				try {
					konsola1 = Integer.parseInt(input.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Wprowadzono dane niewłaściwego typu. Podaj dane typu int: ");
				}
				czyPoprawne1 = konsola1 == 0 ? false : true;
			}

			System.out.print("idu: ");
			int konsola2 = 0;
			boolean czyPoprawne2 = false;
			while (!czyPoprawne2) {
				try {
					konsola2 = Integer.parseInt(input.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Wprowadzono dane niewłaściwego typu. Podaj dane typu int: ");
				}
				czyPoprawne2 = konsola2 == 0 ? false : true;
			}

			System.out.print("idp: ");
			int konsola3 = 0;
			boolean czyPoprawne3 = false;
			while (!czyPoprawne3) {
				try {
					konsola3 = Integer.parseInt(input.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Wprowadzono dane niewłaściwego typu. Podaj dane typu int: ");
				}
				czyPoprawne3 = konsola3 == 0 ? false : true;
			}

			System.out.print("ido: ");
			int konsola4 = 0;
			boolean czyPoprawne4 = false;
			while (!czyPoprawne4) {
				try {
					konsola4 = Integer.parseInt(input.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Wprowadzono dane niewłaściwego typu. Podaj dane typu int: ");
				}
				czyPoprawne4 = konsola4 == 0 ? false : true;
			}

			System.out.print("rodzaj_oceny: ");
			String konsola5 = "";
			boolean czyPoprawne5 = false;
			while (!czyPoprawne5) {
				konsola5 = (input.readLine()).toUpperCase();
				if ((konsola5.equals("S")) || (konsola5.equals("s")) || (konsola5.equals("C"))
						|| (konsola5.equals("c"))) {
				} else {
					System.out.println("Wprowadzono błędne dane. Wpisz C lub S: ");
					continue;
				}
				czyPoprawne5 = konsola5 == "" ? false : true;
			}

			polecenie1.setInt(1, konsola1);
			polecenie2.setInt(1, konsola2);
			polecenie3.setInt(1, konsola3);
			polecenie4.setInt(1, konsola4);

			ResultSet rs4 = polecenie4.executeQuery();
			if (rs4.next()) {
				ResultSet rs3 = polecenie3.executeQuery();
				if (rs3.next()) {
					ResultSet rs2 = polecenie2.executeQuery();
					if (rs2.next()) {
						ResultSet rs1 = polecenie1.executeQuery();
						if (rs1.next()) {
						} else {
							System.out.println(
									"Naruszono klucz 1. Wpisz T lub t, aby kontynuować. Wciśnij inny przycisk, aby zatrzymać program.");
							continue;
						}
						rs1.close();
					} else {
						System.out.println(
								"Naruszono klucz 2. Wpisz T lub t, aby kontynuować. Wciśnij inny przycisk, aby zatrzymać program.");
						continue;
					}
					rs2.close();
				} else {
					System.out.println(
							"Naruszono klucz 3. Wpisz T lub t, aby kontynuować. Wciśnij inny przycisk, aby zatrzymać program.");
					continue;
				}
				rs3.close();
			} else {
				System.out.println(
						"Naruszono klucz 4. Wpisz T lub t, aby kontynuować. Wciśnij inny przycisk, aby zatrzymać program.");
				continue;
			}
			rs4.close();

			polecenie.setInt(1, konsola1);
			polecenie.setInt(2, konsola2);
			polecenie.setInt(3, konsola3);
			polecenie.setInt(4, konsola4);
			polecenie.setString(5, konsola5);
			System.out.println("execute: " + polecenie.executeUpdate());

			System.out.println("Wpisz T lub t, aby kontynuować. Wciśnij inny przycisk, aby zatrzymać program.");
			
		} while (input.readLine().equalsIgnoreCase("T"));
	}
}

