package com.github.nickid2018.rdt;

import java.io.*;
import java.util.*;

public class WritableRDTFile extends RDTFile {

	private ArrayList<RDTTagBase> tags;

	public WritableRDTFile(String fn, ArrayList<RDTTagBase> ar, RDTVersion version) {
		super();
		tags = ar;
		ver = version;
		try {
			output = new DataOutputStream(new FileOutputStream(fn));
		} catch (FileNotFoundException e) {
			output = null;
		}
	}

	public WritableRDTFile(OutputStream os, ArrayList<RDTTagBase> ar, RDTVersion version) {
		super();
		tags = ar;
		ver = version;
		output = new DataOutputStream(os);
	}

	@Override
	public RDTVersion tryVersion() throws IOException {
		throw new UnsupportedOperationException("Try Version");
	}

	@Override
	public void writeRDT(RDTObject<?> o) throws IOException {
		output.writeUTF(RDT_FILE_HEAD);
		output.writeByte(TAG_VERSION);
		output.writeByte(ver.getMajorVersion());
		output.writeByte(ver.getMinorVersion());
		for (RDTTagBase tag : tags) {
			tag.writeTag(output, o);
		}
	}

	@Override
	public void writeRDT() throws IOException {
		RDTObject<Object> nullo = new RDTObject<>(this);
		output.writeUTF(RDT_FILE_HEAD);
		output.writeByte(TAG_VERSION);
		output.writeByte(ver.getMajorVersion());
		output.writeByte(ver.getMinorVersion());
		for (RDTTagBase tag : tags) {
			tag.writeTag(output, nullo);
		}
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
		throw new UnsupportedOperationException("Get Input");
	}

	@Override
	public DataOutputStream getOutput() {
		return super.getOutput();
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
		output.close();
	}

}
