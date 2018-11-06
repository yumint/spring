package kr.or.ddit.board.model;

public class BoardVo {
	
	private int boardId;
	private String boardNm;
	private String regId;
	

	public BoardVo() {
	}


	// 인자가 있는 생성자를 만들면 기본생성자가 생성안되기 때문에 기본생성자도 만들어 주기
	public BoardVo(int boardId, String boardNm, String regId) {
		this.boardId = boardId;
		this.boardNm = boardNm;
		this.regId = regId;
	}


	@Override
	public String toString() {
		return "BoardVo [boardId=" + boardId + ", boardNm=" + boardNm + ", regId=" + regId + "]";
	}
	
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
	

}
