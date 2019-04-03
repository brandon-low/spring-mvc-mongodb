package hello.entity;

public class Proto {
	private String id;
	private String title;
	private String text;
	@Override
	public String toString() {
		return "proto [id=" + id + ", title=" + title + ", text=" + text + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


}
