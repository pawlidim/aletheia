package de.pawlidi.openaletheia.utils.exec;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * This class provides methods to execute shell commands from jvm.
 * 
 * @author PAWLIDIM
 *
 */
public final class ProcessExecutor {

	/** Process watchdog timeout definition */
	public static final long WATCHDOG_TIMEOUT = 60000;

	/** Process output level definition */
	public static final int PROCESS_OUTPUT_LEVEL = 1;

	/** Invisible default constructor. */
	private ProcessExecutor() {
		super();
	}

	/**
	 * Execute given system command without arguments.
	 * 
	 * @param command
	 *            to execute
	 * @return command output as String, null otherwise
	 */
	public static String executeCommand(final String command) {
		return executeCommand(command, new String[] {});
	}

	/**
	 * Execute given system command with arguments.
	 * 
	 * @param command
	 *            to execute
	 * @param args
	 *            as command arguments
	 * @return command output as String, null otherwise
	 */
	public static String executeCommand(final String command, String... args) {
		if (StringUtils.isNotEmpty(command)) {

			// create string output for executor
			ProcessStringOutput processOutput = new ProcessStringOutput(PROCESS_OUTPUT_LEVEL);
			// create external process
			Executor executor = createExecutor(processOutput);

			// create command line without any arguments
			final CommandLine commandLine = new CommandLine(command);

			if (ArrayUtils.isNotEmpty(args)) {
				// add command arguments
				commandLine.addArguments(args);
			}
			int exitValue = -1;

			try {
				// execute command
				exitValue = executor.execute(commandLine);
			} catch (IOException e) {
				// ignore exception
			}

			if (!executor.isFailure(exitValue)) {
				return processOutput.getOutput();
			}
		}
		return null;
	}

	/**
	 * Creates executor with system watchdog for given output stream.
	 * 
	 * @param outputStream
	 * @return
	 */
	private static Executor createExecutor(OutputStream outputStream) {

		// create process watchdog with timeout 60000 milliseconds
		ExecuteWatchdog watchdog = new ExecuteWatchdog(WATCHDOG_TIMEOUT);

		// set watchdog and stream handler
		Executor executor = new DefaultExecutor();
		executor.setWatchdog(watchdog);
		executor.setStreamHandler(new PumpStreamHandler(outputStream, outputStream));
		return executor;
	}
}
