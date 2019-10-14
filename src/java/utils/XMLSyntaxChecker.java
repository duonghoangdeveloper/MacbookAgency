/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Duong
 */
public class XMLSyntaxChecker {

    public static final String CONTENT = "content";
    public static final String OPEN_BRACKET = "openBracket";
    public static final String OPEN_TAG_NAME = "openTagName";
    public static final String TAG_INNER = "tagInner";
    public static final String ATTR_NAME = "attributeName";
    public static final String EQUAL_WAIT = "equalWait";
    public static final String EQUAL = "equal";
    public static final String VALUE_WAIT = "valueWait";
    public static final String ATTR_VALUE_NQ = "nonQuotedAttributeValue";
    public static final String ATTR_VALUE_Q = "quotedAttributeValue";
    public static final String EMPTY_SLASH = "emptyTagSlash";
    public static final String CLOSE_BRACKET = "closeBracket";
    public static final String CLOSE_TAG_SLASH = "closeTagSlash";
    public static final String CLOSE_TAG_NAME = "closeTagName";
    public static final String WAIT_END_TAG_CLOSE = "waitEndTagClose";

    public static final char LT = '<';
    public static final char SLASH = '/';
    public static final char GT = '>';
    public static final char EQ = '=';
    public static final char D_QUOT = '"';
    public static final char S_QUOT = '\'';

    public static final char UNDERSCORE = '_';
    public static final char COLON = ':';
    public static final char HYPHEN = '-';
    public static final char PERIOD = '.';

    private static boolean isStartChar(char c) {
        return Character.isLetter(c) || UNDERSCORE == c || COLON == c;
    }

    private static boolean isNamedChar(char c) {
        return Character.isLetterOrDigit(c) || UNDERSCORE == c || HYPHEN == c || PERIOD == c;
    }

    public static boolean isStartTagChars(char c) {
        return isStartChar(c);
    }

    public static boolean isStartAttrChar(char c) {
        return isStartChar(c);
    }

    public static boolean isTagChars(char c) {
        return isNamedChar(c);
    }

    public static boolean isAttrChars(char c) {
        return isNamedChar(c);
    }

    public static boolean isSpace(char c) {
        return Character.isSpaceChar(c);
    }

    public static final List<String> INLINE_TAGS = Arrays.asList(
            "area", "base", "br", "col", "command",
            "embed", "hr", "img", "input", "keygen",
            "link", "meta", "param", "source", "track", "wbr");

    private Character quote;

