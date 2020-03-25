package com.github.nickid2018.rdt;

public class VersionException extends RuntimeException {

	public VersionException(int major, int minor) {
		super("RDT Version is not exists:" + major + "." + minor);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4622586320211102004L;

}
