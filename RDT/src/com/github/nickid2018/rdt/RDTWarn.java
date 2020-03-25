package com.github.nickid2018.rdt;

import java.util.*;

public class RDTWarn {

	public static final RDTWarn NO_WARN=new RDTWarn();
	
	public static final RDTWarn create() {
		return new RDTWarn();
	}
	
	private String warn="";
	private final ArrayList<RDTWarn> sub=new ArrayList<>();
	
	private RDTWarn() {}
	
	public RDTWarn(String warn) {
		this.warn=warn;
	}

	public String getWarn() {
		return warn;
	}
	
	public void addWarn(RDTWarn warn) {
		if(warn.equals(NO_WARN))
			return;
		sub.add(warn);
	}
	
	public boolean isNoWarn() {
		return sub.size()==0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder(warn);
		for(RDTWarn s:sub) {
			sb.append("\n"+s);
		}
		return sb.toString().trim();
	}
}
