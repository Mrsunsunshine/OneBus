package com.onebus.test;

import org.junit.Test;

import com.onebus.beans.Bus;

public class TestBus {

	@Test
	public void test() {
		Bus bus = new Bus();
		bus.setBusType("123");
		System.out.println(bus.getBusType());
	}

}
