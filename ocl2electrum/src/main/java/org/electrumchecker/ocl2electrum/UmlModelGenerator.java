package org.electrumchecker.ocl2electrum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

final class UmlModelGenerator {
    private static final String XMI_NAMESPACE = "http://www.omg.org/spec/XMI/20131001";

    private UmlModelGenerator() {
    }

    static String generate(String path, String oclContext) throws IOException {
        Document document = parse(path);
        List<Element> modelElements = findModelElements(document);
        Map<String, Element> classifiersById = new HashMap<String, Element>();
        Map<String, Element> classifiersByName = new HashMap<String, Element>();

        for (Element element : modelElements) {
            String kind = umlKind(element);
            if (isClassifier(kind)) {
                String name = element.getAttribute("name");
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("hay un clasificador UML sin nombre");
                }
                String identifier = xmiId(element);
                if (!identifier.isEmpty()) {
                    classifiersById.put(identifier, element);
                }
                classifiersByName.put(name, element);
            }
        }

        if (!classifiersByName.containsKey(oclContext)) {
            throw new IllegalArgumentException("el contexto OCL '" + oclContext
                    + "' no existe como clase o enumeración en " + path);
        }

        StringBuilder output = new StringBuilder("module uml_generated\n\n");
        Set<String> generatedNames = new HashSet<String>();
        for (Element classifier : modelElements) {
            String kind = umlKind(classifier);
            if (!isClassifier(kind)) {
                continue;
            }
            String name = identifier(classifier.getAttribute("name"));
            if (!generatedNames.add(name)) {
                throw new IllegalArgumentException("nombre UML duplicado o no válido: " + name);
            }
            if ("Enumeration".equals(kind)) {
                appendEnumeration(output, classifier, name);
            } else if ("Class".equals(kind)) {
                appendClass(output, classifier, name, classifiersById, classifiersByName);
            } else if ("DataType".equals(kind)) {
                throw new IllegalArgumentException("UML DataType no soportado: " + name
                        + "; usa una enumeración o un tipo primitivo compatible");
            }
        }
        return output.toString();
    }

    private static Document parse(String path) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            return factory.newDocumentBuilder().parse(new File(path));
        } catch (ParserConfigurationException | SAXException exception) {
            throw new IllegalArgumentException("no se pudo leer el UML XMI '" + path + "': "
                    + exception.getMessage(), exception);
        }
    }

    private static List<Element> findModelElements(Document document) {
        List<Element> result = new ArrayList<Element>();
        NodeList all = document.getElementsByTagNameNS("*", "*");
        for (int index = 0; index < all.getLength(); index++) {
            Node node = all.item(index);
            if (node instanceof Element) {
                Element element = (Element) node;
                String kind = umlKind(element);
                if (isClassifier(kind)) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    private static boolean isClassifier(String kind) {
        return "Class".equals(kind) || "Enumeration".equals(kind) || "DataType".equals(kind);
    }

    private static String umlKind(Element element) {
        String type = element.getAttributeNS(XMI_NAMESPACE, "type");
        if (type.isEmpty()) {
            type = element.getAttribute("xmi:type");
        }
        if (type.isEmpty()) {
            return "";
        }
        int separator = type.indexOf(':');
        return separator < 0 ? type : type.substring(separator + 1);
    }

    private static String xmiId(Element element) {
        String id = element.getAttributeNS(XMI_NAMESPACE, "id");
        return id.isEmpty() ? element.getAttribute("xmi:id") : id;
    }

    private static void appendEnumeration(StringBuilder output, Element enumeration, String name) {
        List<String> literals = new ArrayList<String>();
        for (Element child : children(enumeration)) {
            if ("ownedLiteral".equals(localName(child))) {
                literals.add(identifier(child.getAttribute("name")));
            }
        }
        if (literals.isEmpty()) {
            throw new IllegalArgumentException("la enumeración UML '" + name + "' no tiene literales");
        }
        output.append("abstract sig ").append(name).append(" {}\n")
                .append("one sig ").append(join(literals)).append(" extends ").append(name).append(" {}\n\n");
    }

    private static void appendClass(StringBuilder output, Element umlClass, String name,
            Map<String, Element> classifiersById, Map<String, Element> classifiersByName) {
        List<String> fields = new ArrayList<String>();
        for (Element child : children(umlClass)) {
            if (!"ownedAttribute".equals(localName(child))) {
                continue;
            }
            String fieldName = identifier(child.getAttribute("name"));
            String typeReference = child.getAttribute("type");
            if (fieldName.isEmpty() || typeReference.isEmpty()) {
                throw new IllegalArgumentException("cada ownedAttribute de '" + name
                        + "' debe tener name y type");
            }
            String target = resolveType(typeReference, classifiersById, classifiersByName);
            String multiplicity = multiplicity(child);
            fields.add(fieldName + ": " + multiplicity + target);
        }

        String modifier = "true".equals(umlClass.getAttribute("isAbstract")) ? "abstract sig " : "sig ";
        output.append(modifier).append(name).append(" {");
        if (!fields.isEmpty()) {
            output.append("\n    ").append(join(fields, ",\n    ")).append("\n");
        }
        output.append("}\n\n");
    }

    private static String resolveType(String reference, Map<String, Element> classifiersById,
            Map<String, Element> classifiersByName) {
        String normalized = reference.startsWith("#") ? reference.substring(1) : reference;
        Element classifier = classifiersById.get(normalized);
        if (classifier == null) {
            classifier = classifiersByName.get(normalized);
        }
        if (classifier != null) {
            return identifier(classifier.getAttribute("name"));
        }
        if ("Integer".equals(normalized) || "UnlimitedNatural".equals(normalized)) {
            return "Int";
        }
        if ("String".equals(normalized)) {
            return "String";
        }
        throw new IllegalArgumentException("tipo UML no resuelto o no soportado: " + reference);
    }

    private static String multiplicity(Element property) {
        String lower = bound(property, "lower", "lowerValue", "1");
        String upper = bound(property, "upper", "upperValue", "1");
        if ("1".equals(lower) && "1".equals(upper)) {
            return "one ";
        }
        if ("0".equals(lower) && "1".equals(upper)) {
            return "lone ";
        }
        if ("0".equals(lower) && ("*".equals(upper) || "-1".equals(upper))) {
            return "set ";
        }
        if ("1".equals(lower) && ("*".equals(upper) || "-1".equals(upper))) {
            return "some ";
        }
        throw new IllegalArgumentException("multiplicidad no soportada: " + lower + ".." + upper
                + " (se admiten 1, 0..1, 0..* y 1..*)");
    }

    private static String bound(Element property, String attribute, String childName, String defaultValue) {
        String value = property.getAttribute(attribute);
        if (!value.isEmpty()) {
            return value;
        }
        for (Element child : children(property)) {
            if (childName.equals(localName(child))) {
                String childValue = child.getAttribute("value");
                if (!childValue.isEmpty()) {
                    return childValue;
                }
            }
        }
        return defaultValue;
    }

    private static List<Element> children(Element parent) {
        List<Element> result = new ArrayList<Element>();
        NodeList nodes = parent.getChildNodes();
        for (int index = 0; index < nodes.getLength(); index++) {
            Node node = nodes.item(index);
            if (node instanceof Element) {
                result.add((Element) node);
            }
        }
        return result;
    }

    private static String localName(Element element) {
        String name = element.getLocalName();
        if (name != null) {
            return name;
        }
        String qualifiedName = element.getTagName();
        int separator = qualifiedName.indexOf(':');
        return separator < 0 ? qualifiedName : qualifiedName.substring(separator + 1);
    }

    private static String identifier(String name) {
        String sanitized = name.replaceAll("[^A-Za-z0-9_]", "_");
        if (sanitized.isEmpty() || (!Character.isLetter(sanitized.charAt(0)) && sanitized.charAt(0) != '_')) {
            throw new IllegalArgumentException("nombre UML vacío o incompatible con Electrum: " + name);
        }
        return sanitized;
    }

    private static String join(List<String> values) {
        return join(values, ", ");
    }

    private static String join(List<String> values, String separator) {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < values.size(); index++) {
            if (index > 0) {
                result.append(separator);
            }
            result.append(values.get(index));
        }
        return result.toString();
    }
}