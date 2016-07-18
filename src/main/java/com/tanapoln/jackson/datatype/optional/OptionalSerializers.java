package com.tanapoln.jackson.datatype.optional;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.tanapoln.Optional;

class OptionalSerializers extends Serializers.Base
{
    @Override
    public JsonSerializer<?> findReferenceSerializer(SerializationConfig config, 
            ReferenceType refType, BeanDescription beanDesc,
            TypeSerializer contentTypeSerializer, JsonSerializer<Object> contentValueSerializer)
    {
        final Class<?> raw = refType.getRawClass();
        if (Optional.class.isAssignableFrom(raw)) {
            return new OptionalSerializer(refType, contentTypeSerializer, contentValueSerializer);
        }
        return null;
    }
}
