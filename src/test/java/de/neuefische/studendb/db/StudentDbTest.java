package de.neuefische.studendb.db;

import de.neuefische.studendb.model.HistoryStudent;
import de.neuefische.studendb.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDbTest {

    private static Arguments[] provideTestAddArguments() {
        return new Arguments[]{
                Arguments.of(
                        new Student[]{
                                new HistoryStudent("Student 1", "1","c#"),
                                new HistoryStudent("Student 2", "2","c#")
                        },
                        new Student[]{
                                new HistoryStudent("Student 1", "1","c#"),
                                new HistoryStudent("Student 2", "2","c#"),
                                new HistoryStudent("Jane", "42","c#")
                        }
                ),
                Arguments.of(
                        new Student[]{},
                        new Student[]{new HistoryStudent("Jane", "42","c#")}
                )
        };
    }

    private static Arguments[] provideTestRemoveArguments() {
        return new Arguments[]{
                Arguments.of(
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Jane", "42","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        },
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        }
                ),
                Arguments.of(
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        },
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        }
                ),
                Arguments.of(
                        new Student[]{
                                new HistoryStudent("Jane", "42","c#"),
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        },
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        }
                ),
                Arguments.of(
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#"),
                                new HistoryStudent("Jane", "42","c#")
                        },
                        new Student[]{
                                new HistoryStudent("Hans", "12","c#"),
                                new HistoryStudent("Peter", "23","c#")
                        }
                ),
                Arguments.of(
                        new Student[]{},
                        new Student[]{}
                ),
                Arguments.of(
                        new Student[]{new HistoryStudent("Jane", "42","c#")},
                        new Student[]{}
                )
        };
    }

    @Test
    @DisplayName("list() returns all students in the db")
    public void testList() {
        // Given
        Student[] students = new Student[]{
                new HistoryStudent("Jane", "42","c#"),
                new HistoryStudent("Klaus", "13","c#"),
                new HistoryStudent("Franky", "100","c#")
        };
        StudentDb studentDb = new StudentDb(students);

        // When
        Student[] actual = studentDb.list();

        // Then
        Student[] expected = new Student[]{
                new HistoryStudent("Jane", "42","c#"),
                new HistoryStudent("Klaus", "13","c#"),
                new HistoryStudent("Franky", "100","c#")
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("toString() returns a formatted list of all students")
    public void testToString() {
        // Given
        Student[] students = new Student[]{
                new HistoryStudent("Jane", "42","c#"),
                new HistoryStudent("Klaus", "13","c#"),
                new HistoryStudent("Franky", "100","c#")
        };
        StudentDb studentDb = new StudentDb(students);

        // When
        String actual = studentDb.toString();

        // Then
        String expected = "Student{name='Jane', id='42'}\n"
                + "Student{name='Klaus', id='13'}\n"
                + "Student{name='Franky', id='100'}\n";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideTestAddArguments")
    public void testAdd(Student[] givenStudents, Student[] expectedStudents) {
        // Given
        StudentDb studentDb = new StudentDb(givenStudents);
        Student student = new HistoryStudent("Jane", "42","c#");

        // When
        studentDb.add(student);
        Student[] actualStudents = studentDb.list();

        // Then
        assertArrayEquals(expectedStudents, actualStudents);
    }

    @ParameterizedTest
    @MethodSource("provideTestRemoveArguments")
    public void testRemove(Student[] givenStudents, Student[] expectedStudents) {
        // Given
        StudentDb studentDb = new StudentDb(givenStudents);

        // When
        studentDb.remove(new HistoryStudent("Jane", "42","c#"));
        Student[] actualStudents = studentDb.list();

        // Then
        assertArrayEquals(expectedStudents, actualStudents);
    }
}
