package com.humansofulmu;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@Service
public class Md2TaskParser {

    private final String NEW_LINE = "\n";

    Parser parser = Parser.builder().build();
    HtmlRenderer renderer = HtmlRenderer.builder().build();

    // 1. MultipartFile → String (markdown)
    public String convertMd2String(MultipartFile mdFile) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(mdFile.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(NEW_LINE));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read markdown file", e);
        }
    }

    // 2. markdown → PostRequestDTO
    public PostRequestDTO parse2DTO(String markdown) {
        Document document = parser.parse(markdown);

        String name = null;
        String content = null;

        Node currentNode = document.getFirstChild();

        while (currentNode != null) {

            if (isHeader(currentNode) && currentNode.getFirstChild().getChars().toString().contains("user")) {
                currentNode = currentNode.getNext();
                String userContent = currentNode.getChars().toString();
                name = userContent.substring(userContent.indexOf(":") + 1).trim();
                currentNode = currentNode.getNext();
            }

            if (isHeader(currentNode) && currentNode.getFirstChild().getChars().toString().contains("content")) {
                currentNode = currentNode.getNext();
                content = renderer.render(currentNode).trim();
                Node next = currentNode.getNext();
                if (next == null) break;
                currentNode = next;
            }

        }

        if (name == null || content.length() == 0) {
            throw new IllegalArgumentException("Missing #user or #content section in markdown.");
        }

        return new PostRequestDTO(name, content.trim());
    }

    private boolean isHeader(Node node) {
        return node != null && node instanceof Heading heading && heading.getLevel() == 1;
    }


}
