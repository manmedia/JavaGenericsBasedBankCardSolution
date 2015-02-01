package com.mysolutions.creditcardrecords;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mysolutions.creditcardrecords.BankCardProviderNames;
import com.mysolutions.creditcardrecords.CompositeKeyImplementer;

public class CompositeKeyImplementerTest {

	List<CompositeKeyImplementer<String, String>> list;

	@Before
	public void setup() {
		list = new ArrayList<CompositeKeyImplementer<String, String>>();

		list.add(new CompositeKeyImplementer<String, String>(
				"5601-2345-3446-5678", "HSBC Canada"));
		list.add(new CompositeKeyImplementer<String, String>(
				"4519-4532-4524-2456", "Royal Bank of Canada"));
		list.add(new CompositeKeyImplementer<String, String>(
				"3786-7334-8695-345", "American Express"));

	}

	@Test
	public void Test_CompositeKeyImplementer_Has_All_Pairs() {
		assertEquals(3, list.size());
	}

	@Test
	public void Test_CompositeKeyImplementer_Many_To_One_Key_Relationship() {
		list.add(new CompositeKeyImplementer<String, String>(
				"5601-2325-3446-5678", "HSBC Canada"));

		assertEquals(4, list.size());

	}

	@Test(expected = IllegalArgumentException.class)
	public void Test_CompositeKeyImplementer_Many_Throws_IAE_If_BothKeys_AreEqual() {
		list.add(new CompositeKeyImplementer<String, String>("HSBC Canada",
				"HSBC Canada"));
	}

	@Test
	public void Test_CompositeKeyImplementer_Works_With_String_and_Enums_Keys_Identically() {
		CompositeKeyImplementer<String, BankCardProviderNames> testObj1 = new CompositeKeyImplementer<String, BankCardProviderNames>(
				"5601-2325-3446-5678", BankCardProviderNames.HSBC_Canada);

		CompositeKeyImplementer<String, BankCardProviderNames> testObj2 = new CompositeKeyImplementer<String, BankCardProviderNames>(
				"5601-2325-3446-5678", BankCardProviderNames.HSBC_Canada);

		CompositeKeyImplementer<String, BankCardProviderNames> testObj3 = new CompositeKeyImplementer<String, BankCardProviderNames>(
				"3786-7334-8695-345", BankCardProviderNames.American_Express);

		assertEquals(true, testObj1.equals(testObj2));
		assertEquals(false, testObj1.equals(testObj3));
	}

	@Test
	public void Test_CompositeKeyImplementer_Assesses_Equality_Correctly_With_Both_String_Types() {
		CompositeKeyImplementer<String, String> pair1 = new CompositeKeyImplementer<String, String>(
				"5601-2325-3446-5678", "HSBC Canada");

		CompositeKeyImplementer<String, String> pair2 = new CompositeKeyImplementer<String, String>(pair1);

		assertEquals(true, pair1.equals(pair2));

	}
	
	@Test
	public void Test_CompositeKeyImplementer_Assesses_Equality_Correctly_With_Both_Enum_Types() {
		CompositeKeyImplementer<String, BankCardProviderNames> pair1 = new CompositeKeyImplementer<String, BankCardProviderNames>(
				"5601-2325-3446-5678", BankCardProviderNames.HSBC_Canada);

		CompositeKeyImplementer<String, BankCardProviderNames> pair2 = new CompositeKeyImplementer<String, BankCardProviderNames>(pair1);

		assertEquals(true, pair1.equals(pair2));

	}	

	@Test
	public void Test_CompositeKeyImplementer_Can_Locate_Records_From_Collection_Using_Given_Key() {
		
		CompositeKeyImplementer<String, String> pair1 = new CompositeKeyImplementer<String, String>(
				"5601-2345-3446-5678", "HSBC Canada");
		
		assertTrue(list.contains(pair1));
		
	}
	
	@Test
	public void Test_CompositeKeyImplementer_Can_Remove_A_Record_From_Collection_Using_Given_Key() {
		
		CompositeKeyImplementer<String, String> pair1 = new CompositeKeyImplementer<String, String>(
				"5601-2345-3446-5678", "HSBC Canada");
		
		assertTrue(list.remove(pair1));		
		
	}	
}
