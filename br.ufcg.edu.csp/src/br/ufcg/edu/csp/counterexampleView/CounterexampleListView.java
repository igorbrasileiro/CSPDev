package br.ufcg.edu.csp.counterexampleView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.part.ViewPart;

import br.ufcg.edu.csp.CSPDocumentProvider;
import graphvizJavaPloting.GraphvizJava;
import br.ufcg.edu.csp.jetty.FileServer;


public class CounterexampleListView extends ViewPart {

	private TableViewer viewer;
	private CheckerNodeListSingleton cnls;
	private Action doubleClickAction;
	private String projectFilePath;
	private GraphvizJava cgs;
	FileServer fileServer;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new CounterexampleContentProvider());
		viewer.setLabelProvider(new CSPViewLabelProvider());
		
		cnls = CheckerNodeListSingleton.getInstance();
		Object[] content = cnls.getListContent();
		if(content != null)
			viewer.setInput(content);
		
		createDoubleClickAction();
		hookDoubleClickAction();
		
		cgs = new GraphvizJava();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public void updateContent(Object[] content) {
		viewer.setInput(content);
	}

	private void createDoubleClickAction() {
		doubleClickAction = new Action() {
			public void run() {
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				// chamar o contra exemplo
				//System.out.println("Double-click detected on "+obj.toString());
				if(obj instanceof CheckerNodeDecorator 
						&& !((CheckerNodeDecorator)obj).getCheckCondition()) {
					openCounterexampleBrowser(((CheckerNodeDecorator) obj).getCounterexamples());
				}
				
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(o -> doubleClickAction.run());
	}
	
	private void openCounterexampleBrowser(String[] nodes) {
		try{
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
			// trocar instancia caso queira um único browser
			IWebBrowser browser = support.createBrowser(IWorkbenchBrowserSupport.AS_EDITOR, null, "CSP Editor", "Checker counterexample");
			
			String htmlPagePath = createCounterExamplesFiles(nodes);
			String directory = createOutputDirectory();
			
			startServer(directory, htmlPagePath);
			//System.out.println(file);
			//System.out.println(urlFile);
			
			URL localHost = new URL("http://localhost:8080/"+htmlPagePath);
			
			browser.openURL(localHost);
			
		} catch (Exception ceb) {
			ceb.printStackTrace();
			System.out.println("erro counterexample list abrir pagina web");
		}
		
	}
	
	public void startServer(String directory, String htmlPagePath) {
		if(fileServer == null) {
			FileServer fileServer = new FileServer(directory,htmlPagePath);
			fileServer.startServer();
		}
	}
	
	private String createCounterExamplesFiles(String[] nodes) throws IOException {
		if(projectFilePath == null)
			projectFilePath = CSPDocumentProvider.getEditorFile().getParentFile().getAbsolutePath();
		
		String filePath = createHtmlPage(nodes);
		
		return filePath;
	}

	private String createOutputDirectory() {
		File f = new File(projectFilePath+"/Outputs");
		if(!f.exists()) {
			f.mkdir();
		}
		
		return f.getPath();
	}
	
	private String createHtmlPage(String[] nodes) throws IOException {
		String htmlGraphVar = "var graph = " + cgs.ploting(nodes) + "\n";
		
		File file = new File(projectFilePath+"/Outputs/counterExamplePage.html");
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));
		
		buffWrite.append(getHtmlFirstPart());
		buffWrite.append(htmlGraphVar);
		buffWrite.append(getHtmlSecondPart());
		buffWrite.close();
		
		//return file.toURI().toURL();
		return "counterExamplePage.html";
	}

	public String getHtmlFirstPart() {
		return "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"        .node {}\r\n" + 
				"\r\n" + 
				"        .link { stroke: #999; stroke-opacity: .6; stroke-width: 1px; }\r\n" + 
				"    </style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<svg width=\"960\" height=\"600\"></svg>\r\n" + 
				"\r\n" + 
				"<script src=\"http://d3js.org/d3.v4.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"<script src=\"http://d3js.org/d3-selection-multi.v1.js\"></script>\r\n" + 
				"\r\n" + 
				"<script type=\"text/javascript\"> \n";
	}
	
	public String getHtmlSecondPart() {
		return "\r\n" + 
				"    var svg = d3.select(\"svg\"),\r\n" + 
				"        width = +svg.attr(\"width\"),\r\n" + 
				"        height = +svg.attr(\"height\"),\r\n" + 
				"        node,\r\n" + 
				"        link;\r\n" + 
				"\r\n" + 
				"    svg.append('defs').append('marker')\r\n" + 
				"        .attrs({'id':'arrowhead',\r\n" + 
				"            'viewBox':'-0 -5 10 10',\r\n" + 
				"            'refX':13,\r\n" + 
				"            'refY':0,\r\n" + 
				"            'orient':'auto',\r\n" + 
				"            'markerWidth':13,\r\n" + 
				"            'markerHeight':13,\r\n" + 
				"            'xoverflow':'visible'})\r\n" + 
				"        .append('svg:path')\r\n" + 
				"        .attr('d', 'M 0,-5 L 10 ,0 L 0,5')\r\n" + 
				"        .attr('fill', '#999')\r\n" + 
				"        .style('stroke','none');\r\n" + 
				"\r\n" + 
				"    var simulation = d3.forceSimulation()\r\n" + 
				"        .force(\"link\", d3.forceLink().id(function (d) {return d.id;}).distance(100).strength(1))\r\n" + 
				"        .force(\"charge\", d3.forceManyBody())\r\n" + 
				"        .force(\"center\", d3.forceCenter(width / 2, height / 2));\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    update(graph.links, graph.nodes);\r\n" + 
				"\r\n" + 
				"    function update(links, nodes) {\r\n" + 
				"        link = svg.selectAll(\".link\")\r\n" + 
				"            .data(links)\r\n" + 
				"            .enter()\r\n" + 
				"            .append(\"line\")\r\n" + 
				"            .attr(\"class\", \"link\")\r\n" + 
				"            .attr('marker-end','url(#arrowhead)')\r\n" + 
				"\r\n" + 
				"        link.append(\"title\")\r\n" + 
				"            .text(function (d) {return d.type;});\r\n" + 
				"\r\n" + 
				"        edgepaths = svg.selectAll(\".edgepath\")\r\n" + 
				"            .data(links)\r\n" + 
				"            .enter()\r\n" + 
				"            .append('path')\r\n" + 
				"            .attrs({\r\n" + 
				"                'class': 'edgepath',\r\n" + 
				"                'fill-opacity': 0,\r\n" + 
				"                'stroke-opacity': 0,\r\n" + 
				"                'id': function (d, i) {return 'edgepath' + i}\r\n" + 
				"            })\r\n" + 
				"            .style(\"pointer-events\", \"none\");\r\n" + 
				"\r\n" + 
				"        edgelabels = svg.selectAll(\".edgelabel\")\r\n" + 
				"            .data(links)\r\n" + 
				"            .enter()\r\n" + 
				"            .append('text')\r\n" + 
				"            .style(\"pointer-events\", \"none\")\r\n" + 
				"            .attrs({\r\n" + 
				"                'class': 'edgelabel',\r\n" + 
				"                'id': function (d, i) {return 'edgelabel' + i},\r\n" + 
				"                'font-size': 16,\r\n" + 
				"                'fill': '#aaa'\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        edgelabels.append('textPath')\r\n" + 
				"            .attr('xlink:href', function (d, i) {return '#edgepath' + i})\r\n" + 
				"            .style(\"text-anchor\", \"middle\")\r\n" + 
				"            .style(\"pointer-events\", \"none\")\r\n" + 
				"            .attr(\"startOffset\", \"50%\")\r\n" + 
				"            .text(function (d) {\r\n" + 
				"                var sourceName = d.source.substring(0,(d.source.length -1));\r\n" + 
				"                if (sourceName == \"SKIP\") {\r\n" + 
				"                    return \"tick\";\r\n" + 
				"                } else {\r\n" + 
				"                    return sourceName;\r\n" + 
				"                }\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        node = svg.selectAll(\".node\")\r\n" + 
				"            .data(nodes)\r\n" + 
				"            .enter()\r\n" + 
				"            .append(\"g\")\r\n" + 
				"            .attr(\"class\", \"node\")\r\n" + 
				"            .call(d3.drag()\r\n" + 
				"                    .on(\"start\", dragstarted)\r\n" + 
				"                    .on(\"drag\", dragged)\r\n" + 
				"                    //.on(\"end\", dragended)\r\n" + 
				"            );\r\n" + 
				"\r\n" + 
				"        node.append(\"circle\")\r\n" + 
				"            .attr(\"r\", 5)\r\n" + 
				"            .style(\"fill\", function (d) {\r\n" + 
				"            	if (d.name == \"STOP\") {\r\n" + 
				" 					return \"red\";\r\n" + 
				"            	} else {\r\n" + 
				"            		return \"grey\";\r\n" + 
				"            	}\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        node.append(\"text\")\r\n" + 
				"            .attr(\"dy\", -3)\r\n" + 
				"            .text(function (d) {\r\n" + 
				"                if (d.name == \"STOP\") {\r\n" + 
				"                    return \"STOP\";\r\n" + 
				"                } else if(d.name == \"SKIP\"){\r\n" + 
				"                    return \"SKIP\";\r\n" + 
				"                } else {\r\n" + 
				"                    return \"\";\r\n" + 
				"                }\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        simulation\r\n" + 
				"            .nodes(nodes)\r\n" + 
				"            .on(\"tick\", ticked);\r\n" + 
				"\r\n" + 
				"        simulation.force(\"link\")\r\n" + 
				"            .links(links);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    function ticked() {\r\n" + 
				"        link\r\n" + 
				"            .attr(\"x1\", function (d) {return d.source.x;})\r\n" + 
				"            .attr(\"y1\", function (d) {return d.source.y;})\r\n" + 
				"            .attr(\"x2\", function (d) {return d.target.x;})\r\n" + 
				"            .attr(\"y2\", function (d) {return d.target.y;});\r\n" + 
				"\r\n" + 
				"        node\r\n" + 
				"            .attr(\"transform\", function (d) {return \"translate(\" + d.x + \", \" + d.y + \")\";});\r\n" + 
				"\r\n" + 
				"        edgepaths.attr('d', function (d) {\r\n" + 
				"            return 'M ' + d.source.x + ' ' + d.source.y + ' L ' + d.target.x + ' ' + d.target.y;\r\n" + 
				"        });\r\n" + 
				"\r\n" + 
				"        edgelabels.attr('transform', function (d) {\r\n" + 
				"            if (d.target.x < d.source.x) {\r\n" + 
				"                var bbox = this.getBBox();\r\n" + 
				"\r\n" + 
				"                rx = bbox.x + bbox.width / 2;\r\n" + 
				"                ry = bbox.y + bbox.height / 2;\r\n" + 
				"                return 'rotate(180 ' + rx + ' ' + ry + ')';\r\n" + 
				"            }\r\n" + 
				"            else {\r\n" + 
				"                return 'rotate(0)';\r\n" + 
				"            }\r\n" + 
				"        });\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    function dragstarted(d) {\r\n" + 
				"        if (!d3.event.active) simulation.alphaTarget(0.3).restart()\r\n" + 
				"        d.fx = d.x;\r\n" + 
				"        d.fy = d.y;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    function dragged(d) {\r\n" + 
				"        d.fx = d3.event.x;\r\n" + 
				"        d.fy = d3.event.y;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"</script>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>";
	}
}
