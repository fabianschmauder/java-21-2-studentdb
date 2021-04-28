package de.neuefische.studendb.model;

public class HistoryStudent extends Student{

    private String favoritProgrammingLanguage;

    public HistoryStudent(String name, String id, String favoritProgrammingLanguage) {
        super(name, id);
        this.favoritProgrammingLanguage = favoritProgrammingLanguage;
    }

    public String getFavoritProgrammingLanguage() {
        return favoritProgrammingLanguage;
    }

    public void setFavoritProgrammingLanguage(String favoritProgrammingLanguage) {
        this.favoritProgrammingLanguage = favoritProgrammingLanguage;
    }


    @Override
    public String getCourse() {
        return "Computer Science";
    }
}
