package com.evnit.ttpm.stdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evnit.ttpm.stdv.enums.NGHIEPVUCODE;
import com.evnit.ttpm.stdv.model.CustomUserDetails;
import com.evnit.ttpm.stdv.model.FileAttach;
import com.evnit.ttpm.stdv.repository.FileAttachRepository;

@Service
public class FileAttachService {

	private final FileAttachRepository fileAttachRepository;

	@Autowired
	public FileAttachService(FileAttachRepository fileAttachRepository) {
		super();
		this.fileAttachRepository = fileAttachRepository;
	}

	public FileAttach getFileAttachById(Long id) {
		return fileAttachRepository.getFileAttachById(id);
	}

	public List<FileAttach> findAll(Long objectId, NGHIEPVUCODE nghiepvuCode, Integer donviId) {
		return fileAttachRepository.findAllByObjectId(donviId, objectId, nghiepvuCode);
	}

	public List<FileAttach> findAllType(Long objectId, String typeFile, NGHIEPVUCODE nghiepvuCode,
			CustomUserDetails currentUser) {
		return fileAttachRepository.findByTypeObjectId(currentUser.getDonviId(), objectId, typeFile, nghiepvuCode);
	}

	public List<FileAttach> findAllByObjectId(Long objectId, NGHIEPVUCODE nghiepvuCode, String type, Integer donviId) {
		return fileAttachRepository.findAllByObjectId(donviId, objectId, nghiepvuCode, type);
	}

	public List<FileAttach> findAllByObjectId(Long objectId, String type, Integer donviId) {
		return fileAttachRepository.findAllByObjectId(donviId, objectId, type);
	}

	public FileAttach findByObjectAndFileMau(Long objectId, String fileType, CustomUserDetails currentUser) {
		List<FileAttach> listFile = fileAttachRepository.findByObjectAndFileMau(currentUser.getDonviId(), objectId,
				fileType);
		return listFile.size() > 0 ? listFile.get(0) : null;
	}

	public void delete(FileAttach fileAttach) {
		fileAttach.setDeleted(true);
		fileAttachRepository.saveAndFlush(fileAttach);
	}

	@Transactional
	public void deleteByPhieuId(Integer donviId, Long objectId) {
		fileAttachRepository.deleteByPhieuId(donviId, objectId);
	}

	public FileAttach findByFileId(String filePdfId) {
		return fileAttachRepository.findByFileId(filePdfId);
	}

	public FileAttach findByNSFileMau(Long objectId, NGHIEPVUCODE nghiepvuCode, String fileType) {
		List<FileAttach> listFile = fileAttachRepository.findByNSFileMau(objectId, nghiepvuCode, fileType);
		return listFile.size() > 0 ? listFile.get(0) : null;
	}
}
