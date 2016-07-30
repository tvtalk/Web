package DTO;

public class BookmarkDTO {
	private int bk;
	private int sr;
	private String nickName;
	private String title;
	public BookmarkDTO() {
		super();
	}
	public BookmarkDTO(int bk, int sr, String nickName, String title) {
		super();
		this.bk = bk;
		this.sr = sr;
		this.nickName = nickName;
		this.title = title;
	}
	public int getBk() {
		return bk;
	}
	public void setBk(int bk) {
		this.bk = bk;
	}
	public int getSr() {
		return sr;
	}
	public void setSr(int sr) {
		this.sr = sr;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "BookmarkDTO [bk=" + bk + ", sr=" + sr + ", nickName=" + nickName + ", title=" + title + "]";
	}
}
