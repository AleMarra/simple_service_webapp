
/**
 * ActividadControladorParticipanteInexistenteExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package wtp.activity.src.fiuba.taller.actividad;

public class ActividadControladorParticipanteInexistenteExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1387044507427L;
    
    private ActividadControladorStub.ActividadControladorParticipanteInexistenteExcepcion faultMessage;

    
        public ActividadControladorParticipanteInexistenteExcepcionException() {
            super("ActividadControladorParticipanteInexistenteExcepcionException");
        }

        public ActividadControladorParticipanteInexistenteExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadControladorParticipanteInexistenteExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadControladorParticipanteInexistenteExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ActividadControladorStub.ActividadControladorParticipanteInexistenteExcepcion msg){
       faultMessage = msg;
    }
    
    public ActividadControladorStub.ActividadControladorParticipanteInexistenteExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    