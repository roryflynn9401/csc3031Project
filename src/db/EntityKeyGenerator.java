package db;

public enum EntityKeyGenerator {
    ENGINE;

    private int nextKey;
    synchronized int getNextKey() {
        return ++nextKey;
    }
}
