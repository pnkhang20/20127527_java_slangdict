/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package k.slangdict;

/**
 *
 * @author kp
 */
import java.util.*;
import java.util.Map.Entry;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Slang {
    	private TreeMap<String, List<String>> map = new TreeMap<>();
	private static Slang obj = new Slang();
	private int sizeMap;
	private String FILE_SLANG = "slang.txt";
	private String FILE_SLANG_DEFAULT = "slang-default.txt";
	private String FILE_HISTORY = "history.txt";
	public Slang() {
		try {
			String current = new java.io.File(".").getCanonicalPath();
			FILE_SLANG = current + "\\" + FILE_SLANG;
			FILE_SLANG_DEFAULT = current + "\\" + FILE_SLANG_DEFAULT;
			FILE_HISTORY = current + "\\" + FILE_HISTORY;
			readFile(FILE_SLANG);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
