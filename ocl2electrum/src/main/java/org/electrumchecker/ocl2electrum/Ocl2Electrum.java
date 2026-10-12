package org.electrumchecker.ocl2electrum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.TerminalNode;

public final class Ocl2Electrum {
    private Ocl2Electrum() {
    }

    public static void main(String[] arguments) throws IOException {
        try {
            run(arguments);
        } catch (IllegalArgumentException exception) {
            System.err.println("ocl2electrum: " + exception.getMessage());
            System.exit(2);
        }
    }

    private static void run(String[] arguments) throws IOException {
        if (arguments.length == 0 || arguments.length % 2 != 0) {
            throw new IllegalArgumentException(usage());
        }

        String modelPath = null;
        String umlPath = null;
        String oclPath = null;
        String outputPath = null;
        int steps = 5;

        for (int index = 0; index < arguments.length; index += 2) {
            String option = arguments[index];
            String value = arguments[index + 1];
            if ("--model".equals(option)) {
                modelPath = value;
            } else if ("--uml".equals(option)) {
                umlPath = value;
            } else if ("--ocl".equals(option)) {
                oclPath = value;
            } else if ("--out".equals(option)) {
                outputPath = value;
            } else if ("--steps".equals(option)) {
                try {
                    steps = Integer.parseInt(value);
                } catch (NumberFormatException exception) {
                    throw new IllegalArgumentException("--steps debe ser un entero positivo");
                }
                if (steps < 1) {
                    throw new IllegalArgumentException("--steps debe ser un entero positivo");
                }
            } else {
                throw new IllegalArgumentException("opción desconocida: " + option + "\n" + usage());
            }
        }

        if ((modelPath == null) == (umlPath == null) || oclPath == null || outputPath == null) {
            throw new IllegalArgumentException(usage());
        }

        String ocl = readFile(oclPath);
        OclInvariantLexer lexer = new OclInvariantLexer(CharStreams.fromString(ocl));
        OclInvariantParser parser = new OclInvariantParser(new CommonTokenStream(lexer));
        ThrowingErrorListener errors = new ThrowingErrorListener();
        lexer.removeErrorListeners();
        lexer.addErrorListener(errors);
        parser.removeErrorListeners();
        parser.addErrorListener(errors);

        OclInvariantParser.DocumentContext document = parser.document();
        String context = document.ID().getText();
        String model = umlPath == null ? readFile(modelPath) : UmlModelGenerator.generate(umlPath, context);
        ExpressionGenerator generator = new ExpressionGenerator();
        Set<String> generatedNames = new HashSet<String>();
        StringBuilder assertions = new StringBuilder();

        for (int index = 0; index < document.invariantDeclaration().size(); index++) {
            OclInvariantParser.InvariantDeclarationContext invariant = document.invariantDeclaration(index);
            String invariantName = invariant.ID() == null ? "Invariant" + (index + 1) : invariant.ID().getText();
            String generatedName = "OCL_" + sanitize(invariantName);
            if (!generatedNames.add(generatedName)) {
                throw new IllegalArgumentException("nombre de invariante duplicado: " + invariantName);
            }

            String expression = generator.visit(invariant.expression());
            assertions.append("\nassert ").append(generatedName).append(" {\n")
                    .append("    always all self: ").append(context).append(" | ")
                    .append(expression).append("\n")
                    .append("}\n")
                    .append("check ").append(generatedName).append(" for ").append(steps).append(" steps\n");
        }

        Files.write(Paths.get(outputPath), (model + "\n" + assertions.toString()).getBytes(StandardCharsets.UTF_8));
        System.out.println("Generado: " + outputPath);
        System.out.println("Contexto: " + context + ", invariantes: " + generatedNames.size());
    }

