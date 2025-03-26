package com.evnit.ttpm.stdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evnit.ttpm.stdv.model.UserAuthority;
import com.evnit.ttpm.stdv.model.UserAuthorityId;

public interface UserAuthorityReopsitory extends JpaRepository<UserAuthority, UserAuthorityId> {

	@Query("Select x from UserAuthority x where x.donviId =:donviId and x.id.roleId =:roleId")
	List<UserAuthority> findByRoleId(@Param("donviId") Integer donviId, @Param("roleId") Long roleId);

	@Modifying
	@Query("delete from UserAuthority where id.roleId=:roleId")
	void deleteByRoleId(@Param("roleId") Long roleId);

	@Modifying
	@Query("delete from UserAuthority where id.nhomQuyen=:nhomQuyen")
	void deleteByNhomQuyen(@Param("nhomQuyen") String nhomQuyen);
}
