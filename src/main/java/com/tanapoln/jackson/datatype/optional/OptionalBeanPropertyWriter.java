package com.tanapoln.jackson.datatype.optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.tanapoln.Optional;

public class OptionalBeanPropertyWriter extends BeanPropertyWriter
{
    private static final long serialVersionUID = 1L;

    protected OptionalBeanPropertyWriter(BeanPropertyWriter base) {
        super(base);
    }

    protected OptionalBeanPropertyWriter(BeanPropertyWriter base, PropertyName newName) {
        super(base, newName);
    }

    @Override
    protected BeanPropertyWriter _new(PropertyName newName) {
        return new OptionalBeanPropertyWriter(this, newName);
    }

    @Override
    public BeanPropertyWriter unwrappingWriter(NameTransformer unwrapper) {
        return new UnwrappingOptionalBeanPropertyWriter(this, unwrapper);
    }

    @Override
    public void serializeAsField(Object bean, JsonGenerator jgen, SerializerProvider prov) throws Exception
    {
        Optional<Object> value = (Optional<Object>) get(bean);
        JsonInclude.Include inclusion = prov.getConfig().getDefaultPropertyInclusion().getValueInclusion();
        if (inclusion == JsonInclude.Include.ALWAYS) {
            super.serializeAsField(bean, jgen, prov);
            return;
        }
        if (inclusion == JsonInclude.Include.NON_NULL) {
            if (value == null || value.isPresent() == false || value.get() == null)
            return;
        }
        if (value == null || Optional.absent().equals(value)) {
            return;
        }
        super.serializeAsField(bean, jgen, prov);
    }

}
