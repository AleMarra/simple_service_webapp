
/**
 * MaterialsImplServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package service;

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
            * auto generated Axis2 call back method for getRecursos method
            * override this method for handling normal response from getRecursos operation
            */
           public void receiveResultgetRecursos(
                    service.MaterialsImplServiceStub.GetRecursosResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecursos operation
           */
            public void receiveErrorgetRecursos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEncuestaRespondida method
            * override this method for handling normal response from getEncuestaRespondida operation
            */
           public void receiveResultgetEncuestaRespondida(
                    service.MaterialsImplServiceStub.GetEncuestaRespondidaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEncuestaRespondida operation
           */
            public void receiveErrorgetEncuestaRespondida(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for borrarRecurso method
            * override this method for handling normal response from borrarRecurso operation
            */
           public void receiveResultborrarRecurso(
                    service.MaterialsImplServiceStub.BorrarRecursoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from borrarRecurso operation
           */
            public void receiveErrorborrarRecurso(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarEncuestaRespondida method
            * override this method for handling normal response from agregarEncuestaRespondida operation
            */
           public void receiveResultagregarEncuestaRespondida(
                    service.MaterialsImplServiceStub.AgregarEncuestaRespondidaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarEncuestaRespondida operation
           */
            public void receiveErroragregarEncuestaRespondida(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarRecurso method
            * override this method for handling normal response from agregarRecurso operation
            */
           public void receiveResultagregarRecurso(
                    service.MaterialsImplServiceStub.AgregarRecursoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarRecurso operation
           */
            public void receiveErroragregarRecurso(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sayHello method
            * override this method for handling normal response from sayHello operation
            */
           public void receiveResultsayHello(
                    service.MaterialsImplServiceStub.SayHelloResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sayHello operation
           */
            public void receiveErrorsayHello(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarArchivo method
            * override this method for handling normal response from agregarArchivo operation
            */
           public void receiveResultagregarArchivo(
                    service.MaterialsImplServiceStub.AgregarArchivoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarArchivo operation
           */
            public void receiveErroragregarArchivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecurso method
            * override this method for handling normal response from getRecurso operation
            */
           public void receiveResultgetRecurso(
                    service.MaterialsImplServiceStub.GetRecursoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecurso operation
           */
            public void receiveErrorgetRecurso(java.lang.Exception e) {
            }
                


    }
    