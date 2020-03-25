package com.github.nickid2018.rdt.tag;

import java.io.*;

import com.github.nickid2018.rdt.*;

public abstract class TagArrayable extends RDTTagBase {

	public abstract void writeItem(DataOutput oup, RDTObject<?> o) throws IOException;
}
