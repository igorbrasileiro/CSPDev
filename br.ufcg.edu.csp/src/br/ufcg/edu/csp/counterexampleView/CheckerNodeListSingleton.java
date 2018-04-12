package br.ufcg.edu.csp.counterexampleView;

import java.util.ArrayList;

public class CheckerNodeListSingleton {

	private ArrayList<CheckerNodeDecorator> list;
	private static CheckerNodeListSingleton instance;
	
	public static CheckerNodeListSingleton getInstance() {
		if(instance == null) {
			instance = new CheckerNodeListSingleton();
		}
		
		return instance;
	}
	
	private CheckerNodeListSingleton() {
		list = new ArrayList<>();
	}
	
	public void updateList(CheckerNodeDecorator node) {
		list.add(node);
		refreshCounterexampleListView();
	}
	
	private void refreshCounterexampleListView() {
		//TODO: atualizar através de factory
	}
 }
