package com.personel.personel.dto;





import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@NotNull(message = "İsim bilgisi zorunlu...")
	private String name;
	@NotNull(message = "Nickname bilgisi zorunlu...")
	private String username;
	@NotNull(message = "Parola bilgisi zorunlu...")
	private String password;
	@NotNull(message = "E-mail bilgisi zorunlu...")
	private String email;

}
