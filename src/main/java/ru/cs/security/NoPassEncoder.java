package ru.cs.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * скопировал NoOpPasswordEncoder
 * */
public final class NoPassEncoder implements PasswordEncoder {

	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

	/**
	 * Get the singleton {@link NoOpPasswordEncoder}.
	 */
	public static PasswordEncoder getInstance() {
		return INSTANCE;
	}

	private static final PasswordEncoder INSTANCE = new NoPassEncoder();

	private NoPassEncoder() {
	}

}