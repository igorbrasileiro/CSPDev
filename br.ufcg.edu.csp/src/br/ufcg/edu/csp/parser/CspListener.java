// Generated from Csp.g4 by ANTLR 4.4
package br.ufcg.edu.csp.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CspParser}.
 */
public interface CspListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CspParser#channelDecl}.
	 * @param ctx the parse tree
	 */
	void enterChannelDecl(@NotNull CspParser.ChannelDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#channelDecl}.
	 * @param ctx the parse tree
	 */
	void exitChannelDecl(@NotNull CspParser.ChannelDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#proc}.
	 * @param ctx the parse tree
	 */
	void enterProc(@NotNull CspParser.ProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#proc}.
	 * @param ctx the parse tree
	 */
	void exitProc(@NotNull CspParser.ProcContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(@NotNull CspParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(@NotNull CspParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#definitionLeft}.
	 * @param ctx the parse tree
	 */
	void enterDefinitionLeft(@NotNull CspParser.DefinitionLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#definitionLeft}.
	 * @param ctx the parse tree
	 */
	void exitDefinitionLeft(@NotNull CspParser.DefinitionLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#boolExp}.
	 * @param ctx the parse tree
	 */
	void enterBoolExp(@NotNull CspParser.BoolExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#boolExp}.
	 * @param ctx the parse tree
	 */
	void exitBoolExp(@NotNull CspParser.BoolExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#simple}.
	 * @param ctx the parse tree
	 */
	void enterSimple(@NotNull CspParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#simple}.
	 * @param ctx the parse tree
	 */
	void exitSimple(@NotNull CspParser.SimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#simpleDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDefinition(@NotNull CspParser.SimpleDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#simpleDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDefinition(@NotNull CspParser.SimpleDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#boolOrAmb}.
	 * @param ctx the parse tree
	 */
	void enterBoolOrAmb(@NotNull CspParser.BoolOrAmbContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#boolOrAmb}.
	 * @param ctx the parse tree
	 */
	void exitBoolOrAmb(@NotNull CspParser.BoolOrAmbContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#channelColonType}.
	 * @param ctx the parse tree
	 */
	void enterChannelColonType(@NotNull CspParser.ChannelColonTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#channelColonType}.
	 * @param ctx the parse tree
	 */
	void exitChannelColonType(@NotNull CspParser.ChannelColonTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull CspParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull CspParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#any}.
	 * @param ctx the parse tree
	 */
	void enterAny(@NotNull CspParser.AnyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#any}.
	 * @param ctx the parse tree
	 */
	void exitAny(@NotNull CspParser.AnyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#spec}.
	 * @param ctx the parse tree
	 */
	void enterSpec(@NotNull CspParser.SpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#spec}.
	 * @param ctx the parse tree
	 */
	void exitSpec(@NotNull CspParser.SpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull CspParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull CspParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#channelNames}.
	 * @param ctx the parse tree
	 */
	void enterChannelNames(@NotNull CspParser.ChannelNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#channelNames}.
	 * @param ctx the parse tree
	 */
	void exitChannelNames(@NotNull CspParser.ChannelNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#defnCallLeft}.
	 * @param ctx the parse tree
	 */
	void enterDefnCallLeft(@NotNull CspParser.DefnCallLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#defnCallLeft}.
	 * @param ctx the parse tree
	 */
	void exitDefnCallLeft(@NotNull CspParser.DefnCallLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(@NotNull CspParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(@NotNull CspParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull CspParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull CspParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CspParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(@NotNull CspParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(@NotNull CspParser.CommentContext ctx);
}