    public String check(String src) {
        src = src + " ";
        char[] reader = src.toCharArray();
        StringBuilder writer = new StringBuilder();

        StringBuilder openTag = new StringBuilder();
        boolean isEmptyTag = false, isOpenTag = false, isCloseTag = false;
        StringBuilder closeTag = new StringBuilder();
        StringBuilder attrName = new StringBuilder();
        StringBuilder attrValue = new StringBuilder();
        Map<String, String> attributes = new HashMap<String, String>();

        StringBuilder content = new StringBuilder();

        Stack<String> stack = new Stack<String>();

        String state = CONTENT;

        for (int i = 0; i < reader.length; i++) {
            char c = reader[i];
            if (CONTENT.equals(state)) {
                if (c == LT) {
                    state = OPEN_BRACKET;
                    writer.append(content.toString()
                            .trim()
                            .replace("&", "&amp;"));
                } else {
                    content.append(c);
                }
            } else if (OPEN_BRACKET.equals(state)) {
                if (isStartTagChars(c)) {
                    state = OPEN_TAG_NAME;

                    isOpenTag = true;
                    isCloseTag = false;
                    isEmptyTag = false;

                    openTag.setLength(0);
                    openTag.append(c);
                } else if (c == SLASH) {
                    state = CLOSE_TAG_SLASH;

                    isOpenTag = false;
                    isCloseTag = true;
                    isEmptyTag = false;
                }
            } else if (OPEN_TAG_NAME.equals(state)) {
                if (isTagChars(c)) { // loop
                    openTag.append(c);
                } else if (isSpace(c)) {
                    state = TAG_INNER;
                    attributes.clear();
                } else if (c == GT) {
                    state = CLOSE_BRACKET;
                } else if (c == SLASH) {
                    state = EMPTY_SLASH;
                }
            } else if (TAG_INNER.equals(state)) {
                if (isSpace(c)) { // loop

                } else if (isStartAttrChar(c)) {
                    state = ATTR_NAME;

                    attrName.setLength(0);
                    attrName.append(c);
                } else if (c == GT) {
                    state = CLOSE_BRACKET;
                } else if (c == SLASH) {
                    state = EMPTY_SLASH;
                }
            } else if (ATTR_NAME.equals(state)) {
                if (isAttrChars(c)) { // loop
                    attrName.append(c);
                } else if (c == EQ) {
                    state = EQUAL;
                } else if (isSpace(c)) {
                    state = EQUAL_WAIT;
                } else { // exception
                    if (c == SLASH) {
                        attributes.put(attrName.toString(), "true");
                        state = EMPTY_SLASH;
                    } else if (c == GT) {
                        attributes.put(attrName.toString(), "true");
                        state = CLOSE_BRACKET;
                    }
                }
            } else if (EQUAL_WAIT.equals(state)) {
                if (isSpace(c)) { // loop

                } else if (c == EQ) {
                    state = EQUAL;
                } else { // exception 
                    if (isStartAttrChar(c)) {
                        attributes.put(attrName.toString(), "true");
                        state = ATTR_NAME;

                        attrName.setLength(0);
                        attrName.append(c);
                    }
                }
            } else if (EQUAL.equals(state)) {
                if (isSpace(c)) { // loop

                } else if (c == D_QUOT || c == S_QUOT) {
                    quote = c;
                    state = ATTR_VALUE_Q;

                    attrValue.setLength(0);
                } else if (!isSpace(c) && c != GT) {
                    state = ATTR_VALUE_NQ;

                    attrValue.setLength(0);
                    attrValue.append(c);
                }
            } else if (ATTR_VALUE_Q.equals(state)) {
                if (c != quote) { // loop
                    attrValue.append(c);
                } else if (c == quote) {
                    state = TAG_INNER;
                    attributes.put(attrName.toString(), attrValue.toString());
                }
            } else if (ATTR_VALUE_NQ.equals(state)) {
                if (!isSpace(c) && c != GT) {
                    attrValue.append(c);
                } else if (isSpace(c)) {
                    state = TAG_INNER;
                    attributes.put(attrName.toString(), attrValue.toString());
                } else if (c == GT) {
                    state = CLOSE_BRACKET;
                    attributes.put(attrName.toString(), attrValue.toString());
                }
            } else if (EMPTY_SLASH.equals(state)) {
                if (c == GT) {
                    state = CLOSE_BRACKET;
                    isEmptyTag = true;
                }
            } else if (CLOSE_TAG_SLASH.equals(state)) {
                if (isStartTagChars(c)) {
                    state = CLOSE_TAG_NAME;

                    closeTag.setLength(0);
                    closeTag.append(c);
                }
            } else if (CLOSE_TAG_NAME.equals(state)) {
                if (isTagChars(c)) { // loop
                    closeTag.append(c);
                } else if (isSpace(c)) {
                    state = WAIT_END_TAG_CLOSE;
                } else if (c == GT) {
                    state = CLOSE_BRACKET;
                }
            } else if (WAIT_END_TAG_CLOSE.equals(state)) {
                if (isSpace(c)) { // loop

                } else if (c == GT) {
                    state = CLOSE_BRACKET;
                }
            } else if (CLOSE_BRACKET.equals(state)) {
                if (isOpenTag) {
                    String openTagName = openTag.toString().toLowerCase();

                    if (INLINE_TAGS.contains(openTagName)) {
                        isEmptyTag = true;
                    }
                    writer.append(LT)
                            .append(openTagName)
                            .append(convert(attributes))
                            .append((isEmptyTag ? "/" : ""))
                            .append(GT);
                    attributes.clear();
                    // STACK: PUSH OPEN-TAG
                    if (!isEmptyTag) {
                        stack.push(openTagName);
                    }
                } else if (isCloseTag) {
                    // STACK : POPOUT 
                    String closeTagName = closeTag.toString().toLowerCase();

                    if (!stack.isEmpty() && stack.contains(closeTagName)) {
                        while (!stack.isEmpty() && !stack.peek().equals(closeTagName)) {
                            writer.append(LT)
                                    .append(SLASH)
                                    .append(stack.pop())
                                    .append(GT);
                        }
                        if (!stack.isEmpty() && stack.peek().equals(closeTagName)) {
                            writer.append(LT)
                                    .append(SLASH)
                                    .append(stack.pop())
                                    .append(GT);
                        }
                    } // end close-tag missing
                }

                if (c == LT) {
                    state = OPEN_BRACKET;
                } else {
                    state = CONTENT;

                    content.setLength(0);
                    content.append(c);
                }
            }
        }
        if (CONTENT.equals(state)) {
            writer.append(content.toString().trim().replace("&", "&amp;"));
        }

        // pop out all left tags 
        while (!stack.isEmpty()) {
            writer.append(LT)
                    .append(SLASH)
                    .append(stack.pop())
                    .append(GT);
        }

        return writer.toString();
    }

    private String convert(Map<String, String> attributes) {
        if (attributes.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String value = entry.getValue()
                    .replace("&", "&amp;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&apos;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;");

            builder.append(entry.getKey())
                    .append("=")
                    .append("\"").append(value).append("\"")
                    .append(" ");
        }
        String result = builder.toString().trim();
        if (!result.equals("")) {
            result = " " + result;
        }
        return result;
    }
}
