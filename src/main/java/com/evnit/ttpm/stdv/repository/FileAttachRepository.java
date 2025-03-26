package com.evnit.ttpm.stdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evnit.ttpm.stdv.enums.NGHIEPVUCODE;
import com.evnit.ttpm.stdv.model.FileAttach;

public interface FileAttachRepository extends JpaRepository<FileAttach, Long> {

	@Query("Select x from FileAttach x where x.donviId =:donviId and x.objectId =:objectId and x.nghiepvuCode =:nghiepvuCode and x.deleted = false order by x.fileType, x.createTime desc")
	List<FileAttach> findAllByObjectId(@Param("donviId") Integer donviId, @Param("objectId") Long objectId,
			@Param("nghiepvuCode") NGHIEPVUCODE nghiepvuCode);

	@Query("Select x from FileAttach x where x.donviId =:donviId and x.objectId =:objectId and x.fileType =:fileType and x.deleted = false")
	List<FileAttach> findAllByObjectId(@Param("donviId") Integer donviId, @Param("objectId") Long objectId,
									   @Param("fileType") String fileType);

	@Query("Select x from FileAttach x where x.donviId =:donviId and x.objectId =:objectId and x.nghiepvuCode =:nghiepvuCode and x.fileType =:fileType and x.deleted = false")
	List<FileAttach> findAllByObjectId(@Param("donviId") Integer donviId, @Param("objectId") Long objectId,
			@Param("nghiepvuCode") NGHIEPVUCODE nghiepvuCode, @Param("fileType") String fileType);

	@Query("Select x from FileAttach x where x.donviId =:donviId and x.objectId =:objectId and x.fileType =:fileType and x.deleted = false")
	List<FileAttach> findByObjectAndFileMau(@Param("donviId") Integer donviId, @Param("objectId") Long objectId,
			@Param("fileType") String fileType);

	@Query("Select x from FileAttach x where x.donviId =:donviId and x.objectId =:objectId and x.fileType =:fileType and x.nghiepvuCode =:nghiepvuCode and x.deleted = false")
	List<FileAttach> findByTypeObjectId(@Param("donviId") Integer donviId, @Param("objectId") Long objectId,
			@Param("fileType") String fileType, @Param("nghiepvuCode") NGHIEPVUCODE nghiepvuCode);

	@Query("SELECT x FROM FileAttach x WHERE x.id=:id AND x.deleted = false")
	FileAttach getFileAttachById(@Param("id") Long id);

	@Modifying
	@Query("delete FileAttach u where u.donviId =:donviId and u.objectId =:objectId")
	void deleteByPhieuId(@Param("donviId") Integer donviId, @Param("objectId") Long objectId);

	@Query(value = "SELECT fa.* " +
			"FROM NS_QDNDUNG nq " +
			"INNER JOIN FILE_ATTACH fa ON nq.QDINH_ID = fa.Object_id " +
			"WHERE nq.SO_QD = :soQd " +
			"AND nq.NAMQD = YEAR(GETDATE()) " +
			"AND nq.DONVI_ID = :donviId " +
			"AND fa.deleted = 0", nativeQuery = true)
	FileAttach findBySoQdAndNamQdAndDonviId(String soQd, Integer donviId);

	@Query("Select x from FileAttach x where x.fileId = :filePdfId And x.deleted = false")
    FileAttach findByFileId(String filePdfId);

	@Query("Select x from FileAttach x where x.objectId =:objectId and x.nghiepvuCode = :nghiepVuCode and x.fileType =:fileType and x.deleted = false")
	List<FileAttach> findByNSFileMau( @Param("objectId") Long objectId, @Param("nghiepVuCode") NGHIEPVUCODE nghiepVuCode,
											@Param("fileType") String fileType);

	@Query("Select x from FileAttach x where x.objectId =:objectId and x.nghiepvuCode =:nghiepvuCode and x.deleted = false order by x.fileType, x.createTime desc")
	List<FileAttach> findAllByObjectIdNoDonViId(@Param("objectId") Long objectId,
									   @Param("nghiepvuCode") NGHIEPVUCODE nghiepvuCode);


	/**
	 * Truy vấn lấy ra danh sách file đính kèm với thông tin đăng ký nâng lương của
	 * NLĐ
	 *
	 * @param dangKyNLId
	 * @return
	 */
	@Query(value =
			"SELECT " +
			"	fa.* " +
			"FROM " +
			"	FILE_ATTACH fa " +
			"WHERE fa.Object_id = :dangKyNLId " +
			"AND fa.Nghiepvu_code = 'HSNS_DKNL_FILE_DINHKEM' " +
			"AND fa.deleted = 0", nativeQuery = true)
	List<FileAttach> getFilesDknl(Long dangKyNLId);


	/**
	 * Truy vấn lấy ra danh sách file dự thảo cá nhân
	 *
	 * @param dsnangluongId
	 * @return
	 */
	@Query(value =
			"SELECT" +
			"	fa.* " +
			"FROM" +
			"	FILE_ATTACH fa" +
			"	INNER JOIN NS_DANHSACH_LUONG ndl ON (ndl.DSNHANSU_LUONG_ID = fa.Object_id AND ndl.DSNHANSU_LUONG_ID = :dsnangluongId AND ndl.deleted = 0 OR ndl.deleted IS NULL)" +
			"WHERE fa.Nghiepvu_code = 'HSNS_QT_LUONG' " +
			"AND fa.File_type = 'DINH_KEM' " +
			"AND fa.deleted = 0", nativeQuery = true)
	List<FileAttach> getFileDtcanhan(Long dsnangluongId);


	/**
	 * Truy vấn lấy ra danh sách file dự thảo tập thể
	 *
	 * @param dsTimkiemId
	 * @return
	 */
	@Query(value = "SELECT" +
			"	fa.* " +
			"FROM" +
			"	FILE_ATTACH fa" +
			"	INNER JOIN NS_TIMKIEM_DSACHTK ntd ON (ntd.DS_TIMKIEM_ID = fa.Object_id AND ntd.DS_TIMKIEM_ID = :dsTimkiemId AND ntd.deleted = 0 OR ntd.deleted IS NULL)" +
			"WHERE fa.Nghiepvu_code = 'HSNS_QT_LUONG_TAPTHE' " +
			"AND fa.File_type = 'DINH_KEM' " +
			"AND fa.deleted = 0", nativeQuery = true)
	List<FileAttach> getFileDttapthe(Long dsTimkiemId);
}
