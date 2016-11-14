package com.example.com.examw4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 09/11/2016.
 */

public class Balanced {
    /**
     * A string containing only parentheses is balanced if the following is true:
     * 1. if it is an empty string
     * 2. if A and B are correct, AB is correct,
     * 3. if A is correct, (A) and {A} and [A] are also correct.
     * Examples of some correctly balanced strings are:
     * "{}()", "[{()}]", "({()})"
     * Examples of some unbalanced strings are:
     * "{}(", "({)}", "[[", "}{" etc.
     **/

    public static final String input[] = new String[]{
            "{}()",
            "[{()}]",
            "[[",
            "({()})",
            "{}(",
            "[[])"
    };

    public static void main(String[] args) {
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i] + " " + isBalanced(input[i]));
        }
    }

    public static boolean isBalanced(String s) {
        List<String> stack1 = new ArrayList<String>();
        List<String> stack2 = new ArrayList<String>();

        if (s.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case '{':
                        stack1.add("1a");
                        break;
                    case '}':
                        stack2.add("1b");
                        break;
                    case '(':
                        stack1.add("2a");
                        break;
                    case ')':
                        stack2.add("2b");
                        break;
                    case '[':
                        stack1.add("3a");
                        break;
                    case ']':
                        stack2.add("3b");
                        break;
                }
            }

            boolean isTrue = false;
            for (String string1 : stack1) {
                for (String string2 : stack2) {
                    if ((string1.charAt(0) == string2.charAt(0)) &&
                            (string1.charAt(1) != string2.charAt(1))) {
                        isTrue = true;
                        break;
                    } else {
                        isTrue = false;
                    }
                }
            }
            return isTrue;
        }
    }

}
