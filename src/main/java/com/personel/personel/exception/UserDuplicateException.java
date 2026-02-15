package com.personel.personel.exception;

public class UserDuplicateException extends RuntimeException{
	public UserDuplicateException(String message) {
		super("Kullanıcı daha önceden kayıtlı");
	}

}
