package com.cmd;

import java.io.IOException;
import java.util.List;

public interface CmdService {
	List<String> getLastNLineLog() throws IOException;
}
