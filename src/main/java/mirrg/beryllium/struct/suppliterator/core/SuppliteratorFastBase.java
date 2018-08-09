package mirrg.beryllium.struct.suppliterator.core;

import java.util.Optional;

import mirrg.beryllium.struct.suppliterator.ISuppliterator;

public abstract class SuppliteratorFastBase<T> implements ISuppliterator<T>
{

	public Optional<T> next()
	{
		return Optional.ofNullable(nullableNext());
	}

	public abstract T nullableNext();

}
