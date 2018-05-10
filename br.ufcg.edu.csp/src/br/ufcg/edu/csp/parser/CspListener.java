package br.ufcg.edu.csp.parser;

// Generated from Csp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CspParser}.
 */
public interface CspListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CspParser#spec}.
	 * @param ctx the parse tree
	 */
	void enterSpec(CspParser.SpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#spec}.
	 * @param ctx the parse tree
	 */
	void exitSpec(CspParser.SpecContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(CspParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(CspParser.DefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#channelDecl}.
	 * @param ctx the parse tree
	 */
	void enterChannelDecl(CspParser.ChannelDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#channelDecl}.
	 * @param ctx the parse tree
	 */
	void exitChannelDecl(CspParser.ChannelDeclContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#channelNames}.
	 * @param ctx the parse tree
	 */
	void enterChannelNames(CspParser.ChannelNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#channelNames}.
	 * @param ctx the parse tree
	 */
	void exitChannelNames(CspParser.ChannelNamesContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#channelColonType}.
	 * @param ctx the parse tree
	 */
	void enterChannelColonType(CspParser.ChannelColonTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#channelColonType}.
	 * @param ctx the parse tree
	 */
	void exitChannelColonType(CspParser.ChannelColonTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#simpleDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDefinition(CspParser.SimpleDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#simpleDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDefinition(CspParser.SimpleDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#assertDefinition}.
	 * @param ctx the parse tree
	 */
	void enterAssertDefinition(CspParser.AssertDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#assertDefinition}.
	 * @param ctx the parse tree
	 */
	void exitAssertDefinition(CspParser.AssertDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#definitionLeft}.
	 * @param ctx the parse tree
	 */
	void enterDefinitionLeft(CspParser.DefinitionLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#definitionLeft}.
	 * @param ctx the parse tree
	 */
	void exitDefinitionLeft(CspParser.DefinitionLeftContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#defnCallLeft}.
	 * @param ctx the parse tree
	 */
	void enterDefnCallLeft(CspParser.DefnCallLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#defnCallLeft}.
	 * @param ctx the parse tree
	 */
	void exitDefnCallLeft(CspParser.DefnCallLeftContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#any}.
	 * @param ctx the parse tree
	 */
	void enterAny(CspParser.AnyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#any}.
	 * @param ctx the parse tree
	 */
	void exitAny(CspParser.AnyContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#checkConditionBody}.
	 * @param ctx the parse tree
	 */
	void enterCheckConditionBody(CspParser.CheckConditionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#checkConditionBody}.
	 * @param ctx the parse tree
	 */
	void exitCheckConditionBody(CspParser.CheckConditionBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#modelCheckType}.
	 * @param ctx the parse tree
	 */
	void enterModelCheckType(CspParser.ModelCheckTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#modelCheckType}.
	 * @param ctx the parse tree
	 */
	void exitModelCheckType(CspParser.ModelCheckTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CspParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CspParser.TypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#simple}.
	 * @param ctx the parse tree
	 */
	void enterSimple(CspParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#simple}.
	 * @param ctx the parse tree
	 */
	void exitSimple(CspParser.SimpleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(CspParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(CspParser.SetContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#proc}.
	 * @param ctx the parse tree
	 */
	void enterProc(CspParser.ProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#proc}.
	 * @param ctx the parse tree
	 */
	void exitProc(CspParser.ProcContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#boolExp}.
	 * @param ctx the parse tree
	 */
	void enterBoolExp(CspParser.BoolExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#boolExp}.
	 * @param ctx the parse tree
	 */
	void exitBoolExp(CspParser.BoolExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#boolOrAmb}.
	 * @param ctx the parse tree
	 */
	void enterBoolOrAmb(CspParser.BoolOrAmbContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#boolOrAmb}.
	 * @param ctx the parse tree
	 */
	void exitBoolOrAmb(CspParser.BoolOrAmbContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CspParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CspParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(CspParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(CspParser.NumberContext ctx);

	/**
	 * Enter a parse tree produced by {@link CspParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(CspParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CspParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(CspParser.CommentContext ctx);
}