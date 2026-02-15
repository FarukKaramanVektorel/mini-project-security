package com.personel.personel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personel.personel.dto.AuthenticationRequest;
import com.personel.personel.dto.AuthenticationResponse;
import com.personel.personel.dto.RegisterRequest;
import com.personel.personel.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationControler {

	private final AuthenticationService service;
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.register(request));
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authentication(request));
	}
	
}
