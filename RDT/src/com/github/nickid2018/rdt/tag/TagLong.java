package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagLong extends TagArrayable{
	
	public static final TagLong TAG_C_LONG=new TagLong();
	
	private long v;
	
	public TagLong(long v) {
		this.v=v;
	}

	private TagLong() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v=inp.readLong();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_LONG"));
		oup.writeLong(v);
	}

	public long getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v+"";
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeLong(v);
	}
	
	@Override
	public TagLong createNew() {
		return new TagLong();
	}
	
	@Override
	public String tagName() {
		return "TAG_LONG";
	}
}
