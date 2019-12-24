package com.cj.rdt.tag;

import java.io.*;
import com.cj.rdt.*;

public class TagDouble extends TagArrayable{

	public static final TagDouble TAG_C_DOUBLE=new TagDouble();
	
	private double v;
	
	public TagDouble(double v) {
		this.v=v;
	}

	private TagDouble() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		v=inp.readDouble();
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_DOUBLE"));
		oup.writeDouble(v);
	}

	public double getVal() {
		return v;
	}

	@Override
	public String toString() {
		return v+"";
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeDouble(v);
	}

	@Override
	public TagDouble createNew() {
		return new TagDouble();
	}
	
	@Override
	public String tagName() {
		return "TAG_DOUBLE";
	}
}
