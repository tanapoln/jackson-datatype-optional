package com.tanapoln.jackson.datatype.optional;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.tanapoln.Optional;

import java.util.List;

/**
 * {@link BeanSerializerModifier} needed to sneak in handler to exclude "absent"
 * optional values iff handling of "absent as nulls" is enabled.
 */
public class OptionalBeanSerializerModifier extends BeanSerializerModifier
{
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
            BeanDescription beanDesc,
            List<BeanPropertyWriter> beanProperties)
    {
        List<BeanPropertyDefinition> properties = beanDesc.findProperties();
        for (int i = 0; i < beanProperties.size(); ++i) {
            final BeanPropertyWriter writer = beanProperties.get(i);
            JavaType type = writer.getType();
            if (type.isTypeOrSubTypeOf(Optional.class)) {
                if (writer instanceof UnwrappingBeanPropertyWriter) {
                    UnwrappingBeanPropertyWriter unwrapWriter = (UnwrappingBeanPropertyWriter) writer;
                    AnnotatedMember accessor = properties.get(i).getAccessor();
                    NameTransformer nameTransformer = config.getAnnotationIntrospector().findUnwrappingNameTransformer(accessor);
                    beanProperties.set(i, new UnwrappingOptionalBeanPropertyWriter(unwrapWriter, nameTransformer));
                }
                else {
                    beanProperties.set(i, new OptionalBeanPropertyWriter(writer));
                }
            }
        }
        return beanProperties;
    }

    @Override
    public BeanSerializerBuilder updateBuilder(SerializationConfig config, BeanDescription beanDesc, BeanSerializerBuilder builder) {
        return super.updateBuilder(config, beanDesc, builder);
    }
}
