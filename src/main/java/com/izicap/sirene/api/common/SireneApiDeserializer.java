package com.izicap.sirene.api.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.izicap.sirene.api.model.Company;
import org.springframework.beans.factory.annotation.Value;

/**
 * Custom Deserializer not used (just to showcase another way of deserializing custom responses)
 */
import java.io.IOException;

public class SireneApiDeserializer extends StdDeserializer<Company> {

    @Value("${izicap.sirene.api.parent-node-name}")
    private static String parentNode;

    protected SireneApiDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Company deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        Company company = new Company();
        company.setId(Long.valueOf(jsonNode.get(parentNode).get("id").textValue()));
        company.setNic(jsonNode.get(parentNode).get("nic").textValue());
        // same could be done for other fields
        return company;
    }
}
