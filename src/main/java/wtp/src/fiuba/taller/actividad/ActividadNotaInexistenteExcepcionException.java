
/**
 * ActividadNotaInexistenteExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package fiuba.taller.actividad;

public class ActividadNotaInexistenteExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1386889259556L;
    
    private fiuba.taller.actividad.ActividadStub.ActividadNotaInexistenteExcepcion faultMessage;

    
        public ActividadNotaInexistenteExcepcionException() {
            super("ActividadNotaInexistenteExcepcionException");
        }

        public ActividadNotaInexistenteExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadNotaInexistenteExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadNotaInexistenteExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(fiuba.taller.actividad.ActividadStub.ActividadNotaInexistenteExcepcion msg){
       faultMessage = msg;
    }
    
    public fiuba.taller.actividad.ActividadStub.ActividadNotaInexistenteExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    