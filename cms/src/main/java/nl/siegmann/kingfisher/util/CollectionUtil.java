package nl.siegmann.kingfisher.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CollectionUtil {

	public static <T> Map<String, T> singletonMap(String key, T value) {
		Map<String, T> result = new HashMap<String, T>();
		result.put(key, value);
		return result;
	}
	
	public static <S, T> Map<String, Optional<T>> convertToMap(String key, Optional<S> source, Function<S, T> converter) {
		return singletonMap(key, convert(source, converter));
	}
	
	
	public static <S, T> Optional<T> convert(Optional<S> source, Function<S, T> converter) {
		if (source == null || !source.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(converter.apply(source.get()));
	}

	public static <S, T> Map<String, Iterable<T>> convertToMap(String key, Iterable<S> source, Function<S, T> converter) {
		return singletonMap(key, convert(source, converter));
	}

	public static <S, T> Iterable<T> convert(Iterable<S> source, Function<S, T> converter) {
		return convertToList(source, converter);
	}

	public static <S, T> List<T> convertToList(Iterable<S> source, Function<S, T> converter) {
		return CollectionUtil.toStream(source).map(converter).collect(Collectors.toList());
	}

	public static <S, T> List<T> convertToList(Optional<S> source, Function<S, T> converter) {
		List<T> result = new ArrayList<T>();
		if (source.isPresent()) {
			result.add(converter.apply(source.get()));
		}
		return result;
	}

	public static <T> Stream<T> toStream(Iterable<T> in) {
		if (in == null) {
			return Collections.<T>emptyList().stream();
		}
		return StreamSupport.stream(in.spliterator(), false);
	}

	public static <T> Stream<T> toParallelStream(Iterable<T> in) {
		if (in == null) {
			return Collections.<T>emptyList().stream();
		}
		return StreamSupport.stream(in.spliterator(), true);
	}


}
