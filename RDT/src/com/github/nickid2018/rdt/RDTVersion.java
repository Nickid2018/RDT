package com.github.nickid2018.rdt;

import java.io.*;
import java.util.*;

import com.github.nickid2018.rdt.versions.*;

import java.lang.reflect.*;

public class RDTVersion {
	
	public static final Map<List<Integer>,Class<?>> VERSIONS=new HashMap<>();
	
	private int major;
	private int minor;
	private Class<?> vers;

	public RDTVersion(int major,int minor) throws VersionException{
		this.major=major;
		this.minor=minor;
		boolean finded=false;
		for(Map.Entry<List<Integer>, Class<?>> en:VERSIONS.entrySet()) {
			List<Integer> ver=en.getKey();
			if(ver.get(0)==major&&ver.get(1)==minor) {
				finded=true;
				vers=en.getValue();
				break;
			}
		}
		if(!finded)throw new VersionException(major,minor);
	}
	
	RDTVersion(int major,int minor,Class<?> versionIns){
		vers=versionIns;
	}
	
	public int getMajorVersion() {
		return major;
	}

	public int getMinorVersion() {
		return minor;
	}

	public final byte readNextTag(DataInput input) throws IOException {
		return input.readByte();
	}
	
	public final RDTTagBase tryTag(byte b) throws TagException {
		try {
			return ((RDTTagBase) vers.getDeclaredMethod("tryTag", byte.class).invoke(vers, b)).setVersion(this);
		} catch (InvocationTargetException e) {
			throw (TagException)e.getCause();
		} catch (Exception e) {
			throw new TagException("Error in try-tag");
		}
	}
	
	public final byte getTagSign(String name) throws TagException{
		try {
			return (byte)vers.getField(name).getByte(vers);
		} catch (Exception e) {
			throw new TagException("Error in finding sign");
		}
	}

	@Override
	public String toString() {
		return major+"."+minor;
	}
	
	static {
		VERSIONS.put(Arrays.asList(1,1), RDT11.class);
	}
}
