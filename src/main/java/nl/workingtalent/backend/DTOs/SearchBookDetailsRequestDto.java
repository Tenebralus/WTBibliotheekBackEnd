package nl.workingtalent.backend.DTOs;

public class SearchBookDetailsRequestDto {

	private String keyword;
	
	private Long bookId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	
}
