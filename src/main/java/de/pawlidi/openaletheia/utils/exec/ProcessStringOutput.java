/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
