package it.isd;

public class KeyValue<K, V> {
  public final K key;
  public final V value;

  public KeyValue(K key, V value) {
    this.key = key;
    this.value = value;
  }
}
