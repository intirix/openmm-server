package com.intirix.openmm.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class RollingOutputStream extends FilterOutputStream
{
	private int rollingThresh;
	private String nameBase;
	private volatile int currentLine;
	private int numRoll;

	public RollingOutputStream(File file, int rollingSize) throws IOException {
		super(null);
		rollingThresh = rollingSize;
		if (rollingThresh < 1000)
			rollingThresh = 1000;
		nameBase = file.getPath();
		out = new FileOutputStream(file);
	}
	
	

	@Override
	public void write( byte[] b ) throws IOException
	{
		super.write( b );
		flush();
	}



	@Override
	public void write( byte[] arg0, int arg1, int arg2 ) throws IOException
	{
		super.write( arg0, arg1, arg2 );
		flush();
	}



	@Override
	public void flush() throws IOException {
		super.flush();
		if (currentLine++ > rollingThresh) {
			synchronized (this) {
				if (currentLine++ > rollingThresh) {
					OutputStream out2 = out;
					out = new FileOutputStream(nameBase + "." + (numRoll++));
					out2.close();
					currentLine = 0;
				}
			}
		}
	}

}