    private static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }

    private static String sanitize(String name) {
        String sanitized = name.replaceAll("[^A-Za-z0-9_]", "_");
        if (sanitized.isEmpty() || (!Character.isLetter(sanitized.charAt(0)) && sanitized.charAt(0) != '_')) {
            sanitized = "_" + sanitized;
        }
        return sanitized;
    }

    private static String usage() {
        return "uso: ocl2electrum (--model modelo.ele | --uml modelo.uml) "
            + "--ocl invariantes.ocl --out generado.ele [--steps N]";
    }

    private static final class ExpressionGenerator extends OclInvariantBaseVisitor<String> {
        @Override
        public String visitExpression(OclInvariantParser.ExpressionContext context) {
            return visit(context.implicationExpression());
        }

        @Override
        public String visitImplicationExpression(OclInvariantParser.ImplicationExpressionContext context) {
            if (context.IMPLIES() == null) {
                return visit(context.orExpression());
            }
            return "(" + visit(context.orExpression()) + " implies " + visit(context.implicationExpression()) + ")";
        }

        @Override
        public String visitOrExpression(OclInvariantParser.OrExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitXorExpression(OclInvariantParser.XorExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitAndExpression(OclInvariantParser.AndExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitEqualityExpression(OclInvariantParser.EqualityExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitRelationalExpression(OclInvariantParser.RelationalExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitAdditiveExpression(OclInvariantParser.AdditiveExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitMultiplicativeExpression(OclInvariantParser.MultiplicativeExpressionContext context) {
            return visitChain(context);
        }

        @Override
        public String visitUnaryOperation(OclInvariantParser.UnaryOperationContext context) {
            String operator = context.getChild(0).getText();
            String operand = visit(context.unaryExpression());
            return "not".equals(operator) ? "not (" + operand + ")" : "(-" + operand + ")";
        }

        @Override
        public String visitPrimaryOperation(OclInvariantParser.PrimaryOperationContext context) {
            return visit(context.primaryExpression());
        }

        @Override
        public String visitSelfNavigation(OclInvariantParser.SelfNavigationContext context) {
            StringBuilder result = new StringBuilder("self");
            for (TerminalNode identifier : context.ID()) {
                result.append('.').append(identifier.getText());
            }
            return result.toString();
        }

        @Override
        public String visitNameOrNavigation(OclInvariantParser.NameOrNavigationContext context) {
            StringBuilder result = new StringBuilder();
            for (TerminalNode identifier : context.ID()) {
                if (result.length() > 0) {
                    result.append('.');
                }
                result.append(identifier.getText());
            }
            return result.toString();
        }

        @Override
        public String visitIntegerLiteral(OclInvariantParser.IntegerLiteralContext context) {
            return context.INTEGER().getText();
        }

        @Override
        public String visitTrueLiteral(OclInvariantParser.TrueLiteralContext context) {
            return "true";
        }

        @Override
        public String visitFalseLiteral(OclInvariantParser.FalseLiteralContext context) {
            return "false";
        }

        @Override
        public String visitParenthesizedExpression(OclInvariantParser.ParenthesizedExpressionContext context) {
            return "(" + visit(context.expression()) + ")";
        }

        private String visitChain(ParserRuleContext context) {
            StringBuilder result = new StringBuilder(visit(context.getChild(0)));
            for (int index = 1; index < context.getChildCount(); index += 2) {
                String operator = context.getChild(index).getText();
                if ("xor".equals(operator)) {
                    operator = "!=";
                } else if ("div".equals(operator)) {
                    operator = "/";
                } else if ("mod".equals(operator)) {
                    operator = "%";
                } else if ("<>".equals(operator)) {
                    operator = "!=";
                }
                result.append(' ').append(operator).append(' ').append(visit(context.getChild(index + 1)));
            }
            return "(" + result + ")";
        }
    }

    private static final class ThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                int charPositionInLine, String message, RecognitionException exception) {
            throw new IllegalArgumentException("error OCL en línea " + line + ", columna "
                    + charPositionInLine + ": " + message);
        }
    }
}