package Model;

public class DanhMuc {
	private String id;
	private String name;
	
	public DanhMuc(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public DanhMuc() {
	}

	@Override
	public String toString() {
		return "DanhMuc [id=" + id + ", name=" + name + "]";
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
