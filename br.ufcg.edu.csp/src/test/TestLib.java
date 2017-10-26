package test;

import java.util.Properties;

public class TestLib {
	public static void main(String[] args) {
		Properties props = System.getProperties();
		props.keySet().stream().filter( o -> o.toString().startsWith("java"))
		 .forEach( k -> System.out.println(k.toString() + " = " + props.getProperty(k.toString())));
		//forEach(k -> System.out.println(k.toString()));
		System.loadLibrary("libfdr_java.dll");
		uk.ac.ox.cs.fdr.fdr.libraryInit();
		System.out.println("Funcionou");
	}
}
