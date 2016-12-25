package com.deneme1.erel.kredikartbasvuru.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebServiceCallerImpl implements WebServiceCaller{
    private static final String METHOD_NAME = "kontrol";
    private static final String NAMESPACE = "http://212.58.23.165/";
    private static final String SOAP_ACTION = "http://212.58.23.165/kontrol";
    private static final String URL = "http://212.58.23.165/kontrol/Service1.asmx";

    @Override
    public String getMessage(ControlInput input){

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("maas",input.getMaas());
        request.addProperty("yas",input.getYas());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.encodingStyle = SoapEnvelope.ENC;
        envelope.setAddAdornments(false);
        envelope.implicitTypes = false;

        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION,envelope);
            if(envelope.bodyIn instanceof SoapObject){
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                MessageResult messageresult = new MessageResult();
                messageresult.message = response.toString();
                return messageresult.message;
            }
            else if(envelope.bodyIn instanceof SoapFault){
                SoapFault soapfault = (SoapFault) envelope.bodyIn;
                throw new Exception(soapfault.getMessage());
            }
            else
                return "deneme1";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
