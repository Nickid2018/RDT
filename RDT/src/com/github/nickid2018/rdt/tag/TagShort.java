package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagShort extends TagArrayable {

	public static final TagShort TAG_C_SHORT = new TagShort();

	private short v;

	public TagShort(short v) {
		this.v = v;
	}

	private TagShort() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v = inp.readShort();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		// Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_SHORT"));
		oup.writeShort(v);
	}

	public short getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v + "";
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeShort(v);
	}

	@Override
	public TagShort createNew() {
		return new TagShort();
	}

	@Override
	public String tagName() {
		return "TAG_SHORT";
	}
}
