package com.tanapoln.jackson.datatype.optional;

import com.tanapoln.Optional;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;

class OptionalDeserializers extends Deserializers.Base
{
    // 21-Oct-2015, tatu: Code much simplified with 2.7 where we should be getting much
    //    of boilerplate handling automatically

    @Override // since 2.7
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType refType,
            DeserializationConfig config, BeanDescription beanDesc,
            TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer)
    {
        if (refType.hasRawClass(Optional.class)) {
            return new OptionalDeserializer(refType, contentTypeDeserializer,contentDeserializer);
        }
        // 21-Oct-2015, tatu: Should probably consider possibility of custom deserializer being
        //    added to property; if so, `contentDeserializer` would not be null.
        //    Room for future improvement
        
        return null;
    }
}
