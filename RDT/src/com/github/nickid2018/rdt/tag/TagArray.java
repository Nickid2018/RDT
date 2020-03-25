package com.github.nickid2018.rdt.tag;

import java.io.*;
import java.util.*;

import com.github.nickid2018.rdt.*;

public class TagArray<E extends TagArrayable> extends RDTTagBase {

	public static final TagArray<?> TAG_C_ARRAY = new TagArray<>();

	private ArrayList<E> tags;

	public TagArray() {
		this(new ArrayList<>());
	}

	public TagArray(ArrayList<E> tags) {
		this.tags = tags;
	}

	public void addTag(E e) {
		tags.add(e);
	}

	public void removeTag(E e) {
		tags.remove(e);
	}

	public void removeTag(int index) {
		tags.remove(index);
	}

	public ArrayList<E> getTags() {
		return tags;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		RDTWarn warn = RDTWarn.create();
		byte arraySign = inp.readByte();
		RDTTagBase tag = ver.tryTag(arraySign);
		int len = inp.readInt();
		for (int i = 0; i < len; i++) {
			RDTTagBase add = tag.createNew().setVersion(ver);
			warn.addWarn(add.readTag(inp, o));
			tags.add((E) add);
		}
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		if (tags.size() == 0)
			throw new IOException("Array is null!");
		// Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_ARRAY"));
		RDTTagBase sign = tags.get(0);
		oup.writeByte(ver.getTagSign(sign.tagName()));
		oup.writeInt(tags.size());
		for (TagArrayable tag : tags) {
			tag.writeItem(oup, o);
		}
	}

	@Override
	public RDTTagBase createNew() {
		return new TagArray<>();
	}

	@Override
	public String tagName() {
		return "TAG_ARRAY";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (RDTTagBase tag : tags) {
			sb.append(tag + ",");
		}
		if (sb.length() != 1)
			sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

}
