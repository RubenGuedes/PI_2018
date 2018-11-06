import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DoublyLinkedListTest {

	@Test
	public void testPushPop() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

		list.push(10);
		list.push(20);

		assertThat(list.pop(), is(20));
		assertThat(list.pop(), is(10));
	}

	@Test
	public void testPushShift() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();

		list.push("10");
		list.push("20");

		assertThat(list.shift(), is("10"));
		assertThat(list.shift(), is("20"));
	}

	@Test
	public void testUnshiftShift() {
		DoublyLinkedList<Character> list = new DoublyLinkedList<>();

		list.unshift('1');
		list.unshift('2');

		assertThat(list.shift(), is('2'));
		assertThat(list.shift(), is('1'));
	}

	@Test
	public void testUnshiftPop() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

		list.unshift(10);
		list.unshift(20);

		assertThat(list.pop(), is(10));
		assertThat(list.pop(), is(20));
	}

	@Test
	public void testExample() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();

		list.push("dez");
		list.push("vinte");

		assertThat(list.pop(), is("vinte"));

		list.push("treze");

		assertThat(list.shift(), is("dez"));

		list.unshift("catorze");
		list.push("quinze");

		assertThat(list.shift(), is("catorze"));
		assertThat(list.pop(), is("quinze"));
		assertThat(list.shift(), is("treze"));
	}

}