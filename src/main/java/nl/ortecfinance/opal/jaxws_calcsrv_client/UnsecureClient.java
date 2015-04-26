package nl.ortecfinance.opal.jaxws_calcsrv_client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import nl.Calculator;
import nl.Calculator_Service;
import weblogic.wsee.security.unt.ClientUNTCredentialProvider;
import weblogic.xml.crypto.wss.WSSecurityContext;
import weblogic.xml.crypto.wss.provider.CredentialProvider;

public class UnsecureClient {

    public static void main(String[] args) {

        Calculator_Service calcSrv = new Calculator_Service();

        Calculator calculator = calcSrv.getCalculatorPort();
        Map requestContext = ((BindingProvider) calculator).getRequestContext();
        final String USERNAME = "ServiceTest1";
        final String PASSWORD = USERNAME;
        requestContext.put(BindingProvider.USERNAME_PROPERTY, USERNAME);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, USERNAME);
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/JAXWS_CalcService/Calculator");

        List<CredentialProvider> credProviders = new ArrayList<>();
        credProviders.add(new ClientUNTCredentialProvider(USERNAME.getBytes(), PASSWORD.getBytes()));
        requestContext.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
        final Calculator calculatorPort = calcSrv.getCalculatorPort();

        int result = calculatorPort.add(1, 3);

        System.out.println("result is " + result);
    }
}
