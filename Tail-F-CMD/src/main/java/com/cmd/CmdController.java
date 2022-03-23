package com.cmd;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CmdController {
	@Autowired
	CmdService cmdService;
	@Autowired
	CmdServiceImpl cmdServiceImpl;

	@GetMapping("/getUpdatedLogs")
	public List<String> getLastNlineLog() throws IOException {
		return cmdService.getLastNLineLog();
	}

	@GetMapping("/getUpdates/{prevSize}")
	public Data getUpdateOnly(long prevSize) throws IOException {
		return cmdServiceImpl.getLastNLineLog(prevSize);
	}
}