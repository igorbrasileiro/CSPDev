package br.ufcg.edu.csp.parser;

// Generated from Csp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CspParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CspVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CspParser#spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpec(CspParser.SpecContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(CspParser.DefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#channelDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelDecl(CspParser.ChannelDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#channelNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelNames(CspParser.ChannelNamesContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#channelColonType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelColonType(CspParser.ChannelColonTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#simpleDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDefinition(CspParser.SimpleDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#assertDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertDefinition(CspParser.AssertDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#definitionLeft}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitionLeft(CspParser.DefinitionLeftContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#defnCallLeft}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefnCallLeft(CspParser.DefnCallLeftContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#any}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny(CspParser.AnyContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#checkConditionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckConditionBody(CspParser.CheckConditionBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#modelCheckType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModelCheckType(CspParser.ModelCheckTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(CspParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#simple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple(CspParser.SimpleContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(CspParser.SetContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#proc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc(CspParser.ProcContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#boolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(CspParser.BoolExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#boolOrAmb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOrAmb(CspParser.BoolOrAmbContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CspParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(CspParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link CspParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(CspParser.CommentContext ctx);
}