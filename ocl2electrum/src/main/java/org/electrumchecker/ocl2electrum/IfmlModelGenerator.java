package org.electrumchecker.ocl2electrum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

final class IfmlModelGenerator {
    private static final String XMI_NAMESPACE = "http://www.omg.org/spec/XMI/20131001";
    private static final Set<String> NODE_KINDS = new HashSet<String>(Arrays.asList(
            "ViewContainer", "ViewComponent", "Action", "Event", "Parameter",
            "ViewComponentPart", "DomainConcept"));
    private static final Set<String> FLOW_KINDS = new HashSet<String>(Arrays.asList(
            "InteractionFlow", "NavigationFlow", "DataFlow"));
    private static final Set<String> SUPPORTED_KINDS = new HashSet<String>();

    static {
        SUPPORTED_KINDS.addAll(NODE_KINDS);
        SUPPORTED_KINDS.addAll(FLOW_KINDS);
    }

    private IfmlModelGenerator() {
    }

    static String generate(String path, String oclContext) throws IOException {
        Document document = parse(path);
        List<ModelElement> elements = findModelElements(document);
        Map<String, ModelElement> byId = new HashMap<String, ModelElement>();

        for (ModelElement element : elements) {
            if (element.id.isEmpty()) {
                throw new IllegalArgumentException("el elemento IFML '" + element.name
                        + "' no tiene xmi:id en " + path);
            }
            if (byId.put(element.id, element) != null) {
                throw new IllegalArgumentException("xmi:id IFML duplicado: " + element.id);
            }
        }

        if (!SUPPORTED_KINDS.contains(oclContext)) {
            throw new IllegalArgumentException("el contexto OCL '" + oclContext
                    + "' no existe como tipo IFML compatible en " + path);
        }

        StringBuilder output = new StringBuilder("module ifml_generated\n\n");
        output.append("abstract sig IFMLNode {}\n");
        for (String kind : orderedKinds()) {
            if (FLOW_KINDS.contains(kind)) {
                if ("InteractionFlow".equals(kind)) {
                    output.append("abstract sig InteractionFlow extends IFMLNode {\n")
                            .append("    source: one IFMLNode,\n")
                            .append("    target: one IFMLNode\n")
                            .append("}\n\n");
                } else {
                    output.append("abstract sig ").append(kind).append(" extends InteractionFlow {}\n\n");
                }
            } else if (NODE_KINDS.contains(kind)) {
                output.append("abstract sig ").append(kind).append(" extends IFMLNode {}\n");
            }
        }
        output.append("\n");

        for (ModelElement element : elements) {
            output.append("one sig ").append(element.identifier)
                    .append(" extends ").append(element.kind).append(" {}\n");
        }

        List<String> flowClauses = new ArrayList<String>();
        for (ModelElement flow : elements) {
            if (!FLOW_KINDS.contains(flow.kind)) {
                continue;
            }
            String sourceReference = flowReference(flow.element, "source", "sourceElement", "from");
            String targetReference = flowReference(flow.element, "target", "targetElement", "to");
            ModelElement source = resolveReference(sourceReference, flow, byId, "origen");
            ModelElement target = resolveReference(targetReference, flow, byId, "destino");
            flowClauses.add("(" + flow.identifier + ".source = " + source.identifier + " and "
                    + flow.identifier + ".target = " + target.identifier + ")");
        }
        if (!flowClauses.isEmpty()) {
            output.append("\nfact ifml_flow_endpoints {\n    ")
                    .append(join(flowClauses, "\n    and "))
                    .append("\n}\n");
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
            throw new IllegalArgumentException("no se pudo leer el IFML XMI '" + path + "': "
                    + exception.getMessage(), exception);
        }
    }

