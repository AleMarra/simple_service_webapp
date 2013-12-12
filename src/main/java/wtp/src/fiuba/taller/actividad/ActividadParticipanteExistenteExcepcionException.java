
/**
 * ActividadParticipanteExistenteExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package fiuba.taller.actividad;

public class ActividadParticipanteExistenteExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1386889259547L;
    
    private fiuba.taller.actividad.ActividadStub.ActividadParticipanteExistenteExcepcion faultMessage;

    
        public ActividadParticipanteExistenteExcepcionException() {
            super("ActividadParticipanteExistenteExcepcionException");
        }

        public ActividadParticipanteExistenteExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadParticipanteExistenteExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadParticipanteExistenteExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(fiuba.taller.actividad.ActividadStub.ActividadParticipanteExistenteExcepcion msg){
       faultMessage = msg;
    }
    
    public fiuba.taller.actividad.ActividadStub.ActividadParticipanteExistenteExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    