package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagByteArray extends TagArrayable{

	public static final TagByteArray TAG_C_BYTE_ARRAY=new TagByteArray();
	
	private byte[] array;

	public TagByteArray(byte[] arr){
		array=arr;
	}

	private TagByteArray() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		int len=inp.readInt();
		array=new byte[len];
		for(int i=0;i<len;i++) {
			array[i]=inp.readByte();
		}
		return RDTWarn.NO_WARN;
	}
	
	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_BYTE_ARRAY"));
		oup.writeInt(array.length);
		for(int i=0;i<array.length;i++) {
			oup.writeByte(array[i]);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder tos=new StringBuilder("Byte Array");
		for(byte b:array) {
			tos.append(" "+b);
		}
		return tos.toString();
	}
	
	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeInt(array.length);
		for(int i=0;i<array.length;i++) {
			oup.writeByte(array[i]);
		}
	}
	
	@Override
	public TagByteArray createNew() {
		return new TagByteArray();
	}
	
	@Override
	public String tagName() {
		return "TAG_BYTE_ARRAY";
	}
}
