
/**
 * ActividadControladorXmlErroneoExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package wtp.activity.src.fiuba.taller.actividad;

public class ActividadControladorXmlErroneoExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1387044507437L;
    
    private ActividadControladorStub.ActividadControladorXmlErroneoExcepcion faultMessage;

    
        public ActividadControladorXmlErroneoExcepcionException() {
            super("ActividadControladorXmlErroneoExcepcionException");
        }

        public ActividadControladorXmlErroneoExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadControladorXmlErroneoExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadControladorXmlErroneoExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ActividadControladorStub.ActividadControladorXmlErroneoExcepcion msg){
       faultMessage = msg;
    }
    
    public ActividadControladorStub.ActividadControladorXmlErroneoExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    