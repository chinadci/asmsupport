package json.parser;

import java.util.Map;
import java.util.Map.Entry;

import json.JSONPool;
import json.utils.StringEncoder;

public class MapParser extends AbstractParser {

    public MapParser(JSONPool jsonPool) {
        super(jsonPool);
    }

    @Override
    public void parse(StringEncoder encoder, Object obj) {
        encoder.appendDirect('{');
        for(Entry<?,?> entry : ((Map<?, ?>)obj).entrySet()) {
            encoder.appendDirect('"').append(entry.getKey()).appendDirect('"').appendDirect(':');
            jsonPool.getJson(encoder, entry.getValue());
            encoder.appendDirect(',');
        }
        encoder.trimLastComma();
        encoder.appendDirect('}');
    }

}