    private static List<ModelElement> findModelElements(Document document) {
        List<ModelElement> result = new ArrayList<ModelElement>();
        NodeList all = document.getElementsByTagNameNS("*", "*");
        for (int index = 0; index < all.getLength(); index++) {
            Node node = all.item(index);
            if (!(node instanceof Element)) {
                continue;
            }
            Element element = (Element) node;
            String kind = kind(element);
            if (SUPPORTED_KINDS.contains(kind)) {
                result.add(new ModelElement(kind, xmiId(element), element.getAttribute("name"), element));
            }
        }
        if (result.isEmpty()) {
            throw new IllegalArgumentException("no se encontraron elementos IFML compatibles en el XMI");
        }
        return result;
    }

    private static String kind(Element element) {
        String type = element.getAttributeNS(XMI_NAMESPACE, "type");
        if (type.isEmpty()) {
            type = element.getAttribute("xmi:type");
        }
        if (type.isEmpty()) {
            type = localName(element);
        }
        int separator = type.indexOf(':');
        return separator < 0 ? type : type.substring(separator + 1);
    }

    private static String xmiId(Element element) {
        String id = element.getAttributeNS(XMI_NAMESPACE, "id");
        return id.isEmpty() ? element.getAttribute("xmi:id") : id;
    }

    private static String flowReference(Element flow, String... candidates) {
        for (String candidate : candidates) {
            String reference = flow.getAttribute(candidate);
            if (!reference.isEmpty()) {
                return reference;
            }
        }
        NodeList descendants = flow.getElementsByTagNameNS("*", "*");
        for (int index = 0; index < descendants.getLength(); index++) {
            Node node = descendants.item(index);
            if (!(node instanceof Element)) {
                continue;
            }
            Element child = (Element) node;
            for (String candidate : candidates) {
                if (candidate.equals(localName(child))) {
                    String href = child.getAttribute("href");
                    if (!href.isEmpty()) {
                        return href;
                    }
                    String id = xmiId(child);
                    if (!id.isEmpty()) {
                        return id;
                    }
                }
            }
        }
        return "";
    }

    private static ModelElement resolveReference(String reference, ModelElement flow,
            Map<String, ModelElement> byId, String endpoint) {
        if (reference.isEmpty()) {
            throw new IllegalArgumentException("el flujo IFML '" + flow.name + "' no tiene "
                    + endpoint + " definido");
        }
        String normalized = reference;
        int fragment = normalized.lastIndexOf('#');
        if (fragment >= 0) {
            normalized = normalized.substring(fragment + 1);
        }
        if (normalized.startsWith("#")) {
            normalized = normalized.substring(1);
        }
        ModelElement resolved = byId.get(normalized);
        if (resolved == null) {
            throw new IllegalArgumentException("no se pudo resolver el " + endpoint + " '" + reference
                    + "' del flujo IFML '" + flow.name + "'");
        }
        return resolved;
    }

    private static List<String> orderedKinds() {
        List<String> kinds = new ArrayList<String>();
        kinds.add("ViewContainer");
        kinds.add("ViewComponent");
        kinds.add("Action");
        kinds.add("Event");
        kinds.add("Parameter");
        kinds.add("ViewComponentPart");
        kinds.add("DomainConcept");
        kinds.add("InteractionFlow");
        kinds.add("NavigationFlow");
        kinds.add("DataFlow");
        return kinds;
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

    private static final class ModelElement {
        private final String kind;
        private final String id;
        private final String name;
        private final Element element;
        private final String identifier;

        private ModelElement(String kind, String id, String name, Element element) {
            this.kind = kind;
            this.id = id;
            this.name = name;
            this.element = element;
            this.identifier = identifier(name.isEmpty() ? id : name);
        }

        private static String identifier(String name) {
            String sanitized = name.replaceAll("[^A-Za-z0-9_]", "_");
            if (sanitized.isEmpty() || (!Character.isLetter(sanitized.charAt(0))
                    && sanitized.charAt(0) != '_')) {
                return "_" + sanitized;
            }
            return sanitized;
        }
    }
}
