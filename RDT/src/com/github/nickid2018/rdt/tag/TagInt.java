package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagInt extends TagArrayable{
	
	public static final TagInt TAG_C_INT=new TagInt();
	
	private int v;
	
	public TagInt(int v) {
		this.v=v;
	}

	private TagInt() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v=inp.readInt();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_INT"));
		oup.writeInt(v);
	}

	public int getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v+"";
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeInt(v);
	}
	
	@Override
	public TagInt createNew() {
		return new TagInt();
	}
	
	@Override
	public String tagName() {
		return "TAG_INT";
	}
}
