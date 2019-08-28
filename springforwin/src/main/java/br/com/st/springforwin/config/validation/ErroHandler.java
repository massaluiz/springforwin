package br.com.st.springforwin.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.st.springforwin.config.validation.DTO.FormErrorDTO;

@RestControllerAdvice
public class ErroHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDTO> handler(MethodArgumentNotValidException err) {
		List<FormErrorDTO> formErrorDTO = new ArrayList<>();
		List<FieldError> fieldError = err.getBindingResult().getFieldErrors();
		
		fieldError.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormErrorDTO error = new FormErrorDTO(e.getField(), message);
			formErrorDTO.add(error);
		});
		
		return formErrorDTO;
	}

}
