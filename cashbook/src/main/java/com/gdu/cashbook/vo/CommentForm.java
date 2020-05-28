package com.gdu.cashbook.vo;

public class CommentForm {
	private int commentNo;
	private String boardNo;
	private String memberId;
	private String memberName;
	private String commentContent;
	private String lastUpdate;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "CommentForm [commentNo=" + commentNo + ", boardNo=" + boardNo + ", memberId=" + memberId
				+ ", memberName=" + memberName + ", commentContent=" + commentContent + ", lastUpdate=" + lastUpdate
				+ "]";
	}
}
