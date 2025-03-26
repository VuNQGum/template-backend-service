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
package com.evnit.ttpm.stdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evnit.ttpm.stdv.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	List<User> findByDonviId(Integer donviId);

	List<User> findByDonviIdAndNsId(Integer donviId, Long nsId);

	List<User> findByActiveAndNsIdIn(Boolean active, List<Long> nsIdList);
}
