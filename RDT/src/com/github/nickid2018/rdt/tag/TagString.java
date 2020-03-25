package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagString extends TagArrayable {

	public static final TagString TAG_C_STRING = new TagString();

	protected String v;

	public TagString(String v) {
		this.v = v;
	}

	private TagString() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v = inp.readUTF();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		// Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_STRING"));
		oup.writeUTF(v);
	}

	public String getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v;
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeUTF(v);
	}

	@Override
	public TagString createNew() {
		return new TagString();
	}

	@Override
	public String tagName() {
		return "TAG_STRING";
	}
}
