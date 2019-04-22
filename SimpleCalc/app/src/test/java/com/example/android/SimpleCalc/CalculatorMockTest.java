package com.example.android.SimpleCalc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CalculatorMockTest {

    @Mock
    private Calculator mCalculator;

    @Test
    public void calcButton() {
        when(mCalculator.add(1d, 1d)).thenReturn(2d);
        when(mCalculator.add(-1d, 2d)).thenReturn(1d);

        when(mCalculator.sub(1d, 1d)).thenReturn(0d);
        when(mCalculator.sub(1d, 17d)).thenReturn(-16d);

        when(mCalculator.mul(32d, 2d)).thenReturn(64d);

        when(mCalculator.div(32d, 2d)).thenReturn(16d);
        when(mCalculator.div(32d, 0)).thenThrow(IllegalArgumentException.class);
    }
}
