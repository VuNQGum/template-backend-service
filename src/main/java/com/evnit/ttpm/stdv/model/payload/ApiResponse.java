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
package com.evnit.ttpm.stdv.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

	private final Object data;
	private final Boolean state;
	private final String timestamp;
	private final String cause;
	private final String path;
	private final String message;

//	public ApiResponse(Boolean state, Object data) {
//		this.timestamp = Instant.now().toString();
//		this.data = data;
//		this.state = state;
//		this.cause = null;
//		this.path = null;
//		this.message = "";
//	}

	public ApiResponse(Boolean state, Object data, String message) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = null;
		this.path = null;
		this.message = message;
	}

	public ApiResponse(Boolean state, Object data, String cause, String path) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = cause;
		this.path = path;
		this.message = "";
	}

	public ApiResponse(Boolean state, Object data, String message, String cause, String path) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = cause;
		this.path = path;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public Boolean getState() {
		return state;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getCause() {
		return cause;
	}

	public String getPath() {
		return path;
	}

	public String getMessage() {
		return message;
	}
}
