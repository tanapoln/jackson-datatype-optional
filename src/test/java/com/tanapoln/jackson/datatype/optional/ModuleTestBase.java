package com.tanapoln.jackson.datatype.optional;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ModuleTestBase extends junit.framework.TestCase
{
    /*
    /**********************************************************************
    /* Helper methods, setup
    /**********************************************************************
     */
    
    protected ObjectMapper mapperWithModule()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new OptionalModule());
        return mapper;
    }

    protected ObjectMapper mapperWithModule(boolean absentsAsNulls)
    {
        ObjectMapper mapper = new ObjectMapper();
        OptionalModule module = new OptionalModule();
        mapper.registerModule(module);
        return mapper;
    }
    
    /*
    /**********************************************************************
    /* Helper methods, setup
    /**********************************************************************
     */

    protected void verifyException(Throwable e, String... matches)
    {
        String msg = e.getMessage();
        String lmsg = (msg == null) ? "" : msg.toLowerCase();
        for (String match : matches) {
            String lmatch = match.toLowerCase();
            if (lmsg.indexOf(lmatch) >= 0) {
                return;
            }
        }
        fail("Expected an exception with one of substrings ("+Arrays.asList(matches)+"): got one with message \""+msg+"\"");
    }

    protected String quote(String str) {
        return '"'+str+'"';
    }

    protected String aposToQuotes(String json) {
        return json.replace("'", "\"");
    }
}
