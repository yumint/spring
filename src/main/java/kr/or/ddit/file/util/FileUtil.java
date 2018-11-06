package kr.or.ddit.file.util;


public class FileUtil {
	
	/**
	* Method : getFileExt
	* 작성자 : PC
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일 확장자 추출
	*/
	public static String getFileExt(String fileName) {

		int idx = fileName.lastIndexOf(".");	// .뒤에 있는 인덱스 값을 지정 합니다. 
		
		if(idx < 0) {	// dot이 없는경우
			return "";
		}else {
			return fileName.substring(idx);
		}
		
	}
	
}
