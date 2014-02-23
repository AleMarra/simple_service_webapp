
/**
 * ServicioTablaServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package wtp;

    /**
     *  ServicioTablaServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServicioTablaServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServicioTablaServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServicioTablaServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for agregarCampo method
            * override this method for handling normal response from agregarCampo operation
            */
           public void receiveResultagregarCampo(
                    wtp.ServicioTablaServiceStub.AgregarCampoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarCampo operation
           */
            public void receiveErroragregarCampo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearTabla method
            * override this method for handling normal response from crearTabla operation
            */
           public void receiveResultcrearTabla(
                    wtp.ServicioTablaServiceStub.CrearTablaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearTabla operation
           */
            public void receiveErrorcrearTabla(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modificarCampo method
            * override this method for handling normal response from modificarCampo operation
            */
           public void receiveResultmodificarCampo(
                    wtp.ServicioTablaServiceStub.ModificarCampoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modificarCampo operation
           */
            public void receiveErrormodificarCampo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerTabla method
            * override this method for handling normal response from obtenerTabla operation
            */
           public void receiveResultobtenerTabla(
                    wtp.ServicioTablaServiceStub.ObtenerTablaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerTabla operation
           */
            public void receiveErrorobtenerTabla(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarTabla method
            * override this method for handling normal response from eliminarTabla operation
            */
           public void receiveResulteliminarTabla(
                    wtp.ServicioTablaServiceStub.EliminarTablaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarTabla operation
           */
            public void receiveErroreliminarTabla(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerFilas method
            * override this method for handling normal response from obtenerFilas operation
            */
           public void receiveResultobtenerFilas(
                    wtp.ServicioTablaServiceStub.ObtenerFilasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerFilas operation
           */
            public void receiveErrorobtenerFilas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerNombresColumnas method
            * override this method for handling normal response from obtenerNombresColumnas operation
            */
           public void receiveResultobtenerNombresColumnas(
                    wtp.ServicioTablaServiceStub.ObtenerNombresColumnasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerNombresColumnas operation
           */
            public void receiveErrorobtenerNombresColumnas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearFila method
            * override this method for handling normal response from crearFila operation
            */
           public void receiveResultcrearFila(
                    wtp.ServicioTablaServiceStub.CrearFilaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearFila operation
           */
            public void receiveErrorcrearFila(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarFila method
            * override this method for handling normal response from eliminarFila operation
            */
           public void receiveResulteliminarFila(
                    wtp.ServicioTablaServiceStub.EliminarFilaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarFila operation
           */
            public void receiveErroreliminarFila(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modificarFila method
            * override this method for handling normal response from modificarFila operation
            */
           public void receiveResultmodificarFila(
                    wtp.ServicioTablaServiceStub.ModificarFilaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modificarFila operation
           */
            public void receiveErrormodificarFila(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCampo method
            * override this method for handling normal response from eliminarCampo operation
            */
           public void receiveResulteliminarCampo(
                    wtp.ServicioTablaServiceStub.EliminarCampoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCampo operation
           */
            public void receiveErroreliminarCampo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerTiposDeCampos method
            * override this method for handling normal response from obtenerTiposDeCampos operation
            */
           public void receiveResultobtenerTiposDeCampos(
                    wtp.ServicioTablaServiceStub.ObtenerTiposDeCamposResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerTiposDeCampos operation
           */
            public void receiveErrorobtenerTiposDeCampos(java.lang.Exception e) {
            }
                


    }
    