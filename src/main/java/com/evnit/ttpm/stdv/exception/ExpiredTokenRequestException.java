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
package com.evnit.ttpm.stdv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredTokenRequestException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6204112202687330582L;
	private final String tokenType;
	private final String token;
	private final String message;
	private Integer code = 401;

	public ExpiredTokenRequestException(String tokenType, String token, String message) {
		super(String.format("%s: [%s] token: [%s] ", message, tokenType, token));
		this.tokenType = tokenType;
		this.token = token;
		this.message = message;
	}

	public ExpiredTokenRequestException(String tokenType, String token, String message, Integer code) {
		super(String.format("%s: [%s] token: [%s] ", message, tokenType, token));
		this.tokenType = tokenType;
		this.token = token;
		this.message = message;
		this.code = code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getToken() {
		return token;
	}

	public String getMessage() {
		return message;
	}
}
