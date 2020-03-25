package com.github.nickid2018.rdt.tag;

import java.io.*;
import java.util.*;

import com.github.nickid2018.rdt.*;

public class TagBase64 extends TagArrayable {

	public static final TagBase64 TAG_C_BASE64 = new TagBase64();

	public static final byte FUNC_DEFAULT = 0x0;
	public static final byte FUNC_MIME = 0x1;
	public static final byte FUNC_URL_SAFE = 0x2;

	private byte[] v;
	private byte func;

	public TagBase64(byte[] v) {
		this(v, FUNC_DEFAULT);
	}

	public TagBase64(byte[] v, byte func) {
		this.v = v;
		this.func = func;
	}

	private TagBase64() {
	}

	public byte getFunc() {
		return func;
	}

	public void setFunc(byte func) {
		this.func = func;
	}

	@Override
	public void writeItem(DataOutput oup, RDTObject<?> o) throws IOException {
		Base64.Encoder e = null;
		switch (func) {
		case FUNC_DEFAULT:
			e = Base64.getEncoder();
			break;
		case FUNC_MIME:
			e = Base64.getMimeEncoder();
			break;
		case FUNC_URL_SAFE:
			e = Base64.getUrlEncoder();
			break;
		default:
			throw new IOException("Invalid argument");
		}
		String w = e.encodeToString(v);
		oup.writeByte(func);
		oup.writeUTF(w);
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		func = inp.readByte();
		Base64.Decoder e = null;
		switch (func) {
		case FUNC_DEFAULT:
			e = Base64.getDecoder();
			break;
		case FUNC_MIME:
			e = Base64.getMimeDecoder();
			break;
		case FUNC_URL_SAFE:
			e = Base64.getUrlDecoder();
			break;
		default:
			throw new IOException("Invalid argument");
		}
		String s = inp.readUTF();
		v = e.decode(s);
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		Base64.Encoder e = null;
		switch (func) {
		case FUNC_DEFAULT:
			e = Base64.getEncoder();
			break;
		case FUNC_MIME:
			e = Base64.getMimeEncoder();
			break;
		case FUNC_URL_SAFE:
			e = Base64.getUrlEncoder();
			break;
		default:
			throw new IOException("Invalid argument");
		}
		String w = e.encodeToString(v);
		// Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_BASE64"));
		oup.writeByte(func);
		oup.writeUTF(w);
	}

	@Override
	public RDTTagBase createNew() {
		return new TagBase64();
	}

	@Override
	public String tagName() {
		return "TAG_BASE64";
	}

	public byte[] getVal() {
		return v;
	}

	@Override
	public String toString() {
		return new String(v);
	}

}
