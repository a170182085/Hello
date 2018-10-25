package com.my.test;

import java.io.File;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:\\report\\");
		if(!file.exists()) {
			file.mkdir();
		}
		
	}

}
