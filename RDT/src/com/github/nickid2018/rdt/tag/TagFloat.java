package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagFloat extends TagArrayable {

	public static final TagFloat TAG_C_FLOAT = new TagFloat();

	private float v;

	public TagFloat(float v) {
		this.v = v;
	}

	private TagFloat() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v = inp.readFloat();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		// Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_FLOAT"));
		oup.writeFloat(v);
	}

	public float getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v + "";
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeFloat(v);
	}

	@Override
	public TagFloat createNew() {
		return new TagFloat();
	}

	@Override
	public String tagName() {
		return "TAG_FLOAT";
	}
}
