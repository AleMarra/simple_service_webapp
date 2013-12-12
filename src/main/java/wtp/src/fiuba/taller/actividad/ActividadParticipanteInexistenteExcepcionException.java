
/**
 * ActividadParticipanteInexistenteExcepcionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package fiuba.taller.actividad;

public class ActividadParticipanteInexistenteExcepcionException extends java.lang.Exception{

    private static final long serialVersionUID = 1386889259584L;
    
    private fiuba.taller.actividad.ActividadStub.ActividadParticipanteInexistenteExcepcion faultMessage;

    
        public ActividadParticipanteInexistenteExcepcionException() {
            super("ActividadParticipanteInexistenteExcepcionException");
        }

        public ActividadParticipanteInexistenteExcepcionException(java.lang.String s) {
           super(s);
        }

        public ActividadParticipanteInexistenteExcepcionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadParticipanteInexistenteExcepcionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(fiuba.taller.actividad.ActividadStub.ActividadParticipanteInexistenteExcepcion msg){
       faultMessage = msg;
    }
    
    public fiuba.taller.actividad.ActividadStub.ActividadParticipanteInexistenteExcepcion getFaultMessage(){
       return faultMessage;
    }
}
    