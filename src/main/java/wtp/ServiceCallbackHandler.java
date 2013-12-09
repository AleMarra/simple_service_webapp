
/**
 * ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package wtp;

    /**
     *  ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for editarSeccion method
            * override this method for handling normal response from editarSeccion operation
            */
           public void receiveResulteditarSeccion(
                    wtp.ServiceStub.EditarSeccionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editarSeccion operation
           */
            public void receiveErroreditarSeccion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarCartelera method
            * override this method for handling normal response from mostrarCartelera operation
            */
           public void receiveResultmostrarCartelera(
                    wtp.ServiceStub.MostrarCarteleraResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarCartelera operation
           */
            public void receiveErrormostrarCartelera(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for busquedaEventosPorPalabras method
            * override this method for handling normal response from busquedaEventosPorPalabras operation
            */
           public void receiveResultbusquedaEventosPorPalabras(
                    wtp.ServiceStub.BusquedaEventosPorPalabrasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from busquedaEventosPorPalabras operation
           */
            public void receiveErrorbusquedaEventosPorPalabras(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarMuro method
            * override this method for handling normal response from mostrarMuro operation
            */
           public void receiveResultmostrarMuro(
                    wtp.ServiceStub.MostrarMuroResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarMuro operation
           */
            public void receiveErrormostrarMuro(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarMensaje method
            * override this method for handling normal response from eliminarMensaje operation
            */
           public void receiveResulteliminarMensaje(
                    wtp.ServiceStub.EliminarMensajeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarMensaje operation
           */
            public void receiveErroreliminarMensaje(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for busquedaMensajesPorAutor method
            * override this method for handling normal response from busquedaMensajesPorAutor operation
            */
           public void receiveResultbusquedaMensajesPorAutor(
                    wtp.ServiceStub.BusquedaMensajesPorAutorResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from busquedaMensajesPorAutor operation
           */
            public void receiveErrorbusquedaMensajesPorAutor(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarTema method
            * override this method for handling normal response from eliminarTema operation
            */
           public void receiveResulteliminarTema(
                    wtp.ServiceStub.EliminarTemaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarTema operation
           */
            public void receiveErroreliminarTema(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarIndice method
            * override this method for handling normal response from mostrarIndice operation
            */
           public void receiveResultmostrarIndice(
                    wtp.ServiceStub.MostrarIndiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarIndice operation
           */
            public void receiveErrormostrarIndice(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for busquedaNoticiasPorPalabras method
            * override this method for handling normal response from busquedaNoticiasPorPalabras operation
            */
           public void receiveResultbusquedaNoticiasPorPalabras(
                    wtp.ServiceStub.BusquedaNoticiasPorPalabrasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from busquedaNoticiasPorPalabras operation
           */
            public void receiveErrorbusquedaNoticiasPorPalabras(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearMensaje method
            * override this method for handling normal response from crearMensaje operation
            */
           public void receiveResultcrearMensaje(
                    wtp.ServiceStub.CrearMensajeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearMensaje operation
           */
            public void receiveErrorcrearMensaje(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearEvento method
            * override this method for handling normal response from crearEvento operation
            */
           public void receiveResultcrearEvento(
                    wtp.ServiceStub.CrearEventoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearEvento operation
           */
            public void receiveErrorcrearEvento(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for destacarTema method
            * override this method for handling normal response from destacarTema operation
            */
           public void receiveResultdestacarTema(
                    wtp.ServiceStub.DestacarTemaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from destacarTema operation
           */
            public void receiveErrordestacarTema(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for publicarNoticia method
            * override this method for handling normal response from publicarNoticia operation
            */
           public void receiveResultpublicarNoticia(
                    wtp.ServiceStub.PublicarNoticiaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from publicarNoticia operation
           */
            public void receiveErrorpublicarNoticia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarTema method
            * override this method for handling normal response from mostrarTema operation
            */
           public void receiveResultmostrarTema(
                    wtp.ServiceStub.MostrarTemaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarTema operation
           */
            public void receiveErrormostrarTema(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for busquedaNoticiasPorAutor method
            * override this method for handling normal response from busquedaNoticiasPorAutor operation
            */
           public void receiveResultbusquedaNoticiasPorAutor(
                    wtp.ServiceStub.BusquedaNoticiasPorAutorResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from busquedaNoticiasPorAutor operation
           */
            public void receiveErrorbusquedaNoticiasPorAutor(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarNoticia method
            * override this method for handling normal response from eliminarNoticia operation
            */
           public void receiveResulteliminarNoticia(
                    wtp.ServiceStub.EliminarNoticiaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarNoticia operation
           */
            public void receiveErroreliminarNoticia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarNoticia method
            * override this method for handling normal response from mostrarNoticia operation
            */
           public void receiveResultmostrarNoticia(
                    wtp.ServiceStub.MostrarNoticiaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarNoticia operation
           */
            public void receiveErrormostrarNoticia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarSeccion method
            * override this method for handling normal response from eliminarSeccion operation
            */
           public void receiveResulteliminarSeccion(
                    wtp.ServiceStub.EliminarSeccionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarSeccion operation
           */
            public void receiveErroreliminarSeccion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarSubForo method
            * override this method for handling normal response from mostrarSubForo operation
            */
           public void receiveResultmostrarSubForo(
                    wtp.ServiceStub.MostrarSubForoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarSubForo operation
           */
            public void receiveErrormostrarSubForo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for moverSubforo method
            * override this method for handling normal response from moverSubforo operation
            */
           public void receiveResultmoverSubforo(
                    wtp.ServiceStub.MoverSubforoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from moverSubforo operation
           */
            public void receiveErrormoverSubforo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearSubforo method
            * override this method for handling normal response from crearSubforo operation
            */
           public void receiveResultcrearSubforo(
                    wtp.ServiceStub.CrearSubforoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearSubforo operation
           */
            public void receiveErrorcrearSubforo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarMensaje method
            * override this method for handling normal response from mostrarMensaje operation
            */
           public void receiveResultmostrarMensaje(
                    wtp.ServiceStub.MostrarMensajeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarMensaje operation
           */
            public void receiveErrormostrarMensaje(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelarDestacarTema method
            * override this method for handling normal response from cancelarDestacarTema operation
            */
           public void receiveResultcancelarDestacarTema(
                    wtp.ServiceStub.CancelarDestacarTemaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelarDestacarTema operation
           */
            public void receiveErrorcancelarDestacarTema(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for busquedaMensajesPorPalabra method
            * override this method for handling normal response from busquedaMensajesPorPalabra operation
            */
           public void receiveResultbusquedaMensajesPorPalabra(
                    wtp.ServiceStub.BusquedaMensajesPorPalabraResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from busquedaMensajesPorPalabra operation
           */
            public void receiveErrorbusquedaMensajesPorPalabra(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearSeccion method
            * override this method for handling normal response from crearSeccion operation
            */
           public void receiveResultcrearSeccion(
                    wtp.ServiceStub.CrearSeccionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearSeccion operation
           */
            public void receiveErrorcrearSeccion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editarMensaje method
            * override this method for handling normal response from editarMensaje operation
            */
           public void receiveResulteditarMensaje(
                    wtp.ServiceStub.EditarMensajeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editarMensaje operation
           */
            public void receiveErroreditarMensaje(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearTema method
            * override this method for handling normal response from crearTema operation
            */
           public void receiveResultcrearTema(
                    wtp.ServiceStub.CrearTemaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearTema operation
           */
            public void receiveErrorcrearTema(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarEvento method
            * override this method for handling normal response from eliminarEvento operation
            */
           public void receiveResulteliminarEvento(
                    wtp.ServiceStub.EliminarEventoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarEvento operation
           */
            public void receiveErroreliminarEvento(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editarSubforo method
            * override this method for handling normal response from editarSubforo operation
            */
           public void receiveResulteditarSubforo(
                    wtp.ServiceStub.EditarSubforoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editarSubforo operation
           */
            public void receiveErroreditarSubforo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editarTema method
            * override this method for handling normal response from editarTema operation
            */
           public void receiveResulteditarTema(
                    wtp.ServiceStub.EditarTemaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editarTema operation
           */
            public void receiveErroreditarTema(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editarNoticia method
            * override this method for handling normal response from editarNoticia operation
            */
           public void receiveResulteditarNoticia(
                    wtp.ServiceStub.EditarNoticiaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editarNoticia operation
           */
            public void receiveErroreditarNoticia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for mostrarEvento method
            * override this method for handling normal response from mostrarEvento operation
            */
           public void receiveResultmostrarEvento(
                    wtp.ServiceStub.MostrarEventoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mostrarEvento operation
           */
            public void receiveErrormostrarEvento(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarSubforo method
            * override this method for handling normal response from eliminarSubforo operation
            */
           public void receiveResulteliminarSubforo(
                    wtp.ServiceStub.EliminarSubforoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarSubforo operation
           */
            public void receiveErroreliminarSubforo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ocultarEvento method
            * override this method for handling normal response from ocultarEvento operation
            */
           public void receiveResultocultarEvento(
                    wtp.ServiceStub.OcultarEventoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ocultarEvento operation
           */
            public void receiveErrorocultarEvento(java.lang.Exception e) {
            }
                


    }
    