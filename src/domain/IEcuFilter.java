package domain;
@FunctionalInterface
public interface IEcuFilter {
    boolean filter(Object engine);

    default IEcuFilter combine(IEcuFilter other) {
        return engine -> filter(engine) && other.filter(engine);
    }
}
