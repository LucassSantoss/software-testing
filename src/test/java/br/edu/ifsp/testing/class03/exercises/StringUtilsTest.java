package br.edu.ifsp.testing.class03.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    private StringUtils sut;

    @BeforeEach
    void setUp() {
        sut = new StringUtils();
    }

    @Nested
    @DisplayName("Testing null and empty values")
    class TestingNullAndEmptyValues {
        @Test
        @DisplayName("Should return null when string is null")
        void shouldReturnNullWhenStringIsNull() {
            final String[] obtained = sut.substringsBetween(null, "b", "a");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return an empty list when str is empty")
        void shouldReturnAnEmptyListWhenStrIsEmpty() {
            final String[] obtained = sut.substringsBetween("", "a", "b");
        }

        @Test
        @DisplayName("Should return null when open is empty")
        void shouldReturnNullWhenOpenIsEmpty(){
            final String[] obtained = sut.substringsBetween("ada", "", "a");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should throw illegalArgumentException when open is null")
        void shouldThrowIllegalArgumentExceptionWhenOpenIsNull(){
            assertThrows(IllegalArgumentException.class, () -> sut.substringsBetween("ada", null, "a"));
        }

        @Test
        @DisplayName("Should return null when close is empty")
        void shouldReturnNullWhenCloseIsEmpty(){
            final String[] obtained = sut.substringsBetween("ada", "b", "");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when close is null")
        void shouldThrowIllegalArgumentExceptionWhenCloseIsNull(){
            assertThrows(IllegalArgumentException.class, () -> sut.substringsBetween("ada", "a", null));
        }

    }

    // str = 1, open = 1, close = 1
    @Nested
    @DisplayName("Testing all lengths equals one")
    class TestingAllLengthsEqualsOne {
        @Test
        @DisplayName("Should return null when str is equals open and not equals close")
        void shouldReturnNullWhenStrIsEqualsOpenAndNotEqualsClose(){
            final String[] obtained = sut.substringsBetween("a", "a", "b");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return null when str is equals close and not equals open")
        void shouldReturnNullWhenStrIsEqualsCloseAndNotEqualsOpen(){
            final String[] obtained = sut.substringsBetween("a", "b", "a");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return null when str is equals close and open")
        void shouldReturnNullWhenStrIsEqualsCloseAndOpen(){
            final String[] obtained = sut.substringsBetween("a", "a", "a");
            assertThat(obtained).isNull();
        }
        
        @Test
        @DisplayName("Should return null when str is not equals open and close")
        void shouldReturnNullWhenStrIsNotEqualsOpenAndClose(){
            final String[] obtained = sut.substringsBetween("b", "a", "a");
            assertThat(obtained).isNull();
        }
    }

    @Nested
    @DisplayName("Testing str length greater then one")
    class TestingStrLengthGreaterThenOne {
        @Test
        @DisplayName("Should return Null when str contains open but not close")
        void shouldReturnNullWhenStrContainsOpenButNotClose(){
            final String[] obtained = sut.substringsBetween("abc", "a", "d");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return null when str contains close but not open")
        void shouldReturnNullWhenStrContainsCloseButNotOpen(){
            final String[] obtained = sut.substringsBetween("abc", "d", "a");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return empty list when str contains open and close one time together")
        void shouldReturnEmptyListWhenStrContainsOpenAndCloseOneTimeTogether(){
            final String[] obtained = sut.substringsBetween("abc", "a", "b");
            final String[] expected = {""};
            assertThat(obtained).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return a letter when str contains open and close one time not together")
        void shouldReturnALetterWhenStrContainsOpenAndCloseOneTimeNotTogether(){
            final String[] obtained = sut.substringsBetween("abc", "a", "c");
            final String[] expected = {"b"};
            assertThat(obtained).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return a list of letters when str contains open and close a lot of times")
        void shouldReturnAListOfLettersWhenStrContainsOpenAndCloseALotOfTimes(){
            final String[] obtained = sut.substringsBetween("abcakjcahc", "a", "c");
            final String[] expected = {"b", "kj", "h"};
            assertThat(obtained).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return null when str not contains open and close")
        void shouldReturnNullWhenStrNotContainsOpenAndClose(){
            final String[] obtained = sut.substringsBetween("abcawd", "z", "x");
            assertThat(obtained).isNull();
        }
    }

    @Nested
    @DisplayName("Testing all lengths greater then one")
    class TestingAllLengthsGreaterThenOne {
        @Test
        @DisplayName("Should return null when str contains open but not close")
        void shouldReturnNullWhenStrContainsOpenButNotClose(){
            final String[] obtained = sut.substringsBetween("aaaaaa", "aa", "bb");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return null when str contains close but not open")
        void shouldReturnNullWhenStrContainsCloseButNotOpen(){
            final String[] obtained = sut.substringsBetween("aaaaaa", "bb", "aa");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return null when str not contains open and close")
        void shouldReturnNullWhenStrNotContainsOpenAndClose(){
            final String[] obtained = sut.substringsBetween("aaaaaa", "ccc", "kkk");
            assertThat(obtained).isNull();
        }

        @Test
        @DisplayName("Should return a list with a letter when str contains open and close once")
        void shouldReturnAListWithALetterWhenStrContainsOpenAndCloseOnce() {
            final String[] obtained = sut.substringsBetween("abcde", "ab", "de");
            final String[] expected = {"c"};
            assertThat(obtained).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return some letters when str contains open and close more then one time")
        void shouldReturnSomeLettersWhenStrContainsOpenAndCloseMoreThenOneTime(){
            final String[] obtained = sut.substringsBetween("abcdeabhhde", "ab", "de");
            final String[] expected = {"c", "hh"};
            assertThat(obtained).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("Testing white spaces on str")
    class TestingWhiteSpacesOnStr {
        @Test
        @DisplayName("Should return some white spaces when str contains white spaces")
        void shouldReturnWhiteSpacesWhenStrContainsWhiteSpaces(){
            final String[] obtained = sut.substringsBetween("ab c abc", "a", "b");
            final String[] expected = {"", ""};
            assertThat(obtained).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return some letters and white spaces when str contains white spaces")
        void shouldReturnSomeLettersAndWhiteSpacesWhenStrContainsWhiteSpaces(){
            final String[] obtained = sut.substringsBetween("a bcd d", "a ", " d");
            final String[] expected = {"bcd"};
        }
    }

}