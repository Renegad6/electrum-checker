// Generated from org/electrumchecker/ocl2electrum/OclInvariant.g4 by ANTLR 4.13.2
package org.electrumchecker.ocl2electrum;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OclInvariantParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OclInvariantVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(OclInvariantParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#invariantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvariantDeclaration(OclInvariantParser.InvariantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(OclInvariantParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#implicationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicationExpression(OclInvariantParser.ImplicationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#orExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(OclInvariantParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#xorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorExpression(OclInvariantParser.XorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(OclInvariantParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(OclInvariantParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(OclInvariantParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(OclInvariantParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OclInvariantParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(OclInvariantParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOperation}
	 * labeled alternative in {@link OclInvariantParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperation(OclInvariantParser.UnaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryOperation}
	 * labeled alternative in {@link OclInvariantParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryOperation(OclInvariantParser.PrimaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selfNavigation}
	 * labeled alternative in {@link OclInvariantParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfNavigation(OclInvariantParser.SelfNavigationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nameOrNavigation}
	 * labeled alternative in {@link OclInvariantParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameOrNavigation(OclInvariantParser.NameOrNavigationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link OclInvariantParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(OclInvariantParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueLiteral}
	 * labeled alternative in {@link OclInvariantParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueLiteral(OclInvariantParser.TrueLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseLiteral}
	 * labeled alternative in {@link OclInvariantParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseLiteral(OclInvariantParser.FalseLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link OclInvariantParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(OclInvariantParser.ParenthesizedExpressionContext ctx);
}