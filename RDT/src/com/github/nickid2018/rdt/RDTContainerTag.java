package com.github.nickid2018.rdt;

import static com.github.nickid2018.rdt.RDTFile.*;

import java.io.*;
import java.util.*;

import com.github.nickid2018.rdt.RDTReaderUtils.*;

public class RDTContainerTag extends RDTTagBase {

	protected ArrayList<RDTTagBase> tags;
	private RDTReader r;

	public RDTContainerTag() {
		this(new ArrayList<>(), null);
	}

	public RDTContainerTag(RDTReader r) {
		this(new ArrayList<>(), r);
	}

	public RDTContainerTag(ArrayList<RDTTagBase> ar) {
		this(ar, null);
	}

	public RDTContainerTag(ArrayList<RDTTagBase> ar, RDTReader r) {
		tags = ar;
		this.r = r;
	}

	public RDTContainerTag setReader(RDTReader r) {
		this.r = r;
		return this;
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		RDTWarn warn = RDTWarn.create();
		// Reader
		byte reader = inp.readByte();
		r = RDTReaderUtils.forceFind(new Sign(reader, ver.getMajorVersion(), ver.getMinorVersion()));
		if (r == null) {
			warn.addWarn(new RDTWarn("Cannot find reader with correct version:sign " + reader + " version " + ver));
			Set<RDTReader> reds = RDTReaderUtils.find(reader);
			if (reds.size() == 0)
				throw new IOException("Cannot find reader:sign " + reader);
			for (RDTReader re : reds) {
				r = re;
			}
		}
		// Read
		while (((DataInputStream) inp).available() > 0) {
			byte tagSign = ver.readNextTag(inp);
			if (tagSign == RDTFile.TAG_END)
				break;
			RDTTagBase tag = ver.tryTag(tagSign);
			warn.addWarn(tag.readTag(inp, o));
			if (tagSign != RDTFile.TAG_START)
				r.read(tag, warn);
			tags.add(tag);
		}
		return warn.isNoWarn() ? RDTWarn.NO_WARN : warn;
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		oup.writeByte(TAG_START);
		oup.writeByte(RDTReaderUtils.getByte(r));
		for (RDTTagBase tag : tags) {
			tag.writeTag(oup, o);
		}
		oup.writeByte(TAG_END);
	}

	public void addTag(RDTTagBase tag) {
		tags.add(tag);
	}

	public void removeTag(RDTTagBase tag) {
		tags.remove(tag);
	}

	@Override
	public RDTContainerTag createNew() {
		return new RDTContainerTag();
	}

	@Override
	public String tagName() {
		return "";
	}

	@Override
	public String toString() {
		return "";
	}
}
