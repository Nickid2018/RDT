package com.github.nickid2018.rdt;

import java.util.*;

public class RDTReaderUtils {
	
	public static class Sign{
		
		private final byte bsign;
		private final int major,minor;
		
		public Sign(byte bsign,int major,int minor) {
			this.major = major;
			this.minor = minor;
			this.bsign = bsign;
		}

		public byte getBsign() {
			return bsign;
		}

		public int getMinor() {
			return minor;
		}

		public int getMajor() {
			return major;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Sign))
				return false;
			Sign s=(Sign) obj;
			return bsign==s.bsign&&major==s.major&&minor==s.minor;
		}
 	}
	
	private static final Map<Sign,RDTReader> regedcomp=new HashMap<>();

	public static final void addCompiler(Sign s,RDTReader r) {
		regedcomp.put(s, r);
	}
	
	public static final void remove(Sign s) {
		regedcomp.remove(s);
	}
	
	public static final RDTReader forceFind(Sign s) {
		for(Map.Entry<Sign, RDTReader> re:regedcomp.entrySet()) {
			if(re.getKey().equals(s))
				return re.getValue();
		}
		return null;
	}
	
	public static final Set<RDTReader> find(byte bsign) {
		Set<RDTReader> rm=new HashSet<>();
		for(Map.Entry<Sign, RDTReader> en:regedcomp.entrySet()) {
			if(en.getKey().getBsign()==bsign)rm.add(en.getValue());
		}
		return rm;
	}
	
	public static final byte getByte(RDTReader r) {
		for(Map.Entry<Sign, RDTReader> en:regedcomp.entrySet()) {
			if(en.getValue().equals(r))
				return en.getKey().getBsign();
		}
		return -128;
	}
}
