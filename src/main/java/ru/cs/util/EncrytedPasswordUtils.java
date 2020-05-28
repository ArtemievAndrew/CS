package ru.cs.util;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

	// Encrypte Password with BCryptPasswordEncoder
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.print("Input password for encode: ");
		String password = in.nextLine();
		in.close();

		String encrytedPassword = encrytePassword(password);

		System.out.println("Password: " + password);
		System.out.println("Encryted Password: " + encrytedPassword);
	}

}
