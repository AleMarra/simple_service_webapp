
/**
 * ActividadControladorParticipanteExistenteExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package wtp.activity.src.fiuba.taller.actividad;

public class ActividadControladorParticipanteExistenteExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1387044507418L;
    
    private ActividadControladorStub.ActividadControladorParticipanteExistenteExcepcion faultMessage;

    
        public ActividadControladorParticipanteExistenteExcepcionException() {
            super("ActividadControladorParticipanteExistenteExcepcionException");
        }

        public ActividadControladorParticipanteExistenteExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadControladorParticipanteExistenteExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadControladorParticipanteExistenteExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ActividadControladorStub.ActividadControladorParticipanteExistenteExcepcion msg){
       faultMessage = msg;
    }
    
    public ActividadControladorStub.ActividadControladorParticipanteExistenteExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    