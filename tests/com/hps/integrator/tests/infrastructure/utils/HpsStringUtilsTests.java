package com.hps.integrator.tests.infrastructure.utils;

import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class HpsStringUtilsTests
{
  @Test
  public void join_shouldReturnEmptyString_whenListIsEmpty()
  {
    //arrange
    List<String> list = new ArrayList<String>();

    //act
    String result = HpsStringUtils.join(list, ',');

    //assert
    assertEquals(result, "");
  }

  @Test
  public void join_shouldNotIncludeComma_whenListHasOneItem()
  {
    //arrange
    List<String> list = new ArrayList<String>();
    list.add("Tacos");

    //act
    String result = HpsStringUtils.join(list, ',');

    //assert
    assertEquals(result, "Tacos");
  }

  @Test
  public void join_shouldSeparateValuesByComma_whenListHasMultipleItems()
  {
    //arrange
    List<String> list = new ArrayList<String>();
    list.add("Tacos");
    list.add("Lembas");
    list.add("Beans");

    //act
    String result = HpsStringUtils.join(list, ',');

    //assert
    assertEquals(result, "Tacos,Lembas,Beans");
  }
}
