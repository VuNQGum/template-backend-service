/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 
 */
package com.evnit.ttpm.stdv.advice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.evnit.ttpm.stdv.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.evnit.ttpm.stdv.model.payload.ApiResponse;

@RestControllerAdvice
public class AuthControllerAdvice {

	//private static final Logger logger = Logger.getLogger(AuthControllerAdvice.class);

	private final MessageSource messageSource;

	@Autowired
	public AuthControllerAdvice(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse processValidationError(MethodArgumentNotValidException ex, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<ObjectError> allErrors = result.getAllErrors();
		String data = processAllErrors(allErrors).stream().collect(Collectors.joining("\n"));
		return new ApiResponse(false, data, data, ex.getClass().getName(), resolvePathFromWebRequest(request));
	}

	/**
	 * Utility Method to generate localized message for a list of field errors
	 *
	 * @param allErrors
	 *            the field errors
	 * @return the list
	 */
	private List<String> processAllErrors(List<ObjectError> allErrors) {
		return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
	}

	/**
	 * Resolve localized error message. Utility method to generate a localized error
	 * message
	 *
	 * @param objectError
	 *            the field error
	 * @return the string
	 */
	private String resolveLocalizedErrorMessage(ObjectError objectError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
		//logger.info(localizedErrorMessage);
		return localizedErrorMessage;
	}

	private String resolvePathFromWebRequest(WebRequest request) {
		try {
			return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri")
					.toString();
		} catch (Exception ex) {
			return null;
		}
	}

	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse handleAppExceptio404(AppException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = AppException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResponse handleAppException(AppException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = ResourceAlreadyInUseException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse handleBadRequestException(BadRequestException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = BadCredentialsException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public ApiResponse handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = InvalidTokenRequestException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ApiResponse handleInvalidTokenException(InvalidTokenRequestException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ApiResponse handleAuthenticationException (AuthenticationException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ApiResponse handleAccessDeniedException (AccessDeniedException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), "Không có quyền truy cập", ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(value = CustomAppException.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public ApiResponse handleCustomAppException (CustomAppException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getMessage(), ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse handleUnwantedException(Exception ex, WebRequest request) {
		// Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
		ex.printStackTrace();  // Thực tế người ta dùng logger
		return new ApiResponse(false, "Xảy ra lỗi hệ thống. Vui lòng iên hệ với đội hỗ trợ để giải quyết.", "Xảy ra lỗi hệ thống. Vui lòng iên hệ với đội hỗ trợ để giải quyết.", ex.getClass().getName(),
				resolvePathFromWebRequest(request));
	}
}
