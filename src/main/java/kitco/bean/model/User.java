package kitco.bean.model;

import java.io.Serializable;

public class User implements Serializable, Cloneable{

	private static final long serialVersionUID = 4592312573526318427L;

	private long id;
	
	private String userName;

	private String password;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
