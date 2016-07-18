package com.tanapoln.jackson.failing;

import com.tanapoln.Optional;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import com.tanapoln.jackson.datatype.optional.ModuleTestBase;

public class OptionalnclusionTest extends ModuleTestBase
{
    @JsonAutoDetect(fieldVisibility=Visibility.ANY)
    public static final class OptionalData {
        public Optional<String> myString = Optional.empty();
    }

    public void testSerOptNonDefault() throws Exception
    {
//        OptionalData data = new OptionalData();
//        data.myString = null;
//        String value = mapperWithModule().setSerializationInclusion(
//                JsonInclude.Include.NON_DEFAULT).writeValueAsString(data);
//        assertEquals("{}", value);
    }
}
