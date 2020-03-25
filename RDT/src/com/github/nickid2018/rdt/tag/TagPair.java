package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagPair extends TagArrayable {
	
	public static final TagPair TAG_C_PAIR=new TagPair();
	
	private RDTTagBase k,v;
	
	public TagPair(RDTTagBase k,RDTTagBase v) {
		this.k=k;
		this.v=v;
	}

	private TagPair() {
	}

	public RDTTagBase getKey() {
		return k;
	}

	public RDTTagBase getValue() {
		return v;
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		k.writeTag(oup, o);
		v.writeTag(oup, o);
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		RDTWarn warn = RDTWarn.create();
		byte tagSign=ver.readNextTag(inp);
		k = ver.tryTag(tagSign);
		warn.addWarn(k.readTag(inp, o));
		tagSign=ver.readNextTag(inp);
		v = ver.tryTag(tagSign);
		warn.addWarn(v.readTag(inp, o));
		return warn.isNoWarn() ? RDTWarn.NO_WARN : warn;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_PAIR"));
		k.writeTag(oup, o);
		v.writeTag(oup, o);
	}

	@Override
	public RDTTagBase createNew() {
		return new TagPair();
	}

	@Override
	public String tagName() {
		return "TAG_PAIR";
	}

	@Override
	public String toString() {
		return k+"="+v;
	}

}
