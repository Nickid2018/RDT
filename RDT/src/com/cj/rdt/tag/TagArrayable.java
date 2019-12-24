package com.cj.rdt.tag;

import java.io.*;
import com.cj.rdt.*;

public abstract class TagArrayable extends RDTTagBase {

	public abstract void writeItem(DataOutput oup, RDTObject<?> o) throws IOException;
}
