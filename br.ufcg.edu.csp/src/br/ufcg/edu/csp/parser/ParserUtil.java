package br.ufcg.edu.csp.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

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
		//parser.setErrorHandler(new ExceptionErrorStrategy());
		//TODO : ExceptionErrorStrategy
		
		return parser;
	}

}
