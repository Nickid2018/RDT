package com.github.nickid2018.rdt;

import java.io.*;

public class ReadOnlyRDTFile extends RDTFile {

	public ReadOnlyRDTFile(String fn) {
		super();
		try {
			input = new DataInputStream(new FileInputStream(fn));
		} catch (FileNotFoundException e) {
			input = null;
		}
	}

	public ReadOnlyRDTFile(InputStream is) {
		super();
		input = new DataInputStream(is);
	}

	@Override
	public RDTVersion tryVersion() throws IOException {
		String head = input.readUTF();
		if (!head.equals(RDT_FILE_HEAD))
			throw new IOException("Can't parse file as RDT File.");
		if (input.readByte() != TAG_VERSION)
			throw new IOException("Can't read version information.");
		byte major = input.readByte();
		byte minor = input.readByte();
		RDTVersion ver = new RDTVersion(major, minor);
		return this.ver = ver;
	}

	@Override
	public void writeRDT(RDTObject<?> o) throws IOException {
		throw new UnsupportedOperationException("Write RDT");
	}

	@Override
	public void writeRDT() throws IOException {
		throw new UnsupportedOperationException("Write RDT");
	}

	@Override
	public void createInput() throws IOException {
		throw new UnsupportedOperationException("Create Input");
	}

	@Override
	public void createOutput() throws IOException {
		throw new UnsupportedOperationException("Create Output");
	}

	@Override
	public DataInputStream getInput() {
		return super.getInput();
	}

	@Override
	public DataOutputStream getOutput() {
		throw new UnsupportedOperationException("Get Output");
	}

	@Override
	public void destoryInput() throws IOException {
		throw new UnsupportedOperationException("Destroy Input");
	}

	@Override
	public void destoryOutput() throws IOException {
		throw new UnsupportedOperationException("Destroy Output");
	}

	@Override
	public void destory() throws IOException {
		input.close();
	}
}
