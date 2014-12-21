package com.virginholidays.boot;

import it.codegen.tbx.search.TravelBoxSearch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Anshul Dutta on 20/12/2014.
 */
@Component
    @ManagedResource(
            objectName="bean:name=testBean4",
            description="My Managed Bean",
            log=true,
            logFile="jmx.log",
            currencyTimeLimit=15,
            persistPolicy="OnUpdate",
            persistPeriod=200,
            persistLocation="foo",
            persistName="bar")
public class WebServiceUser {

    //    @ManagedResource(
//            objectName="bean:name=testBean4",
//            description="My Managed Bean",
//            log=true,
//            logFile="jmx.log",
//            currencyTimeLimit=15,
//            persistPolicy="OnUpdate",
//            persistPeriod=200,
//            persistLocation="foo",
//            persistName="bar")
    @Bean(name = "jyotuWebService")
    public JaxWsPortProxyFactoryBean createProxy() {
        JaxWsPortProxyFactoryBean bean = new JaxWsPortProxyFactoryBean();
        try {
            bean.setServiceInterface(TravelBoxSearch.class);
            bean.setWsdlDocumentUrl(new URL("https://tbx-ecom.virginholidays.com:8585/TravelBoxSearch/TravelBoxSearchService?wsdl"));
            bean.setNamespaceUri("http://search.tbx.codegen.it");
            bean.setServiceName("TravelBoxServerService");
            bean.setPortName("TravelBoxServerPort");
            bean.setLookupServiceOnStartup(false);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return bean;
    }

}
