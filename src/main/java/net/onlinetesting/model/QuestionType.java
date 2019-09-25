package net.onlinetesting.model;

public enum QuestionType {
    ONE ("ONE"),
    MULTIPLE ("MULTIPLE");

    private final String name;

    QuestionType(String s) {
        name = s;
    }
    public String getName() {
        return name;
    }

}
