
/**
 * ActividadRemoteExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package wtp.activity.fiuba.taller.actividad;

public class ActividadRemoteExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1392588515296L;
    
    private ActividadStub.ActividadRemoteException faultMessage;

    
        public ActividadRemoteExceptionException() {
            super("ActividadRemoteExceptionException");
        }

        public ActividadRemoteExceptionException(java.lang.String s) {
           super(s);
        }

        public ActividadRemoteExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ActividadRemoteExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ActividadStub.ActividadRemoteException msg){
       faultMessage = msg;
    }
    
    public ActividadStub.ActividadRemoteException getFaultMessage(){
       return faultMessage;
    }
}
    