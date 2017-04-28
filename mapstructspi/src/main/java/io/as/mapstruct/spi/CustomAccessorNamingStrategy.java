package io.as.mapstruct.spi;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.beans.Introspector;

public class CustomAccessorNamingStrategy extends DefaultAccessorNamingStrategy
{
    @Override
    public boolean isSetterMethod(ExecutableElement method)
    {
        Element element = method.getEnclosingElement();
        String name = element.getSimpleName().toString();
        return super.isSetterMethod(method) || name.endsWith("Builder");
    }

    @Override
    public String getPropertyName(ExecutableElement getterOrSetterMethod)
    {
        String methodName = getterOrSetterMethod.getSimpleName().toString();
        int beginIndex;

        if (methodName.startsWith("is"))
        {
            beginIndex = 2;
        }
        else if (methodName.startsWith("get") || methodName.startsWith("set"))
        {
            beginIndex = 3;
        } else
        {
            beginIndex = 0;
        }

        return Introspector.decapitalize(methodName.substring(beginIndex));
    }
}
