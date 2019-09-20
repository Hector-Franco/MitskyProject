package tk.mitsky.web.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityUtils {

	private static final String CHAR_LOWER = "efgwlmniqrstuaxyjkbcñzhdopv";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "9137824605";

	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();

	// ALGORITMO HASH SHA-512
	public static String getSecurePassword(String passwordToHash, String salt) {

		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < bytes.length; i++)
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

			generatedPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return generatedPassword;
	}

	// GENERAR RANDOM STRING
	public static String generateRandomString() {

		StringBuilder sb = new StringBuilder(4);

		for (int i = 0; i < 4; i++) {

			// 0-64 (exclusive), random returns 0-63
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);
		}

		return sb.toString();
	}

}
