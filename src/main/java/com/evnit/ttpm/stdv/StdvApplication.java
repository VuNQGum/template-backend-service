/*
 * Licensed under the Apache License, Version 2.0 (the "License") Đã xác nhận giấy;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. Bad credentials
 * https://mvnrepository.com/artifact/org.mariuszgromada.math
 */
package com.evnit.ttpm.stdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { StdvApplication.class, Jsr310JpaConverters.class })
public class StdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(StdvApplication.class, args);
	}

}
