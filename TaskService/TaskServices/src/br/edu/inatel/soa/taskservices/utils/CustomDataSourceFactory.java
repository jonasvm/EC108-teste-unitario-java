package br.edu.inatel.soa.taskservices.utils;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

public class CustomDataSourceFactory extends BasicDataSourceFactory implements ObjectFactory {

    private static final Pattern _propRefPattern = Pattern.compile("\\$\\{.*?\\}");
       
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable environment) throws Exception {
        if (obj instanceof Reference) {
            Reference ref = (Reference) obj;
            System.out.println("Resolving context reference values dynamically");

            for(int i = 0; i < ref.size(); i++) {
                RefAddr addr = ref.get(i);
                String tag = addr.getType();
                String value = (String) addr.getContent();

                Matcher matcher = _propRefPattern.matcher(value);
                if (matcher.find()) {
                    String resolvedValue = resolve(value.replaceAll("[${}]", ""));
                    System.out.println("Resolved " + value);
                    ref.remove(i);
                    ref.add(i, new StringRefAddr(tag, resolvedValue));
                }
            }
        }
        // Return the customized instance
        return super.getObjectInstance(obj, name, nameCtx, environment);
    }

    private String resolve(String key) {
        String value = System.getProperty(key);
        return value;
    }
}
