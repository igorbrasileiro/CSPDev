// Generated from Csp.g4 by ANTLR 4.4
package br.ufcg.edu.csp.parser;
import org.antlr.v4.runtime.misc.NotNull;
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
	 * Visit a parse tree produced by {@link CspParser#channelDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelDecl(@NotNull CspParser.ChannelDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#proc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc(@NotNull CspParser.ProcContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(@NotNull CspParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#definitionLeft}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitionLeft(@NotNull CspParser.DefinitionLeftContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#boolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(@NotNull CspParser.BoolExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#simple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple(@NotNull CspParser.SimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#simpleDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDefinition(@NotNull CspParser.SimpleDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#boolOrAmb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOrAmb(@NotNull CspParser.BoolOrAmbContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#channelColonType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelColonType(@NotNull CspParser.ChannelColonTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull CspParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#any}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny(@NotNull CspParser.AnyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpec(@NotNull CspParser.SpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull CspParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#channelNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelNames(@NotNull CspParser.ChannelNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#defnCallLeft}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefnCallLeft(@NotNull CspParser.DefnCallLeftContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(@NotNull CspParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull CspParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CspParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(@NotNull CspParser.CommentContext ctx);
}