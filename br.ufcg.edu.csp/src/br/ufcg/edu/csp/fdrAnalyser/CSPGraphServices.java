package br.ufcg.edu.csp.fdrAnalyser;

import java.io.IOException;

import graphvizJavaPloting.GraphvizJava;

public class CSPGraphServices {
	
	private GraphvizJava gvj;
	
	public CSPGraphServices() {
		gvj = new GraphvizJava();
	}

	public void plotGraph(String[] nodes) {
		try {
			gvj.ploting(nodes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
