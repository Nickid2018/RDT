package com.cj.rdt;

import java.io.*;

public class ReadOnlyRDTObject extends RDTObject<Object> {

	public ReadOnlyRDTObject(ReadOnlyRDTFile f) throws IOException {
		super();
		f.tryVersion();
	}

	@Override
	public Object getObject() {
		throw new UnsupportedOperationException("Get Object");
	}

	@Override
	public ReadOnlyRDTFile getRDTFile() {
		return (ReadOnlyRDTFile) super.getRDTFile();
	}

	private boolean read = false;
	@Override
	public RDTWarn read() throws IOException {
		if(read)
			throw new UnsupportedOperationException("Read File Twice");
		read = true;
		RDTWarn warn = RDTWarn.create();
		DataInputStream input = f.getInput();
		// Read
		while (input.available() > 0) {
			RDTTagBase tag = f.getVersion().tryTag(f.getVersion().readNextTag(input));
			warn.addWarn(tag.readTag(input, this));
		}
		return warn.isNoWarn() ? RDTWarn.NO_WARN : warn;
	}

}
