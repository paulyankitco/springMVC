package kitco.bean.model;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable, Cloneable{

	private static final long serialVersionUID = 4592312573526318427L;

	private long testId;
	
	private String testName;

	private String testDesc;
	
	private List<User> userList;

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
