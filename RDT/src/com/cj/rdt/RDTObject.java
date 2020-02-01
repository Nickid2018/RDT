package com.cj.rdt;

import java.io.*;

public class RDTObject<T> {

	private final T obj;
	protected RDTFile f;

	protected RDTObject() {
		obj = null;
	}

	public RDTObject(T sobj, String file, RDTVersion ver) throws IOException {
		obj = sobj;
		f = new RDTFile(file, ver);
	}

	public RDTObject(T sobj, String file) throws IOException {
		obj = sobj;
		f = new RDTFile(file);
		f.tryVersion();
	}

	public RDTObject(T sobj, RDTFile f) {
		this.obj = sobj;
		this.f = f;
	}

	public RDTObject(RDTFile f) {
		this.obj = null;
		this.f = f;
	}

	public T getObject() {
		return obj;
	}

	public RDTFile getRDTFile() {
		return f;
	}

	public RDTWarn read() throws IOException {
		RDTWarn warn = RDTWarn.create();
		f.destory();
		f.createInput();
		DataInputStream input = f.getInput();
		// Skip RDF_FILE_HEAD
		input.readUTF();
		// Skip RDF_VERSION
		input.readByte();
		input.readByte();
		// Read
		while (input.available() > 0) {
			RDTTagBase tag = f.getVersion().tryTag(f.getVersion().readNextTag(input));
			warn.addWarn(tag.readTag(input, this));
		}
		f.destoryInput();
		return warn.isNoWarn() ? RDTWarn.NO_WARN : warn;
	}
}
