package br.edu.ifsp.testing.class02.exercises;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChocolateTest {
    private Chocolate sut;

    @BeforeEach
    void setUp() {
        sut = new Chocolate();
    }

    @Nested
    @DisplayName("Testing valid cases")
    class TestingValidCases {
        @Test
        @DisplayName("Should return zero when chocolate price is grater then money")
        void shouldReturnZeroWhenChocolatePriceIsGraterThenMoney(){
            int obtained = sut.calculateTotalOfChocolates(2, 5, 2);
            assertThat(obtained).isZero();
        }

        @Test
        @DisplayName("Should return one when chocolate price is equals money")
        void shouldReturnOneWhenChocolatePriceIsEqualsMoney(){
            int obtained = sut.calculateTotalOfChocolates(5, 5, 2);
            assertThat(obtained).isOne();
        }

        @Test
        @DisplayName("Should return one when can buy one but can not trade")
        void shouldReturnOneWhenCanBuyOneButCanNotTrade(){
            int obtained = sut.calculateTotalOfChocolates(6, 5, 2);
            assertThat(obtained).isOne();
        }

        @Test
        @DisplayName("Should return positive when can buy chocolates and trade")
        void shouldReturnPositiveWhenCanBuyChocolatesAndTrade(){
            int obtained = sut.calculateTotalOfChocolates(10, 5, 2);
            assertThat(obtained).isEqualTo(3);
        }

        @Test
        @DisplayName("Should return positive when can buy chocolates and trade more then once")
        void shouldReturnPositiveWhenCanBuyChocolatesAndTradeMoreThenOnce(){
            int obtained = sut.calculateTotalOfChocolates(15, 5, 2);
            assertThat(obtained).isEqualTo(5);
        }

        @Test
        @DisplayName("Should return positive when can buy chocolates and trade more then twice")
        void shouldReturnPositiveWhenCanBuyChocolatesAndTradeMoreThenTwice(){
            int obtained = sut.calculateTotalOfChocolates(20, 2, 2);
            assertThat(obtained).isEqualTo(19);
        }
    }

    @Nested
    @DisplayName("Testing invalid cases")
    class TestingInvalidCases {

        @ParameterizedTest
        @ValueSource(ints = {-2, -1})
        @DisplayName("Should throw invalidArgumentException when money is less then 0")
        void shouldThrowInvalidArgumentExceptionWhenMoneyIsLessThen0(int money){
            assertThrows(IllegalArgumentException.class, () -> sut.calculateTotalOfChocolates(money, 5, 10));
        }

        @ParameterizedTest
        @ValueSource(ints = {-2, 0})
        @DisplayName("Should throw invalidArgumentException when chocolate price is less or equals 0")
        void shouldThrowInvalidArgumentExceptionWhenChocolatePriceIsLessOrEquals0(int price){
            assertThrows(IllegalArgumentException.class, () -> sut.calculateTotalOfChocolates(10, price, 5));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 1})
        @DisplayName("Should throw invalidArgumentException when promotion is less or equals one")
        void shouldThrowInvalidArgumentExceptionWhenPromotionIsLessOrEqualsOne(int promotion){
            assertThrows(IllegalArgumentException.class, () -> sut.calculateTotalOfChocolates(10, 5, promotion));
        }
    }
}