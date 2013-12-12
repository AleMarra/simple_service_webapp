
/**
 * ActividadTransformerExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package fiuba.taller.actividad;

public class ActividadTransformerExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1386889259536L;
    
    private fiuba.taller.actividad.ActividadStub.ActividadTransformerException faultMessage;

    
        public ActividadTransformerExceptionException() {
            super("ActividadTransformerExceptionException");
        }

        public ActividadTransformerExceptionException(java.lang.String s) {
           super(s);
        }

        public ActividadTransformerExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadTransformerExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(fiuba.taller.actividad.ActividadStub.ActividadTransformerException msg){
       faultMessage = msg;
    }
    
    public fiuba.taller.actividad.ActividadStub.ActividadTransformerException getFaultMessage(){
       return faultMessage;
    }
}
    