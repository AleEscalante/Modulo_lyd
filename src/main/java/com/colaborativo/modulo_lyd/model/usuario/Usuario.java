package com.colaborativo.modulo_lyd.model.usuario;

import com.colaborativo.modulo_lyd.model.conductor.Estado;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id_usuario;

	private String Nombre;
	private String Correo_empresa;
	private String Rol;

	@Enumerated(EnumType.STRING)
	private esActivo esActivo;

	

}
