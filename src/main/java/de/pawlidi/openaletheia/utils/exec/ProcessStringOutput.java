package de.pawlidi.openaletheia.utils.exec;

import org.apache.commons.exec.LogOutputStream;

/**
 * 
 * 
 * @author PAWLIDIM
 *
 *         Create: 22:16:50 2015
 *
 */
public class ProcessStringOutput extends LogOutputStream {

	private StringBuilder processOutput;

	public ProcessStringOutput(final int logLevel) {
		super(logLevel);
		this.processOutput = new StringBuilder();
	}

	@Override
	protected void processLine(String line, int logLevel) {
		processOutput.append(line);
		processOutput.append("\n");
	}

	public String getOutput() {
		return processOutput.toString();
	}
}
