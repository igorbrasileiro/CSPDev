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
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.parser.CspParser.DefinitionLeftContext;

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
		String editorContent = CSPDocumentProvider.getTextFromEditor();
		// TODO: Editor nulo retornar sem documento encontrado
		CspParser parser = (CspParser) ParserUtil.getParserFromText(editorContent);
		CspParser.SpecContext tree = parser.spec();

		return tree;
	}

	public static CspParser.DefinitionLeftContext getProcessNodeAt(int docOffset, ParserRuleContext startNode, IDocument doc) {

		try {
			int lineNum = doc.getLineOfOffset(docOffset);
			int lineBeingOffset = doc.getLineOffset(lineNum);
			int offsetInLine = docOffset - lineBeingOffset;

			String lineText = getLineText(doc, docOffset);
			//			String processName = lineText.split;
			// descer até definitionleft para pegar somente a definição do processo
			while(offsetInLine >= 0 && Character.isWhitespace(lineText.charAt(offsetInLine))) {
					offsetInLine--;
			}
			
			int realOffset = offsetInLine + lineBeingOffset;
			
			CspParser.DefinitionLeftContext processFound = findProcess(startNode, realOffset, lineNum);
			
			return processFound;

		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Este método retorna o nó sintático, que compreende a palavra na linha e coluna. Caso exista, retorna o nó. 
	 * Caso não exista retorna null;
	 * @param startNode
	 * @param offset
	 * @param lineNum
	 * @return
	 */
	private static CspParser.DefinitionLeftContext findProcess(ParserRuleContext startNode, int offset, int lineNum) {
		if(startNode instanceof CspParser.SpecContext){
			// regra de muitos filhos
			int children = ((ParseTree) startNode).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree node = ((ParseTree) startNode).getChild(i);
				ParseTree nodeFound = findProcess((ParserRuleContext) node, offset, lineNum);
				if(nodeFound != null && nodeFound instanceof CspParser.DefinitionLeftContext) {
					return (CspParser.DefinitionLeftContext) nodeFound;
				}
			}
		} else if(startNode instanceof CspParser.DefinitionContext) {
			// regra de filho unico
			ParseTree node = ((ParseTree) startNode).getChild(0);
			return findProcess((ParserRuleContext) node, offset, lineNum);
		} else if(startNode instanceof CspParser.SimpleDefinitionContext) {
			ParseTree node = ((ParseTree) startNode).getChild(0);
			return findProcess((ParserRuleContext) node, offset, lineNum);
		} else if(startNode instanceof CspParser.DefinitionLeftContext) {
			// linenum+1 because editor start from 0, while parser start from 1
			if(startNode.getStart().getLine() == lineNum+1
					&& startNode.getStart().getStartIndex() <= offset 
					&& startNode.getStop().getStopIndex()>= offset) {
				return (DefinitionLeftContext) startNode;
			}
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
