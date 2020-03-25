package com.github.nickid2018.rdt.versions;

import java.util.*;

import com.github.nickid2018.rdt.*;
import com.github.nickid2018.rdt.tag.TagArray;
import com.github.nickid2018.rdt.tag.TagBase64;
import com.github.nickid2018.rdt.tag.TagByte;
import com.github.nickid2018.rdt.tag.TagByteArray;
import com.github.nickid2018.rdt.tag.TagDouble;
import com.github.nickid2018.rdt.tag.TagFloat;
import com.github.nickid2018.rdt.tag.TagInt;
import com.github.nickid2018.rdt.tag.TagLong;
import com.github.nickid2018.rdt.tag.TagMap;
import com.github.nickid2018.rdt.tag.TagPair;
import com.github.nickid2018.rdt.tag.TagSerializable;
import com.github.nickid2018.rdt.tag.TagShort;
import com.github.nickid2018.rdt.tag.TagString;
import com.github.nickid2018.rdt.tag.TagStringCharset;

public class RDT11 {

	private static final Map<Byte, RDTTagBase> tags = new HashMap<>();

	public static final byte TAG_INT = 0x2;
	public static final byte TAG_DOUBLE = 0x3;
	public static final byte TAG_FLOAT = 0x4;
	public static final byte TAG_LONG = 0x5;
	public static final byte TAG_SHORT = 0x6;
	public static final byte TAG_BYTE = 0x7;

	public static final byte TAG_STRING = 0x8;
	public static final byte TAG_STRING_CHARSET = 0x9;

	public static final byte TAG_BYTE_ARRAY = 0xA;
	public static final byte TAG_ARRAY = 0xB;

	public static final byte TAG_PAIR = 0xC;
	public static final byte TAG_MAP = 0xD;

	public static final byte TAG_SERIALIZABLE = 0xE;

	public static final byte TAG_BASE64 = 0xF;

	public static final void addTag(byte b, RDTTagBase tag) {
		tags.put(b, tag);
	}

	public static final void removeTag(byte b) {
		tags.remove(b);
	}

	public static final RDTTagBase tryTag(byte tag) {
		for (Map.Entry<Byte, RDTTagBase> en : tags.entrySet()) {
			if (en.getKey() == tag)
				return en.getValue().createNew();
		}
		throw new TagException(RDTFile.RDT11, tag);
	}

	static {
		tags.put(RDTFile.TAG_START, RDTFile.TAG_C_START);
		tags.put(RDTFile.TAG_END, RDTFile.TAG_C_END);
		tags.put(TAG_INT, TagInt.TAG_C_INT);
		tags.put(TAG_DOUBLE, TagDouble.TAG_C_DOUBLE);
		tags.put(TAG_FLOAT, TagFloat.TAG_C_FLOAT);
		tags.put(TAG_LONG, TagLong.TAG_C_LONG);
		tags.put(TAG_SHORT, TagShort.TAG_C_SHORT);
		tags.put(TAG_BYTE, TagByte.TAG_C_BYTE);
		tags.put(TAG_STRING, TagString.TAG_C_STRING);
		tags.put(TAG_STRING_CHARSET, TagStringCharset.TAG_C_STRING_CHARSET);
		tags.put(TAG_BYTE_ARRAY, TagByteArray.TAG_C_BYTE_ARRAY);
		tags.put(TAG_ARRAY, TagArray.TAG_C_ARRAY);
		tags.put(TAG_PAIR, TagPair.TAG_C_PAIR);
		tags.put(TAG_MAP, TagMap.TAG_C_MAP);
		tags.put(TAG_SERIALIZABLE, TagSerializable.TAG_C_SERIALIZABLE);
		tags.put(TAG_BASE64, TagBase64.TAG_C_BASE64);
	}
}
