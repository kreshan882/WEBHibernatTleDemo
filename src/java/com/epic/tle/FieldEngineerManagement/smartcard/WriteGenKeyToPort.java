package com.epic.tle.FieldEngineerManagement.smartcard;


import java.io.*;
import java.util.*;
import gnu.io.*;

public class WriteGenKeyToPort {

	static CommPortIdentifier portId;
	static CommPortIdentifier saveportId;
	static Enumeration portList;
	static InputStream inputStream;
	static SerialPort serialPort;

	static OutputStream outputStream;
	static boolean outputBufferEmptyFlag = false;

	public static void writKeytoport(String key, String port) throws Exception {

		try {

			CommDriver RXTXDriver = (CommDriver) Class.forName(
					"gnu.io.RXTXCommDriver").newInstance();
			RXTXDriver.initialize();

		
		} catch (Throwable e) {
			System.err.println(e + " thrown while loading "
					+ "gnu.io.RXTXCommDriver");
		}

		
		String defaultPort = "";

		if("COM".equals(KeyInectingConfig.PORT)){
			try{
				defaultPort = "/dev/ttyS0";
				
				portList = CommPortIdentifier.getPortIdentifiers();
				while (portList.hasMoreElements()) {
					portId = (CommPortIdentifier) portList.nextElement();
					if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
						if (portId.getName().equals(defaultPort)) {
							System.out.println("Found port: " + defaultPort);
							serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
							inputStream = serialPort.getInputStream();
							serialPort.notifyOnDataAvailable(true);
							serialPort.setSerialPortParams(KeyInectingConfig.DATA_RATE,
									KeyInectingConfig.DATABITS, KeyInectingConfig.STOPBITS,
									KeyInectingConfig.PARITY);
							
							outputStream = serialPort.getOutputStream();
							serialPort.notifyOnOutputEmpty(true);
							
						
							
							
						
							outputStream.write(key.getBytes());
							
							System.out.println("Successfully completed..");
							Thread.sleep(3000);
							break;
							
							
							
						}
					}
	
				}
			}catch(Exception e){
				e.printStackTrace();
				throw e;
				
			}finally {
				if (serialPort != null)
					serialPort.close();
				try {
					if (inputStream != null)
						inputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
		}
	}

}
