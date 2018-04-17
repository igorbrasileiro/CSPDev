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
		/* CODIGO UTIL
		 * for(IWorkbenchWindow workbench : PlatformUI.getWorkbench().getWorkbenchWindows()) {
				for(IWorkbenchPage workbenchPage : workbench.getPages()) {
					workbenchPage.findView(); //nome da viewpart
				}
			}
		 */
		updateListners();
	}
	
	private void updateListners() {
		//TODO: atualizar observers
	}
 }
