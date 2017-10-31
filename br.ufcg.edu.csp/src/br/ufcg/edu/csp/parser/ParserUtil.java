package br.ufcg.edu.csp.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

import br.ufcg.edu.csp.CSPDocumentProvider;

public class ParserUtil {
	public static Parser parse(File file) {
		ANTLRInputStream reader = null;
		CspParser parser = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			reader = new ANTLRInputStream((InputStream)fis);
			return getParserFromReader(reader);

		} catch (IOException e) {
			e.printStackTrace();
		}// lembrar de dar cast na classe que receber para expressionParser
		return parser;
	}
	
	public static void parse(File[] files) {
		for(File file: files) {
			parse(file);
		}
	}
	
	public static Parser getParserFromText(String text) {
		ANTLRInputStream reader = new ANTLRInputStream(text);
		
		return getParserFromReader(reader);
	}
	
	private static Parser getParserFromReader(ANTLRInputStream reader) {
		CspLexer lexer  = new CspLexer((CharStream)reader);
		TokenStream tokens = new CommonTokenStream(lexer);
		CspParser parser = new CspParser(tokens);
		parser.setErrorHandler(new ExceptionErrorStrategy());
		
		// apagar
		//Token t1 = tokens.LT(4);
		//Token t2 = tokens.LT(5);
		//t2.getStopIndex();
		//Token t1 = ((BufferedTokenStream) tokens).get(4);
		//reader.
		
		
		return parser;
	}
	
	public static CspParser.SpecContext getRootFromTextEditor() {
		CspParser parser = (CspParser) ParserUtil.getParserFromText(CSPDocumentProvider.getTextFromEditor());
		CspParser.SpecContext tree = parser.spec();
		
		return tree;
	}
	
	public static CspParser.SimpleDefinitionContext getProcessNodeAt(int docOffset, ParserRuleContext startNode, IDocument doc, NodeAssist node) {
		
		try {
			int lineNum = doc.getLineOfOffset(docOffset);
			int lineBeingOffset = doc.getLineOffset(lineNum);
			int offsetInLine = docOffset - lineBeingOffset;
			
			String lineText = getLineText(doc, docOffset);
//			String processName = lineText.split;
			//TODO achar uma forma de encontrar o No e Texto
			//int last = startNode.getStart().getStartIndex();
			
		} catch (BadLocationException e) {
			return null;
		}
		
		
		
		return null;
	}
	
	private static String getLineText (IDocument doc, int docOffset) {
		try {
			IRegion lineRegion = doc.getLineInformationOfOffset(docOffset);
			return doc.get(lineRegion.getOffset(), lineRegion.getLength());
		} catch (Exception e) {
			return null;
		}
	}

}
