package de.pawlidi.openaletheia.utils.exec;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProcessExecutorTest {

	@Test
	public void testExecuteCommand() {
		String result = ProcessExecutor.executeCommand("ipconfig");
		assertNotNull(result);
	}

}
