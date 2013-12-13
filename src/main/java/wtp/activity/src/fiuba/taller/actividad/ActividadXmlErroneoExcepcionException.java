
/**
 * ActividadXmlErroneoExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package wtp.activity.src.fiuba.taller.actividad;

public class ActividadXmlErroneoExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1386934718078L;
    
    private wtp.activity.src.fiuba.taller.actividad.ActividadStub.ActividadXmlErroneoExcepcion faultMessage;

    
        public ActividadXmlErroneoExcepcionException() {
            super("ActividadXmlErroneoExcepcionException");
        }

        public ActividadXmlErroneoExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadXmlErroneoExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadXmlErroneoExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(wtp.activity.src.fiuba.taller.actividad.ActividadStub.ActividadXmlErroneoExcepcion msg){
       faultMessage = msg;
    }
    
    public wtp.activity.src.fiuba.taller.actividad.ActividadStub.ActividadXmlErroneoExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    