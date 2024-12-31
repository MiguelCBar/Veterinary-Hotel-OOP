package hva.searchpredicate;

@FunctionalInterface
public interface SearchPredicate<T> {
    boolean test(T t);
}