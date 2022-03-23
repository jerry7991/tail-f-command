package com.cmd;

import java.util.List;

public class Data {
	List<String> data;
	long totalFilesize;

	public Data(List<String> result, long currLen) {
		this.data = result;
		totalFilesize = currLen;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public long getTotalFilesize() {
		return totalFilesize;
	}

	public void setTotalFilesize(long totalFilesize) {
		this.totalFilesize = totalFilesize;
	}

}
