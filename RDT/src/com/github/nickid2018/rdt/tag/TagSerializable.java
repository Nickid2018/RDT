package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public class TagSerializable extends RDTTagBase {
	
	public static final TagSerializable TAG_C_SERIALIZABLE=new TagSerializable();
	
	private Serializable obj;
	
	private TagSerializable() {
		this(null);
	}
	
	public TagSerializable(Serializable obj) {
		this.obj=obj;
	}
	
	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		DataInputStream is=(DataInputStream) inp;
		ObjectInputStream ois=new ObjectInputStream(is);
		try {
			obj=(Serializable) ois.readObject();
		} catch (Exception e) {
			throw new IOException(e);
		}
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_SERIALIZABLE"));
		DataOutputStream dos=(DataOutputStream) oup;
		ObjectOutputStream oos=new ObjectOutputStream(dos);
		oos.writeObject(obj);
	}
	
	@Override
	public String toString() {
		return obj.toString();
	}

	@Override
	public RDTTagBase createNew() {
		return new TagSerializable();
	}

	@Override
	public String tagName() {
		return "TAG_SERIALIZABLE";
	}

}
