package kr.or.ddit.file.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import kr.or.ddit.file.dao.FileDaoInf;
import kr.or.ddit.file.model.FileVo;


@Service(value="fileService")
public class FileService implements FileServiceInf {
	
	@Resource(name="fileDao")
	private FileDaoInf fileDao;

	/**
	* Method : insertFile
	* 작성자 : PC
	* 변경이력 :
	* @param fileVo
	* @return
	* Method 설명 : 파일 정보 저장
	*/
	@Override
	public int insertFile(FileVo fileVo) {
		return fileDao.insertFile(fileVo);
	}

}
