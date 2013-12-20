
/**
 * MaterialsImplServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package wtp.materials.src.service;

    /**
     *  MaterialsImplServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MaterialsImplServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MaterialsImplServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MaterialsImplServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerRecursos method
            * override this method for handling normal response from obtenerRecursos operation
            */
           public void receiveResultobtenerRecursos(
                    MaterialsImplServiceStub.ObtenerRecursosResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRecursos operation
           */
            public void receiveErrorobtenerRecursos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setArchivo method
            * override this method for handling normal response from setArchivo operation
            */
           public void receiveResultsetArchivo(
                    MaterialsImplServiceStub.SetArchivoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setArchivo operation
           */
            public void receiveErrorsetArchivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEncuesta method
            * override this method for handling normal response from getEncuesta operation
            */
           public void receiveResultgetEncuesta(
                    MaterialsImplServiceStub.GetEncuestaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEncuesta operation
           */
            public void receiveErrorgetEncuesta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEncuestaRespondida method
            * override this method for handling normal response from getEncuestaRespondida operation
            */
           public void receiveResultgetEncuestaRespondida(
                    MaterialsImplServiceStub.GetEncuestaRespondidaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEncuestaRespondida operation
           */
            public void receiveErrorgetEncuestaRespondida(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarLink method
            * override this method for handling normal response from agregarLink operation
            */
           public void receiveResultagregarLink(
                    MaterialsImplServiceStub.AgregarLinkResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarLink operation
           */
            public void receiveErroragregarLink(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarEncuestaRespondida method
            * override this method for handling normal response from agregarEncuestaRespondida operation
            */
           public void receiveResultagregarEncuestaRespondida(
                    MaterialsImplServiceStub.AgregarEncuestaRespondidaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarEncuestaRespondida operation
           */
            public void receiveErroragregarEncuestaRespondida(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarEncuesta method
            * override this method for handling normal response from agregarEncuesta operation
            */
           public void receiveResultagregarEncuesta(
                    MaterialsImplServiceStub.AgregarEncuestaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarEncuesta operation
           */
            public void receiveErroragregarEncuesta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sayHello method
            * override this method for handling normal response from sayHello operation
            */
           public void receiveResultsayHello(
                    MaterialsImplServiceStub.SayHelloResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sayHello operation
           */
            public void receiveErrorsayHello(java.lang.Exception e) {
            }
                


    }
    