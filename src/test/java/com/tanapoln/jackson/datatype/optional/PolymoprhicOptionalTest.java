package com.tanapoln.jackson.datatype.optional;

import com.tanapoln.Optional;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PolymoprhicOptionalTest extends ModuleTestBase
{
    // For [datatype-optional#14]
    public static class Container {
        public Optional<Contained> contained;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY)
    @JsonSubTypes({
        @JsonSubTypes.Type(name = "ContainedImpl", value = ContainedImpl.class),
    })
    public static interface Contained { }

    public static class ContainedImpl implements Contained { }

    private final ObjectMapper MAPPER = mapperWithModule();
    
    // [datatype-optional#14]
    public void testPolymorphic14() throws Exception
    {
        final Container dto = new Container();
        dto.contained = Optional.of((Contained) new ContainedImpl());
        
        final String json = MAPPER.writeValueAsString(dto);

        final Container fromJson = MAPPER.readValue(json, Container.class);
        assertNotNull(fromJson.contained);
        assertTrue(fromJson.contained.isPresent());
        assertSame(ContainedImpl.class, fromJson.contained.get().getClass());
    }
}
