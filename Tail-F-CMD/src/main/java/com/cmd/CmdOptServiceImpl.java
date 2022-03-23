package com.cmd;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CmdOptServiceImpl implements CmdService {

	@Override
	public List<String> getLastNLineLog() throws IOException {
		List<String> result = new ArrayList<>();
		RandomAccessFile randomAccessFile = new RandomAccessFile(Constants.FILE_DIR, "r");

		long endPoint = randomAccessFile.length();
		int lineAdded = 0;

		boolean readMore = true;

		while (readMore) {
			byte[] buffer = new byte[Constants.CHUNK_SIZE];

			long startPoint = endPoint - Constants.CHUNK_SIZE;

			long readLength = Constants.CHUNK_SIZE;

			if (startPoint < 0) {
				readLength = Constants.CHUNK_SIZE + startPoint;
				startPoint = 0;
			}

			randomAccessFile.seek(startPoint);

			readLength = randomAccessFile.read(buffer, 0, (int) readLength);

			if (readLength <= 0) {
				break;
			}

			int unParsedLen = (int) readLength;
			int index = unParsedLen - 1;
			while (index >= 0 && lineAdded < 10) {
				if (buffer[index] == '\n') {
					int startOfLine = index + 1;
					int len = (unParsedLen - startOfLine);
					if (len > 0) {
						result.add(new String(buffer, startOfLine, len));
						lineAdded++;
					}
					unParsedLen = index + 1;
				}
				index--;
			}

			endPoint = endPoint - (Constants.CHUNK_SIZE - unParsedLen);

			readMore = result.size() < 10 && startPoint != 0;

		}
		return result;
	}

}
