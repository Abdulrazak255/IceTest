package be.intecbrussel.stockTest;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.Eatable;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;

import be.intecbrussel.exception.NoMoreException;
import be.intecbrussel.sellers.IceCreamCar;
import be.intecbrussel.sellers.IceCreamSeller;
import be.intecbrussel.sellers.PriceList;
import be.intecbrussel.sellers.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Stream;

import static be.intecbrussel.eatables.Magnum.MagnumType.WHITECHOCOLATE;
import static org.junit.jupiter.api.Assertions.*;

public class CheckCarTest {

    private IceCreamSeller ice_Car;

    {
        ice_Car = new IceCreamCar();
    }

    @ParameterizedTest
    @MethodSource("coneFactory")
    public void testCone (Cone.Flavor flavor1,Cone.Flavor flavor2 , double expectedProfit) {

        Cone.Flavor [] balls = new Cone.Flavor[]{flavor1,flavor2};


        IceCreamCar iceCreamCar = new IceCreamCar();
        Stock stock = new Stock();
        iceCreamCar.setStock(stock);
        stock.setCones(1);
        stock.setBalls(2);

        PriceList priceList = new PriceList();
        iceCreamCar.setPriceList(priceList);

        Cone cone = iceCreamCar.orderCone(balls);

        Assertions.assertEquals(expectedProfit ,iceCreamCar.getProfit());
        assertNotNull(cone);


        //iceCreamCar.getStock().setCones(coneNumber);            //I will cry
        //iceCreamCar.getPriceList().setBallPrice(priceOfBall);   //I will laugh
    }
    public static Stream<Arguments> coneFactory()
    {

        return Stream.of(
                Arguments.of(Cone.Flavor.CHOCOLATE ,Cone.Flavor.BANANA,0.5),
                Arguments.of(Cone.Flavor.CHOCOLATE ,Cone.Flavor.STRAWBERRY,0.5),
                Arguments.of(Cone.Flavor.CHOCOLATE ,Cone.Flavor.PISTACHE,0.5),
                Arguments.of(Cone.Flavor.CHOCOLATE ,Cone.Flavor.LEMON,0.5)



        );

    }
    @ParameterizedTest
    @CsvSource({"MILKCHOCOLATE, 0.044000000000000004",
            "ALPINENUTS, 0.06"})

    public void testMagnum(Magnum.MagnumType magnumType, double expectedProfit) {
        Stock stock = new Stock();


        PriceList priceList = new PriceList();
        IceCreamCar iceCreamCar = new IceCreamCar(priceList,stock);

        iceCreamCar.setPriceList(priceList);

        stock.setMagni(1);
        Magnum magnum = iceCreamCar.orderMagnum(magnumType);


        assertEquals(expectedProfit, iceCreamCar.getProfit());
        assertNotNull(magnum);

    }
    @Test
    public void testRocket() {

        Stock stock = new Stock();
        PriceList priceList = new PriceList();

        IceCreamCar iceCreamCar = new IceCreamCar(priceList,stock);

        iceCreamCar.setPriceList(priceList);

        stock.setIceRockets(2);
        IceRocket iceRocket = iceCreamCar.orderIceRocket();
        double priceRocket = priceList.getRocketPrice()*0.20;


        assertEquals(priceRocket, iceCreamCar.getProfit());
        assertNotNull(iceRocket);
}

}