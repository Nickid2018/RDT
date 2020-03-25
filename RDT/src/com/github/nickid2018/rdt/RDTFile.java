package com.github.nickid2018.rdt;

import java.io.*;
import java.util.*;

import com.github.nickid2018.rdt.tag.*;

public class RDTFile extends RDTContainerTag {

	public static final RDTVersion RDT11 = new RDTVersion(1, 1);

	public static final byte TAG_START = 0x0;
	public static final byte TAG_END = 0x1;
	public static final byte TAG_VERSION = -1;

	public static final String RDT_FILE_HEAD = "RDT";

	public static final RDTTagBase TAG_C_START = new TagStart();
	public static final RDTTagBase TAG_C_END = new TagEnd();

	protected String file;
	protected DataInputStream input;
	protected DataOutputStream output;

	protected RDTFile() {
	}

	public RDTFile(String fn) {
		super();
		file = fn;
	}

	public RDTFile(String fn, ArrayList<RDTTagBase> ar) {
		super(ar);
		file = fn;
	}

	public RDTFile(String fn, RDTVersion ver) {
		super();
		file = fn;
		this.ver = ver;
	}

	public RDTFile(String fn, RDTVersion ver, ArrayList<RDTTagBase> ar) {
		super(ar);
		file = fn;
		this.ver = ver;
	}

	public RDTVersion tryVersion() throws IOException {
		createInput();
		String head = input.readUTF();
		if (!head.equals(RDT_FILE_HEAD))
			throw new IOException("Can't parse file as RDT File.");
		if (input.readByte() != TAG_VERSION)
			throw new IOException("Can't read version information.");
		byte major = input.readByte();
		byte minor = input.readByte();
		destoryInput();
		RDTVersion ver = new RDTVersion(major, minor);
		return this.ver = ver;
	}

	@Override
	public final RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		throw new IOException("Refuse access");
	}

	@Override
	public final RDTContainerTag setReader(RDTReader r) {
		return this;
	}

	@Override
	public final void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		throw new IOException("Refuse access");
	}

	public void writeRDT(RDTObject<?> o) throws IOException {
		createOutput();
		output.writeUTF(RDT_FILE_HEAD);
		output.writeByte(TAG_VERSION);
		output.writeByte(ver.getMajorVersion());
		output.writeByte(ver.getMinorVersion());
		for (RDTTagBase tag : tags) {
			tag.writeTag(output, o);
		}
		destoryOutput();
	}

	public void writeRDT() throws IOException {
		RDTObject<Object> nullo = new RDTObject<>(this);
		createOutput();
		output.writeUTF(RDT_FILE_HEAD);
		output.writeByte(TAG_VERSION);
		output.writeByte(ver.getMajorVersion());
		output.writeByte(ver.getMinorVersion());
		for (RDTTagBase tag : tags) {
			tag.writeTag(output, nullo);
		}
		destoryOutput();
	}

	public void createInput() throws IOException {
		destoryOutput();
		input = new DataInputStream(new FileInputStream(file));
	}

	public void createOutput() throws IOException {
		destoryInput();
		output = new DataOutputStream(new FileOutputStream(file));
	}

	public DataInputStream getInput() {
		return input;
	}

	public DataOutputStream getOutput() {
		return output;
	}

	public void destoryInput() throws IOException {
		if (input != null) {
			input.close();
			input = null;
		}
	}

	public void destoryOutput() throws IOException {
		if (output != null) {
			output.close();
			output = null;
		}
	}

	public void destory() throws IOException {
		destoryInput();
		destoryOutput();
	}
}
