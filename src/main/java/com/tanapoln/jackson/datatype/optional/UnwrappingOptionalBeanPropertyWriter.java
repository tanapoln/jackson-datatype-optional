package com.tanapoln.jackson.datatype.optional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.tanapoln.Optional;

public class UnwrappingOptionalBeanPropertyWriter extends UnwrappingBeanPropertyWriter
{
    private static final long serialVersionUID = 1L;

    public UnwrappingOptionalBeanPropertyWriter(BeanPropertyWriter base,
                                                NameTransformer transformer) {
        super(base, transformer);
    }

    protected UnwrappingOptionalBeanPropertyWriter(UnwrappingBeanPropertyWriter base,
                                                   NameTransformer transformer, SerializedString name) {
        super(base, transformer, name);
    }

    @Override
    protected UnwrappingBeanPropertyWriter _new(NameTransformer transformer, SerializedString newName)
    {
        return new UnwrappingOptionalBeanPropertyWriter(this, transformer, newName);
    }

    @Override
    public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception
    {
        Optional<Object> value = (Optional<Object>) get(bean);
        if (value == null || Optional.absent().equals(value) || value.get() == null) {
            return;
        }
        super.serializeAsField(bean, gen, prov);
    }
}
