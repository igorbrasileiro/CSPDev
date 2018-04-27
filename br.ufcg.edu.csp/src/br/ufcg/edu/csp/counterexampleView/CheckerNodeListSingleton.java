package br.ufcg.edu.csp.counterexampleView;

import java.util.ArrayList;

import org.eclipse.ui.PlatformUI;

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
		if(list.contains(node)) {
			int index = list.indexOf(node);
			CheckerNodeDecorator existentNode = list.get(index);
			existentNode.setCheckCondition(node.getCheckCondition());
			existentNode.setCounterexamples(node.getCounterexamples());
			existentNode.setNode(node.getNode());
			existentNode.setAssertionText(node.getAssertionText());
		} else if(!list.contains(node)) {
			CheckerNodeDecorator newNode = new CheckerNodeDecorator(node.getNode());
			newNode.setNodeAsCounterexampleNode();
			newNode.setCheckCondition(node.getCheckCondition());
			newNode.setCounterexamples(node.getCounterexamples());
			newNode.setAssertionText(node.getAssertionText());
			list.add(newNode);
		}
		updateCounterexampleListView();
	}
	
	private void updateCounterexampleListView() {
		/* CODIGO UTIL
		 * for(IWorkbenchWindow workbench : PlatformUI.getWorkbench().getWorkbenchWindows()) {
				for(IWorkbenchPage workbenchPage : workbench.getPages()) {
					workbenchPage.findView(); //nome da viewpart
				}
			}
		 */
		CounterexampleListView clv = (CounterexampleListView) PlatformUI.getWorkbench().
				getActiveWorkbenchWindow().getActivePage().
					findView("br.ufcg.edu.csp.CounterexampleListView");
		
		if(clv != null) {
			clv.updateContent(getListContent());
		}
	}
	
	public Object[] getListContent() {
		if(list.size() == 0) {
			return null;
		} else {
			return list.toArray();
		}
	}

	public boolean containsNode(CheckerNodeDecorator node) {
		return list.contains(node);
	}
 }
