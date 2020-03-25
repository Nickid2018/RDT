package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagByte extends TagArrayable {

	public static final TagByte TAG_C_BYTE = new TagByte();

	private byte v;

	public TagByte(byte v) {
		this.v = v;
	}

	private TagByte() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v = inp.readByte();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		// Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_BYTE"));
		oup.writeByte(v);
	}

	public byte getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v + "";
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeByte(v);
	}

	@Override
	public TagByte createNew() {
		return new TagByte();
	}

	@Override
	public String tagName() {
		return "TAG_BYTE";
	}
}
