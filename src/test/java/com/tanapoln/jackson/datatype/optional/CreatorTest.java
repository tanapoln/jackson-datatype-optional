package com.tanapoln.jackson.datatype.optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanapoln.Optional;

public class CreatorTest extends ModuleTestBase
{
    static class CreatorWithOptionalStrings
    {
        Optional<String> a, b;

        // note: something weird with test setup, should not need annotations
        @JsonCreator
        public CreatorWithOptionalStrings(@JsonProperty("a") Optional<String> a,
                @JsonProperty("b") Optional<String> b)
        {
            this.a = a;
            this.b = b;
        }
    }

    /*
    /**********************************************************
    /* Test methods
    /**********************************************************
     */

    private final ObjectMapper MAPPER = mapperWithModule();

    /**
     * Test to ensure that creator parameters use defaulting
     * (introduced in Jackson 2.6)
     */
    public void testCreatorWithOptional() throws Exception
    {
        CreatorWithOptionalStrings bean = MAPPER.readValue(
                aposToQuotes("{'a':'foo'}"), CreatorWithOptionalStrings.class);
        assertNotNull(bean);
        assertNotNull(bean.a);
        assertNotNull(bean.b);
        assertTrue(bean.a.isPresent());
        assertFalse(bean.b.isPresent());
        assertEquals("foo", bean.a.get());
    }
}
