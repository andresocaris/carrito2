package com.ao.juego.controller;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ao.juego.exceptions.CategoriaException;
import com.ao.juego.exceptions.ErrorMessage;
import com.ao.juego.exceptions.ProductoException;
import com.ao.juego.exceptions.UsuarioException;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(value = { CategoriaException.class })
	public ResponseEntity<Object> handleException(CategoriaException categoriaException) {
		ErrorMessage message = new ErrorMessage(categoriaException.getMessage(), HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneOffset.UTC));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ProductoException.class })
	public ResponseEntity<Object> handleException(ProductoException productoException) {
		ErrorMessage message = new ErrorMessage(productoException.getMessage(), HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneOffset.UTC));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { UsuarioException.class })
	public ResponseEntity<Object> handleException(UsuarioException usuarioException) {
		ErrorMessage message = new ErrorMessage(usuarioException.getMessage(), HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneOffset.UTC));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
