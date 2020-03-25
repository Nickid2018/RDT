package com.cj.rdt.tag;

import java.io.*;
import com.cj.rdt.*;

public class TagEnd extends RDTTagBase {

	@Override
	public RDTWarn readTag(DataInput inp,RDTObject<?> o) {
		//The function is only to override but real meaning
		return RDTWarn.NO_WARN;
	}

	@Override
	public void writeTag(DataOutput oup,RDTObject<?> o) throws IOException {
		//The function should be called in RDTContainerTag
		//The function is only to override but real meaning
	}

	@Override
	public TagEnd createNew() {
		return new TagEnd();
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
