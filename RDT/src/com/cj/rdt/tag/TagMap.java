package com.cj.rdt.tag;

import java.io.*;
import java.util.*;
import com.cj.rdt.*;
import java.util.Map.*;

public class TagMap<K extends TagArrayable,V extends TagArrayable> extends RDTTagBase {
	
	public static final TagMap<?,?> TAG_C_MAP=new TagMap<>();
	
	private Map<K,V> map;
	
	public TagMap(Map<K,V> map) {
		this.map=map;
	}

	public TagMap() {
		this(new HashMap<>());
	}

	public Map<K,V> getMap() {
		return map;
	}
	
	public void addTag(K k,V v) {
		map.put(k, v);
	}
	
	public void removeTag(K e) {
		map.remove(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		RDTWarn warn = RDTWarn.create();
		byte ktagSign=ver.readNextTag(inp);
		TagArrayable k=(TagArrayable) ver.tryTag(ktagSign);
		byte vtagSign=ver.readNextTag(inp);
		TagArrayable v=(TagArrayable) ver.tryTag(vtagSign);
		int length=inp.readInt();
		for(int i=0;i<length;i++) {
			K ko=(K) k.createNew();
			V vo=(V) v.createNew();
			map.put(ko, vo);
			warn.addWarn(ko.readTag(inp, o));
			warn.addWarn(vo.readTag(inp, o));
		}
		return warn.isNoWarn() ? RDTWarn.NO_WARN : warn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		if(map.size()==0)
			throw new IOException("Map is null!");
		//Sign write
		oup.writeByte(o.getRDTFile().getVersion().getTagSign("TAG_MAP"));
		Map.Entry<K, V>[] ens=new Map.Entry[map.size()];
		ens=map.entrySet().toArray(ens);
		oup.writeByte(ver.getTagSign(ens[0].getKey().tagName()));
		oup.writeByte(ver.getTagSign(ens[0].getValue().tagName()));
		oup.writeInt(ens.length);
		for(int i=0;i<ens.length;i++) {
			ens[i].getKey().writeItem(oup, o);
			ens[i].getValue().writeItem(oup, o);
		}
	}

	@Override
	public RDTTagBase createNew() {
		return new TagMap<>();
	}

	@Override
	public String tagName() {
		return "TAG_MAP";
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder("[");
		for(Entry<K, V> tag:map.entrySet()) {
			sb.append(tag.getKey()+"="+tag.getValue()+",");
		}
		if(sb.length()!=1)
			sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}

}
