
/**
 * ActividadControladorNotaInexistenteExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package wtp.activity.src.fiuba.taller.actividad;

public class ActividadControladorNotaInexistenteExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1387044507446L;
    
    private ActividadControladorStub.ActividadControladorNotaInexistenteExcepcion faultMessage;

    
        public ActividadControladorNotaInexistenteExcepcionException() {
            super("ActividadControladorNotaInexistenteExcepcionException");
        }

        public ActividadControladorNotaInexistenteExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadControladorNotaInexistenteExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadControladorNotaInexistenteExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ActividadControladorStub.ActividadControladorNotaInexistenteExcepcion msg){
       faultMessage = msg;
    }
    
    public ActividadControladorStub.ActividadControladorNotaInexistenteExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    