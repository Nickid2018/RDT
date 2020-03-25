package com.cj.rdt.tag;

import java.io.*;
import com.cj.rdt.*;
import java.nio.charset.*;

public class TagStringCharset extends TagString {
	
	public static final TagStringCharset TAG_C_STRING_CHARSET=new TagStringCharset();
	
	private Charset charset;

	public TagStringCharset(String v,Charset c) {
		super(v);
		charset=c;
	}
	
	public TagStringCharset(String v, String ch) {
		this(v,Charset.forName(ch));
	}

	private TagStringCharset() {
		super("");
	}
	
	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		String charsetn=inp.readUTF();
		charset=Charset.forName(charsetn);
		int len=inp.readInt();
		byte[] encodes=new byte[len];
		for(int i=0;i<len;i++) {
			encodes[i]=inp.readByte();
		}
		v=new String(encodes,charset);
		return RDTWarn.NO_WARN;
	}
	
	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_STRING_CHARSET"));
		oup.writeUTF(charset.name());
		byte[] encodes=v.getBytes(charset);
		oup.writeInt(encodes.length);
		for(int i=0;i<encodes.length;i++) {
			oup.writeByte(encodes[i]);
		}
	}
	
	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeUTF(charset.name());
		byte[] encodes=v.getBytes(charset);
		oup.writeInt(encodes.length);
		for(int i=0;i<encodes.length;i++) {
			oup.writeByte(encodes[i]);
		}
	}
	
	@Override
	public TagStringCharset createNew() {
		return new TagStringCharset();
	}
	
	@Override
	public String tagName() {
		return "TAG_STRING_CHARSET";
	}
}
