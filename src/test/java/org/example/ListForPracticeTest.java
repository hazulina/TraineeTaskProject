package org.example;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Test class for {@link org.example.ListForPractice}
 */
class ListForPracticeTest {

    @DisplayName("should add Element to List - correct Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForAddTest")
    void addElementTest(String description, ListForPractice givenList, Object expectedAnswer) {
        //given
        //when
        givenList.addElement(expectedAnswer);
        Object actual = givenList.getElementByIndex(0);
        //then
        assertThat(actual).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("should add Element to List on specific index - correct Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForAddToIndexCorrectTest")
    void addElementToIndexTest(String description, int givenIndex, ListForPractice givenList, Object expectedAnswer) {
        //given
        //when
        givenList.addElementToIndex(expectedAnswer, givenIndex);
        //then
        assertThat(givenList.getElementByIndex(givenIndex)).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("should throw IndexOutOfBoundsException - addElementToIndexWrongTest")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForAddToIndexIncorrectTest")
    void addElementToIndexWrongTest(String description, int givenIndex, ListForPractice givenList, Object expectedAnswer) {
        //given
        //when
        Throwable throwable = catchThrowable(() -> {
            givenList.addElementToIndex(expectedAnswer, givenIndex);
        });
        //then
        assertThat(throwable).as(description).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("get element by index - correct Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForGetByIndexCorrectTest")
    void getElementByIndexTest(String description, int givenIndex, ListForPractice givenList, Object expectedAnswer) {
        //given
        //when
        Object actual = givenList.getElementByIndex(givenIndex);
        //then
        assertThat(actual).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("get element by index - incorrect Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForGetByIndexIncorrectTest")
    void getElementByIndexWrongTest(String description, int givenIndex, ListForPractice givenList) {
        //given
        //when
        Throwable throwable = catchThrowable(() -> {
            givenList.getElementByIndex(givenIndex);
        });
        //then
        assertThat(throwable).as(description).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("update value by index - correct Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForUpdateElementCorrectTest")
    void updateElementByIndexTest(String description, int givenIndex, int givenSize, ListForPractice givenList, Object expectedAnswer) {
        //given
        //when
        givenList.updateElementByIndex(expectedAnswer, givenIndex);
        int actualSize = givenList.getSize();
        Object actual = givenList.getElementByIndex(givenIndex);
        //then
        assertThat(actualSize).as(description).isEqualTo(givenSize);
        assertThat(actual).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("update value by index - incorrect Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForUpdateElementIncorrectTest")
    void updateElementByIndexIncorrectTest(String description, int givenIndex, int givenSize, ListForPractice givenList, Object expectedAnswer) {
        //given
        //when
        Throwable throwable = catchThrowable(() -> givenList.updateElementByIndex(expectedAnswer, givenIndex));
        int actualSize = givenList.getSize();

        //then
        assertThat(actualSize).as(description).isEqualTo(givenSize);
        assertThat(throwable).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("delete element - correct Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForDeleteCorrectTest")
    void deleteElementTest(String description, ListForPractice givenList, Object givenElement, int expectedAnswer) {
        //given
        //when
        givenList.deleteElement(givenElement);
        int actual = givenList.getIndex(givenElement);
        //then
        assertThat(actual).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("delete element - incorrect Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForDeleteIncorrectTest")
    void deleteElementIncorrectTest(String description, ListForPractice givenList, Object givenElement, int expectedAnswer) {
        //given
        //when
        givenList.deleteElement(givenElement);
        int actual = givenList.getSize();
        //then
        assertThat(actual).as(description).isNotEqualTo(expectedAnswer);
    }

    @DisplayName("remove all elements from the list - correctTest")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForTruncateTest")
    void truncateTest(String description, int expectedAnswer, ListForPractice givenList) {
        //given
        //when
        givenList.truncate();
        int actual = givenList.getSize();
        //then
        assertThat(actual).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("sort Integer elements asc - correct test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForIntegerSortTest")
    void sortIntegerTest(String description, ListForPractice<Integer> givenList, ListForPractice<Integer> expectedAnswer) {
        //given
        //when
        givenList.sort((o1, o2) -> o2 - o1);
        //then
        assertThat(givenList).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("sort String elements asc - correct test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForStringSortTest")
    void sortStringTest(String description, ListForPractice<String> givenList, ListForPractice<String> expectedAnswer) {
        //given
        //when
        givenList.sort(Comparator.naturalOrder());
        //then
        assertThat(givenList).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("sort Double elements asc - correct test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForDoubleSortTest")
    void sortDoubleTest(String description, ListForPractice<Double> givenList, ListForPractice<Double> expectedAnswer) {
        //given
        //when
        givenList.sort(Comparator.naturalOrder());
        //then
        assertThat(givenList).as(description).isEqualTo(expectedAnswer);
    }

    @DisplayName("should get list size - correct Test")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenArgumentsForGetListSizeTest")
    void getSizeTest(String description, int expectedAnswer, ListForPractice givenList) {
        //given
        //when
        int actual = givenList.getSize();
        //then
        assertThat(actual).as(description).isEqualTo(expectedAnswer);
    }

    static Stream<Arguments> givenArgumentsForAddToIndexCorrectTest() {
        return Stream.of(
                arguments("String list", 0, generateStringList(1), "Java"),
                arguments("Integer List", 1, generateIntegerList(3), 73),
                arguments("Double List", 4, generateDoubleList(5), 2.75),
                arguments("Object List", 0, generateObjectList(1), "java")
        );
    }

    static Stream<Arguments> givenArgumentsForAddToIndexIncorrectTest() {
        return Stream.of(
                arguments("String list", 5, generateStringList(0), "Java"),
                arguments("Integer List", 7, generateIntegerList(3), 73),
                arguments("Double List", 25, generateDoubleList(5), 2.75),
                arguments("Object List", 14, generateObjectList(1), "java")
        );
    }

    static Stream<Arguments> givenArgumentsForAddTest() {
        return Stream.of(
                arguments("String list", generateStringList(0), "Java"),
                arguments("Integer List", generateIntegerList(0), 73),
                arguments("Double List", generateDoubleList(0), 2.75),
                arguments("Object List", generateObjectList(0), "java")
        );
    }

    static Stream<Arguments> givenArgumentsForGetByIndexCorrectTest() {
        return Stream.of(
                arguments("String list", 0, generateStringListForGetByIndex(2, 0, "Java"), "Java"),
                arguments("Integer List", 1, generateIntegerListForGetByIndex(3, 1, 73), 73),
                arguments("Double List", 4, generateDoubleListForGetByIndex(5, 4, 2.75), 2.75),
                arguments("Object List", 1, generateObjectListForGetByIndex(2, 1, "java"), "java")
        );
    }

    static Stream<Arguments> givenArgumentsForDeleteCorrectTest() {
        return Stream.of(
                arguments("String list", generateStringListForGetByIndex(2, 0, "Java"), "Java", -1),
                arguments("Integer List", generateIntegerListForGetByIndex(3, 1, 73), 73, -1),
                arguments("Double List", generateDoubleListForGetByIndex(5, 4, 2.75), 2.75, -1),
                arguments("Object List", generateObjectListForGetByIndex(2, 1, "java"), "java", -1)
        );
    }

    static Stream<Arguments> givenArgumentsForDeleteIncorrectTest() {
        return Stream.of(
                arguments("String list", generateStringListForGetByIndex(1, 0, "Java"), "Java1", -1),
                arguments("Integer List", generateIntegerListForGetByIndex(1, 0, 73), 732, -1),
                arguments("Double List", generateDoubleListForGetByIndex(1, 0, 2.75), 4.75, -1),
                arguments("Object List", generateObjectListForGetByIndex(1, 0, "java"), "java4", -1)
        );
    }

    static Stream<Arguments> givenArgumentsForGetByIndexIncorrectTest() {
        return Stream.of(
                arguments("String list", 4, generateStringListForGetByIndex(2, 0, "Java")),
                arguments("Integer List", 12, generateIntegerListForGetByIndex(3, 1, 73)),
                arguments("Double List", -1, generateDoubleListForGetByIndex(5, 4, 2.75)),
                arguments("Object List", 22, generateObjectListForGetByIndex(2, 1, "java"))
        );
    }

    static Stream<Arguments> givenArgumentsForGetListSizeTest() {
        return Stream.of(
                arguments("String list", 1, generateStringList(1)),
                arguments("Integer List", 8, generateIntegerList(8)),
                arguments("Double List", 10, generateDoubleList(10)),
                arguments("Empty List", 0, generateStringList(0))
        );
    }

    static Stream<Arguments> givenArgumentsForUpdateElementCorrectTest() {
        return Stream.of(
                arguments("String list", 0, 1, generateStringList(1), "Java"),
                arguments("Integer List", 1, 3, generateIntegerList(3), 73),
                arguments("Double List", 4, 5, generateDoubleList(5), 2.75),
                arguments("Object List", 0, 1, generateObjectList(1), "java")
        );
    }

    static Stream<Arguments> givenArgumentsForUpdateElementIncorrectTest() {
        return Stream.of(
                arguments("String list", 0, 0, generateStringList(0), "Java"),
                arguments("Integer List", 1, 0, generateIntegerList(0), 73),
                arguments("Double List", 24, 5, generateDoubleList(5), 2.75),
                arguments("Object List", 10, 1, generateObjectList(1), "java")
        );
    }

    static Stream<Arguments> givenArgumentsForIntegerSortTest() {
        return Stream.of(
                arguments("Integer List", generateUnsortedIntegerList(15), generateSortedIntegerList(15)),
                arguments("Integer List", generateUnsortedIntegerList(1), generateSortedIntegerList(1)),
                arguments("Integer List", generateUnsortedIntegerList(100), generateSortedIntegerList(100)),
                arguments("Integer List", generateUnsortedIntegerList(0), generateSortedIntegerList(0))
        );
    }

    static Stream<Arguments> givenArgumentsForStringSortTest() {
        return Stream.of(
                arguments("String list", generateUnsortedStringList(7), generateSortedStringList(7)),
                arguments("String list", generateUnsortedStringList(0), generateSortedStringList(0)),
                arguments("String list", generateUnsortedStringList(100), generateSortedStringList(100)),
                arguments("String list", generateUnsortedStringList(1), generateSortedStringList(1))

        );
    }

    static Stream<Arguments> givenArgumentsForDoubleSortTest() {
        return Stream.of(
                arguments("Double List", generateUnsortedDoubleList(4), generateSortedDoubleList(4)),
                arguments("Double List", generateUnsortedDoubleList(1000), generateSortedDoubleList(1000)),
                arguments("Double List", generateUnsortedDoubleList(0), generateSortedDoubleList(0)),
                arguments("Double List", generateUnsortedDoubleList(1), generateSortedDoubleList(1))

        );
    }

    static Stream<Arguments> givenArgumentsForTruncateTest() {
        return Stream.of(
                arguments("String list", 0, generateStringList(1)),
                arguments("Integer List", 0, generateIntegerList(8)),
                arguments("Double List", 0, generateDoubleList(10)),
                arguments("Empty List", 0, generateStringList(0))
        );
    }

    static ListForPractice<String> generateStringList(int size) {
        ListForPractice<String> testList = new ArrayListForPractice<>();
        byte[] array = new byte[7];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            random.nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);
            testList.addElement(generatedString);
        }
        return testList;
    }

    static ListForPractice<String> generateStringListForGetByIndex(int size, int targetIndex, String expected) {
        ListForPractice<String> testList = new ArrayListForPractice<>();
        byte[] array = new byte[7];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            random.nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);
            testList.addElement(generatedString);
        }
        testList.updateElementByIndex(expected, targetIndex);
        return testList;
    }

    static ListForPractice<String> generateSortedStringList(int size) {
        ListForPractice<String> testList = new ArrayListForPractice<>();
        for (int i = 0; i < size; i++) {
            String generatedString = "" + i;
            testList.addElement(generatedString);
        }
        return testList;
    }

    static ListForPractice<String> generateUnsortedStringList(int size) {
        ListForPractice<String> testList = new ArrayListForPractice<>();
        for (int i = size - 1; i >= 0; i--) {
            String generatedString = "" + i;
            testList.addElement(generatedString);
        }
        return testList;
    }

    static ListForPractice<Integer> generateIntegerList(int size) {
        ListForPractice<Integer> testList = new ArrayListForPractice<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int generatedInt = random.nextInt();
            testList.addElement(generatedInt);
        }
        return testList;
    }

    static ListForPractice<Integer> generateIntegerListForGetByIndex(int size, int targetIndex, Integer expected) {
        ListForPractice<Integer> testList = new ArrayListForPractice<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int generatedInt = random.nextInt();
            testList.addElement(generatedInt);
        }
        testList.updateElementByIndex(expected, targetIndex);
        return testList;
    }

    static ListForPractice<Integer> generateSortedIntegerList(int size) {
        ListForPractice<Integer> testList = new ArrayListForPractice<>();
        for (int i = 0; i < size; i++) {
            testList.addElement(i);
        }
        return testList;
    }

    static ListForPractice<Integer> generateUnsortedIntegerList(int size) {
        ListForPractice<Integer> testList = new ArrayListForPractice<>();
        for (int i = size - 1; i >= 0; i--) {
            testList.addElement(i);
        }
        return testList;
    }

    static ListForPractice<Double> generateDoubleList(int size) {
        ListForPractice<Double> testList = new ArrayListForPractice<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            double generatedDouble = random.nextDouble();
            testList.addElement(generatedDouble);
        }
        return testList;
    }

    static ListForPractice<Double> generateDoubleListForGetByIndex(int size, int targetIndex, Double expected) {
        ListForPractice<Double> testList = new ArrayListForPractice<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            double generatedDouble = random.nextDouble();
            testList.addElement(generatedDouble);
        }
        testList.updateElementByIndex(expected, targetIndex);
        return testList;
    }

    static ListForPractice<Double> generateSortedDoubleList(int size) {
        ListForPractice<Double> testList = new ArrayListForPractice<>();
        for (int i = 0; i < size; i++) {
            testList.addElement((double) i);
        }
        return testList;
    }

    static ListForPractice<Double> generateUnsortedDoubleList(int size) {
        ListForPractice<Double> testList = new ArrayListForPractice<>();
        for (int i = size - 1; i >= 0; i--) {
            testList.addElement((double) i);
        }
        return testList;
    }

    static ListForPractice<Object> generateObjectList(int size) {
        ListForPractice<Object> testList = new ArrayListForPractice<>();
        for (int i = 0; i < size; i++) {
            Object generatedObject = "java" + i;
            testList.addElement(generatedObject);
        }
        return testList;
    }

    static ListForPractice<Object> generateObjectListForGetByIndex(int size, int targetIndex, Object expected) {
        ListForPractice<Object> testList = new ArrayListForPractice<>();
        for (int i = 0; i < size; i++) {
            Object generatedObject = "java" + i;
            testList.addElement(generatedObject);
        }
        testList.updateElementByIndex(expected, targetIndex);
        return testList;
    }
}