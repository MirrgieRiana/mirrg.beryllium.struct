package mirrg.beryllium.struct.suppliterator;

import static mirrg.beryllium.struct.suppliterator.ISuppliterator.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class TestSuppliterator
{

	@Test
	public void test()
	{
		Function<String, ISuppliterator<Integer>> f = s -> of(s.chars()
			.mapToObj(i -> i)
			.collect(Collectors.toCollection(ArrayList::new)).iterator());

		ISuppliterator<Number> flatMap = cast(concat(

			of(

				"ee",
				"654",
				"yut436y3"

			)
				.filter(s -> s.length() != 3),

			of(

				"q"

			)

		)
			.flatMap(f));

		ArrayList<Number> collection = flatMap
			.toCollection(() -> new ArrayList<Number>());

		assertEquals(Stream.of(
			(int) 'e',
			(int) 'e',
			(int) 'y',
			(int) 'u',
			(int) 't',
			(int) '4',
			(int) '3',
			(int) '6',
			(int) 'y',
			(int) '3',
			(int) 'q').collect(Collectors.toCollection(ArrayList::new)),
			collection);
	}

	@Test
	public void test2()
	{
		aAE(range(5, 11), 5, 6, 7, 8, 9, 10);
		aAE(range(5, 11).skip(2), 7, 8, 9, 10);
		aAE(range(5, 11).limit(2), 5, 6);
		aAE(range(5, 11).mid(2, 3), 7, 8, 9);
		aAE(range(5, 11).map(i -> i * 2), 10, 12, 14, 16, 18, 20);
		aAE(range(5, 11).filter(i -> i % 2 == 0), 6, 8, 10);
		aAE(range(5, 11).reverse(), 10, 9, 8, 7, 6, 5);
		aAE(of(6, 4, 7, 2, 2, 12, 6, 8, 7).sorted(Comparable::compareTo), 2, 2, 4, 6, 6, 7, 7, 8, 12);

		assertEquals((int) range(5, 11).filter(i -> i % 3 == 0).find().get(), 6);
		assertEquals("5<>6<>7<>8<>9<>10", range(5, 11).join("<>"));
		assertEquals("5678910", range(5, 11).join());
		assertEquals("5<>6<>7<>8<>9<>10", range(5, 11).map(i -> "" + i).collect(Collectors.joining("<>")));
		assertEquals(6L, (long) range(5, 11).collect(Collectors.counting()));
		assertEquals(6, range(5, 11).count());
	}

	private void aAE(ISuppliterator<Integer> actual, int... expected)
	{
		assertArrayEquals(expected, actual.toIntArray(i -> i));
	}

}
