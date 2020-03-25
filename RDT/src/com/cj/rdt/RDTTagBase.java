package com.cj.rdt;

import java.io.*;

public abstract class RDTTagBase {
	
	protected RDTVersion ver=RDTFile.RDT11;

	public RDTTagBase() {
	}
	
	public abstract RDTWarn readTag(DataInput inp,RDTObject<?> o) throws IOException;
	public abstract void writeTag(DataOutput oup,RDTObject<?> o) throws IOException;
	public abstract RDTTagBase createNew();
	public abstract String tagName();
	public abstract String toString();
	
	public RDTTagBase setVersion(RDTVersion ver) {
		this.ver=ver;
		return this;
	}
	
	public RDTVersion getVersion() {
		return ver;
	}

}
