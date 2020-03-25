package com.cj.rdt;

public class TagException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1638660435417681309L;

	public TagException(RDTVersion ver, byte tag) {
		super("Tag Error:version "+ver.toString()+" at tag byte "+tag);
	}

	public TagException(String s) {
		super(s);
	}
}
