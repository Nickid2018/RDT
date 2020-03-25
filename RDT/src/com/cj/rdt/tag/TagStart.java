package com.cj.rdt.tag;

import java.io.*;
import com.cj.rdt.*;

public class TagStart extends RDTContainerTag {
	
	public TagStart() {
	}

	@Override
	public RDTWarn readTag(DataInput inp, RDTObject<?> o) throws IOException {
		
		RDTContainerTag tag=new RDTContainerTag();
		return tag.readTag(inp, o);
	}

	@Override
	public void writeTag(DataOutput oup, RDTObject<?> o) throws IOException {
		//The function should be called in RDTContainerTag
		//The function is only to override but real meaning
	}

	@Override
	public TagStart createNew() {
		return new TagStart();
	}
}
