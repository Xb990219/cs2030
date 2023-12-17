import java.util.Map;
import java.util.Optional;

public class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final ImmutableMap<String, V> map;

    public KeyableMap(String key) {
        this.key = key;
        this.map = new ImmutableMap<>();
    }

    public KeyableMap(String key, ImmutableMap<String,V> map) {
        this.key = key;
        this.map = map;
    }

    public ImmutableMap<String, V> getMap() {
        return this.map;
    }

    public Optional<V> get(String key) {
        return this.map.get(key);
    }

    public KeyableMap<V> put(V item) {
        ImmutableMap<String, V> newMap = this.map.put(item.getKey(), item);
        return new KeyableMap<V>(this.key, newMap);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        String mapString = "";
        for (Map.Entry<String, V> mapValue : this.map) {
            mapString += mapValue.getValue();
            mapString += ", ";
        }
        if (mapString.endsWith(", ")) {
            mapString = mapString.substring(0,mapString.length() - 2);
        }
        String outputString = "";
        outputString += this.getKey() + ": {" + mapString + "}";
        return outputString;
    }
}
